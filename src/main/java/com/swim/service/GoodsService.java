package com.swim.service;

import com.swim.dao.IGoodsMapper;
import com.swim.dao.IMemberMapper;
import com.swim.dao.IPointsMapper;
import com.swim.dao.ISwimMapper;
import com.swim.entity.*;
import com.swim.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@Service
public class GoodsService {
    @Autowired(required = false)
    private IGoodsMapper iGoodsMapper;
    @Autowired(required = false)
    private ISwimMapper iSwimMapper;
    @Autowired
    private SwimService swimService;
    @Autowired(required = false)
    private IMemberMapper iMemberMapper;
    @Autowired(required = false)
    private IPointsMapper iPointsMapper;

    @Transactional
    public Integer buyGoodsByGoodsId(Integer id,Integer goodsId){//消费记录，参数用户id和商品id
        User user=iSwimMapper.selectUserById(id);
        Goods goods=iGoodsMapper.selectGoodsById(goodsId);
        if(user.getAccount().getBalance()<goods.getGoodsPrice()){
            throw new RuntimeException("账户余额不足");
        }
        if(goods==null){
            throw new RuntimeException("该商品不存在");
        }
        if(iGoodsMapper.cost(id,goods.getGoodsPrice())==1){//账户扣款
            CostHistory costHistory=new CostHistory();
            costHistory.setId(id);
            costHistory.setMoney(-goods.getGoodsPrice());
            costHistory.setGoodsName(goods.getGoodsName());
            costHistory.setCostTime(new Timestamp(System.currentTimeMillis()));
//            swimService.setMoneyData(goods.getGoodsPrice());
            return iGoodsMapper.recordCostHistory(costHistory);
        }else {
            throw new RuntimeException("扣款失败");
        }
    }

    @Transactional
    public Integer buyGoodsByGoodsId(Integer id,Integer goodsId,Float cost){//消费记录，参数用户id和商品id
        Goods goods=iGoodsMapper.selectGoodsById(goodsId);
        if(goods==null){
            throw new RuntimeException("该商品不存在");
        }
        if(iGoodsMapper.cost(id,cost)==1){//账户扣款
            CostHistory costHistory=new CostHistory();
            costHistory.setId(id);
            costHistory.setMoney(-cost);
            costHistory.setGoodsName(goods.getGoodsName());
            costHistory.setCostTime(new Timestamp(System.currentTimeMillis()));
//            swimService.setMoneyData(goods.getGoodsPrice());
            return iGoodsMapper.recordCostHistory(costHistory);
        }else {
            throw new RuntimeException("扣款失败");
        }
    }

    @Transactional
    public Integer buyGoodsByGoodsId2(Integer id,Integer goodsId){//消费记录，参数用户id和商品id(第二版)
        User user=iSwimMapper.selectUserById(id);
        Goods goods=iGoodsMapper.selectGoodsById(goodsId);
        if(user.getAccount().getBalance()<goods.getGoodsPrice()){
            throw new RuntimeException("账户余额不足");
        }
        if(goods==null){
            throw new RuntimeException("该商品不存在");
        }
        if(iGoodsMapper.cost(id,goods.getGoodsPrice())==1){//账户扣款
            if(goods.getGoodsType()==GoodsType.MEMBER.getGoodsType()){
                if(user.getMember().getMemberStatus()==false){
                    iMemberMapper.addNoMemberDay(id,goods.getGoodsValue(),new Date(System.currentTimeMillis()));

                }else {
                    iMemberMapper.addMemberDay(id,goods.getGoodsValue());
                }
            }else if(goods.getGoodsType()==GoodsType.SWIM_TIME.getGoodsType()){
                iPointsMapper.addSwimTime(id,goods.getGoodsValue());
            }else if(goods.getGoodsType()==GoodsType.OTHER.getGoodsType()){
            }else {
                throw new RuntimeException("该商品不可购买");
            }
            CostHistory costHistory=new CostHistory();
            costHistory.setId(id);
            costHistory.setMoney(-goods.getGoodsPrice());
            costHistory.setGoodsName(goods.getGoodsName());
            costHistory.setCostTime(new Timestamp(System.currentTimeMillis()));
//            swimService.setMoneyData(goods.getGoodsPrice());
            iGoodsMapper.recordCostHistory(costHistory);
            return 1;
        }else {
            throw new RuntimeException("扣款失败");
        }
    }

