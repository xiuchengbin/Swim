package com.swim.service;

import com.swim.dao.IMemberMapper;
import com.swim.dao.IPointsMapper;
import com.swim.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

@Service
public class PointsService {
    @Autowired(required = false)
    private IPointsMapper iPointsMapper;
    @Autowired(required = false)
    private IMemberMapper iMemberMapper;
    @Autowired
    private UserManagementService userManagementService;

    @Transactional
    public Object lottery(HttpSession session,int lotteryTime){
        User user=(User) session.getAttribute("user");
        Integer id=user.getId();
        if(iPointsMapper.getPointsById(id)<5*lotteryTime){
            return -1;
        }
        List<PointsGoods> pointsGoods=lottery(id,lotteryTime);
        if(pointsGoods==null){//权值为0
            return null;
        }
        for (int i=0;i<pointsGoods.size();i++){
            if(pointsGoods.get(i).getPointsGoodsType()!=PointsGoodsType.NOTHING.getGoodsType()){//中奖了
                LotteryHistory lotteryHistory=new LotteryHistory();
                lotteryHistory.setId(id);
                lotteryHistory.setPrize(pointsGoods.get(i).getPointsGoodsName());
                lotteryHistory.setLotteryTime(new Timestamp(System.currentTimeMillis()));
                if(iPointsMapper.addLotteryHistory(lotteryHistory)!=1){
                    throw new RuntimeException("抽奖记录错误");
                }
            }
            if(pointsGoods.get(i).getPointsGoodsType()==PointsGoodsType.MEMBER.getGoodsType()){//会员
                iMemberMapper.addMemberDay(id,pointsGoods.get(i).getPointsGoodsValue());
            }else if(pointsGoods.get(i).getPointsGoodsType()==PointsGoodsType.SWIM_TIME.getGoodsType()){//游泳次数
                iPointsMapper.addSwimTime(id,pointsGoods.get(i).getPointsGoodsValue());
            }else if(pointsGoods.get(i).getPointsGoodsType()==PointsGoodsType.POINTS.getGoodsType()){//积分
                setPointsById(id,pointsGoods.get(i).getPointsGoodsValue(),"积分抽奖");
            }else if(pointsGoods.get(i).getPointsGoodsType()==PointsGoodsType.ACCOUNT.getGoodsType()){//账户余额
                iPointsMapper.rechargeAccount(id,pointsGoods.get(i).getPointsGoodsValue());
                userManagementService.setCostHistory(id,Float.parseFloat(pointsGoods.get(i).getPointsGoodsValue().toString()),"抽奖");
            }else if(pointsGoods.get(i).getPointsGoodsType()==PointsGoodsType.NOTHING.getGoodsType()){//未获奖

            }
        }
        return pointsGoods;
    }

//    public List<PointsGoods> lottery(Integer id,int time){//抽奖原版
//        List<PointsGoods> pointsGoodsList=iPointsMapper.getAllPointsGoods();
//        Map<String,PointsGoods> goodsMap=new HashMap<>();//key为随机数，value为指定奖励
//        int weightAll=0;//加权总数
//        int temp=0;//
//        for (int i=0;i<pointsGoodsList.size();i++){//获取总加权数
//            Integer goodsWeight=pointsGoodsList.get(i).getPointsGoodsWeight();//每个商品的加权数
//            for (int j=0;j<goodsWeight;j++){
//                goodsMap.put(String.valueOf(temp),pointsGoodsList.get(i));
//                temp++;
//            }
//            weightAll=weightAll+goodsWeight;
//        }
//        List<PointsGoods> list=new ArrayList<>();//获得奖品
//        Random random=new Random();
//        for (int i=0;i<time;i++){//抽奖次数
//            Integer randomNum=random.nextInt(weightAll);
//            list.add(goodsMap.get(randomNum.toString()));
//        }
//        setPointsById(id,-5*time, "积分抽奖");
//        return list;
//    }
    public List<PointsGoods> lottery(Integer id,int time){//抽奖改进版
        List<PointsGoods> pointsGoodsList=iPointsMapper.getAllPointsGoods();
        List<Integer> prizeNumList=new ArrayList();//奖励数列表
        List<PointsGoods> prizeList=new ArrayList();//奖品列表
        int weightAll=0;//加权总数
        int temp=0;//
        for (int i=0;i<pointsGoodsList.size();i++){//获取总加权数
            Integer goodsWeight=pointsGoodsList.get(i).getPointsGoodsWeight();//每个商品的加权数
            temp=temp+goodsWeight;
            prizeNumList.add(temp);
            prizeList.add(pointsGoodsList.get(i));
            weightAll=weightAll+goodsWeight;
        }
        if(weightAll<=0){
            return null;
        }
        List<PointsGoods> list=new ArrayList<>();//获得奖品
        Random random=new Random();
        for (int i=0;i<time;i++){//抽奖次数
            Integer randomNum=random.nextInt(weightAll);
            int a = 0;
            for (int j=0;j<prizeNumList.size();j++){
                if(randomNum>=a&&randomNum<prizeNumList.get(j)){
                    list.add(prizeList.get(j));
                }
                a=prizeNumList.get(j);
            }
        }
        setPointsById(id,-5*time, "积分抽奖");
        return list;
    }

    public Integer setPointsById(Integer id,Integer points,String source){
        if(iPointsMapper.addPoints(id,points)==1){
            PointsHistory pointsHistory=new PointsHistory();
            pointsHistory.setId(id);
            pointsHistory.setPoints(points);
            pointsHistory.setPointsTime(new Timestamp(System.currentTimeMillis()));
            pointsHistory.setSource(source);
            return iPointsMapper.addPointsHistory(pointsHistory);
        }
        return 0;
    }

    public Map<String,Object> getPointsGoodsList(Integer page, Integer limit){
        Integer start=(page-1)*limit;
        List<User> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iPointsMapper.getGoodsCount());//总数据量
        map.put("msg","");
        map.put("data",iPointsMapper.getPointsGoodsList(start,limit));
        return map;
    }


    public Object deletePointsGoods(Integer pointsGoodsId) {
        return iPointsMapper.deletePointsGoods(pointsGoodsId);
    }

    public Integer alterPointsGoods(PointsGoods pointsGoods) {
        return iPointsMapper.alterPointsGoodsById(pointsGoods);
    }

    public Integer addPointsGoods(PointsGoods pointsGoods) {
        return iPointsMapper.addPointsGoods(pointsGoods);
    }

    public Map<String, Object> getUserPointsHistory(Integer page, Integer limit, Integer id) {
        if(page==null||limit==null){
            return null;
        }
        Integer start=(page-1)*limit;
        List<User> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iPointsMapper.getPointsHistoryCountById(id));//总数据量
        map.put("msg","");
        map.put("data",iPointsMapper.getPointsHistoryById(id,start,limit));
        return map;
    }


    public Map<String, Object> getUserLotteryHistory(Integer page, Integer limit, Integer id) {
        if(page==null||limit==null){
            return null;
        }
        Integer start=(page-1)*limit;
        List<User> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iPointsMapper.getLotteryHistoryCountById(id));//总数据量
        map.put("msg","");
        map.put("data",iPointsMapper.getLotteryHistoryById(id,start,limit));
        return map;
    }


    public Map<String, Object> getLotteryHistoryByTime(Integer page, Integer limit, String startTime, String endTime, Integer id) {
        if(page==null||limit==null){
            return null;
        }
        Integer start=(page-1)*limit;
        List<User> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iPointsMapper.getLotteryHistoryCountByIdAndTime(id,startTime,endTime));//总数据量
        map.put("msg","");
        map.put("data",iPointsMapper.getLotteryHistoryByIdAndTime(id,start,limit,startTime,endTime));
        return map;
    }
}