    public Map<String,Object> getGoodsList(Integer page, Integer limit){
        if(page==null||limit==null){
            return null;
        }
        Integer start=(page-1)*limit;
        List<User> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iGoodsMapper.getGoodsCount());//总数据量
        map.put("msg","");
        map.put("data",iGoodsMapper.getGoodsList(start,limit));
        return map;
    }

    public Integer deleteGoods(Integer goodsId){
        if(goodsId==GoodsId.GOODS_TIMER||goodsId==GoodsId.SWIM_ONE_TIME||goodsId==GoodsId.CASH_PLEDGE||goodsId==GoodsId.MEMBER_ONE_MONTH){
            return 0;
        }
        return iGoodsMapper.deleteGoodsById(goodsId);
    }

    public Goods getGoodsByGoodsId(Integer goodsId){
        return iGoodsMapper.selectGoodsById(goodsId);
    }

    public Integer alterGoods(Goods goods){
        Integer goodsId=goods.getGoodsId();
        if(goodsId==GoodsId.GOODS_TIMER||goodsId==GoodsId.CASH_PLEDGE){
            goods.setGoodsValue(null);
        }else if(goodsId==GoodsId.SWIM_ONE_TIME){
            goods.setGoodsValue(1);
        }
        return iGoodsMapper.alterGoods(goods);
    }

    public Integer addGoods(Goods goods){
        if(goods.getGoodsType()==3||goods.getGoodsType()==4){
            return 0;
        }
        if(goods.getGoodsType()==GoodsType.MEMBER.getGoodsType()){
//            goods.setGoodsValue(goods.getGoodsValue()* MyUtil.DAY_MINUTE);
            goods.setGoodsValue(goods.getGoodsValue());
        }
        return iGoodsMapper.addGoods(goods);
    }

    public Map<String,Object> buyGoods(Integer id, Integer goodsId) {
        Map<String,Object> map=new LinkedHashMap<>();
        try {
            buyGoodsByGoodsId2(id,goodsId);
        }catch (RuntimeException e){
            map.put("status",false);
            map.put("msg","购买失败,"+e.getMessage());
            return map;
        }
        map.put("status",true);
        map.put("msg","购买成功");
        return map;
    }

    public Map<String,Object> getGoodsHistory(Integer page, Integer limit,Integer id){
        if(page==null||limit==null){
            return null;
        }
        Integer start=(page-1)*limit;
        List<User> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iGoodsMapper.getCostHistoryCountById(id));//总数据量
        map.put("msg","");
        map.put("data",iGoodsMapper.getCostHistoryById(id,start,limit));
        return map;
    }

    public Map<String,Object> getSwimHistory(Integer page, Integer limit,Integer id){
        if(page==null||limit==null){
            return null;
        }
        Integer start=(page-1)*limit;
        List<User> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iGoodsMapper.getSwimHistoryCountById(id));//总数据量
        map.put("msg","");
        map.put("data",iGoodsMapper.getSwimHistoryById(id,start,limit));
        return map;
    }

    public Map<String,Object> getAllSwimHistory(Integer page, Integer limit){
        if(page==null||limit==null){
            return null;
        }
        Integer start=(page-1)*limit;
        List<User> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iGoodsMapper.getAllSwimHistoryCount());//总数据量
        map.put("msg","");
        map.put("data",iGoodsMapper.getAllSwimHistory(start,limit));
        return map;
    }

    public Map<String,Object> getGoodsHistoryByTime(Integer page, Integer limit, String startTime,String endTime,Integer id){
        if(page==null||limit==null){
            return null;
        }
        Integer start=(page-1)*limit;
        List<User> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iGoodsMapper.getCostHistoryCountByIdAndTime(id,startTime,endTime));//总数据量
        map.put("msg","");
        map.put("data",iGoodsMapper.getCostHistoryByIdAndTime(id,start,limit,startTime,endTime));
        return map;
    }

    public Map<String,Object> getSwimHistoryByTime(Integer page, Integer limit, String startTime,String endTime,Integer id){
        if(page==null||limit==null){
            return null;
        }
        Integer start=(page-1)*limit;
        List<User> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iGoodsMapper.getSwimHistoryCountByIdAndTime(id,startTime,endTime));//总数据量
        map.put("msg","");
        map.put("data",iGoodsMapper.getSwimHistoryByIdAndTime(id,start,limit,startTime,endTime));
        return map;
    }


    public Map<String, Object> getPointsHistoryByTime(Integer page, Integer limit, String startTime,String endTime,Integer id) {
        if(page==null||limit==null){
            return null;
        }
        Integer start=(page-1)*limit;
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iGoodsMapper.getPointsHistoryCountByIdAndTime(id,startTime,endTime));//总数据量
        map.put("msg","");
        map.put("data",iGoodsMapper.getPointsHistoryByIdAndTime(id,start,limit,startTime,endTime));
        return map;
    }

    public Map<String, Object> getAllSwimHistoryByIdOrTime(Integer page, Integer limit, Integer id, String startTime, String endTime) {
        if(page==null||limit==null){
            return null;
        }
        Integer start=(page-1)*limit;
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iGoodsMapper.getAllSwimHistoryCountByIdOrTime(id,startTime,endTime));//总数据量
        map.put("msg","");
        map.put("data",iGoodsMapper.getAllSwimHistoryByIdOrTime(id,start,limit,startTime,endTime));
        return map;
    }


}
