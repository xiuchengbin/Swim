package com.swim.service;

import com.swim.dao.IGoodsMapper;
import com.swim.dao.IIncomeMapper;
import com.swim.dao.IPointsMapper;
import com.swim.dao.ISwimMapper;
import com.swim.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class SwimService {
    @Autowired(required = false)
    private ISwimMapper iSwimMapper;
    @Autowired(required = false)
    private GoodsService goodsService;
    @Autowired(required = false)
    private IPointsMapper iPointsMapper;
    @Autowired(required = false)
    private IIncomeMapper iIncomeMapper;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private final static int ADD_POINTS=10;
    private final static String POINTS_SOURCE="游泳";
//    private final static String VISTOR_ID="vistor_id";
//    private final static String NAME="name";
//    private final static String PHONE_NUMBER="phone_number";

    public Map<String,Object> selectVistorSwimming(Integer page,Integer limit){
        Integer start=(page-1)*limit;
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iSwimMapper.selectCountSwimmingVistor());//总数据量
        map.put("msg","");
        map.put("data",iSwimMapper.selectSwimmingVistor(start,limit));
        return map;
    }

    public Map<String,Object> selectUserListNoSwim(Integer page,Integer limit){
        Integer start=(page-1)*limit;
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iSwimMapper.selectCountNoSwimUser());//总数据量
        map.put("msg","");
        map.put("data",iSwimMapper.selectNoSwimUser(start,limit));
        return map;
    }

    public Map<String,Object> selectUserListSwimming(Integer page,Integer limit){
        Integer start=(page-1)*limit;
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iSwimMapper.selectCountSwimmingUser());//总数据量
        map.put("msg","");
        map.put("data",iSwimMapper.selectSwimmingUser(start,limit));
        return map;
    }


    public Map<String,Object> selectVistorSwimmingByType(Integer page,Integer limit,String type,String param){
        Integer start=(page-1)*limit;
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iSwimMapper.selectCountSwimmingVistorByType(type,param+"%"));//总数据量
        map.put("msg","");
        map.put("data",iSwimMapper.selectSwimmingVistorByType(start,limit,type,param+"%"));
        return map;
    }

    public Map<String,Object> selectUserListNoSwimByType(Integer page,Integer limit,String type,String param){
        Integer start=(page-1)*limit;
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        if(type.equals("username")){
            map.put("count",iSwimMapper.selectCountNoSwimByType1(type,param+"%"));//总数据量
        }else{
            map.put("count",iSwimMapper.selectCountNoSwimByType2(type,param+"%"));//总数据量
        }
        map.put("msg","");
        map.put("data",iSwimMapper.selectNoSwimUserByType(start,limit,type,param+"%"));
        return map;
    }

    public Map<String,Object> selectUserListSwimmingByType(Integer page,Integer limit,String type,String param){
        Integer start=(page-1)*limit;
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        if(type.equals("username")){
            map.put("count",iSwimMapper.selectCountSwimmingByType1(type,param+"%"));//总数据量
        }else{
            map.put("count",iSwimMapper.selectCountSwimmingByType2(type,param+"%"));//总数据量
        }
        map.put("msg","");
        map.put("data",iSwimMapper.selectSwimmingUserByType(start,limit,type,param+"%"));
        return map;
    }


    public Integer deleteVistor(Integer vistorId) {
        return iSwimMapper.deleteVistor(vistorId);
    }

    public Float selectGoodsPrice(Integer type){
        if(type==1){//次数计费
            return swimBySwimTime();//单次游泳费用
        }else if(type==2){//时间计费
            return swimByCASH_PLEDGE();//押金
        }else {
            return 0f;
        }
    }

    public Integer addVistor(Vistor vistor,Integer type){
        if(vistor.getBalance()<=0){
            return 0;
        }
        Integer temp=0;
        Timestamp time=new Timestamp(System.currentTimeMillis());
        setSwims();
        if(type==1){//计次消费
            vistor.setCost(vistor.getBalance());
            vistor.setBalance(0f);
            vistor.setSwimStart(time);
            vistor.setSwimEnd(time);
            temp=iSwimMapper.addVistor(vistor);
            setMoneyData(vistor.getCost(),"散客计时游泳消费",-1);
            return temp;
        }else if(type==2){//计时消费
            vistor.setSwimStart(time);
            temp=iSwimMapper.addVistor(vistor);
            if(temp==1){
                return vistor.getVistorId();
            }else {
                return temp;
            }
        }else {
            return temp;
        }
    }

    public Float swimBySwimTime(){//单次费用
//        return iSwimMapper.selectGoodsBySwimTime(GoodsType.SWIM_TIME.getGoodsType()).getGoodsPrice();//单次游泳费用
        return iSwimMapper.selectGoodsByGoodsId(GoodsId.SWIM_ONE_TIME).getGoodsPrice();
    }

    public Float swimByCASH_PLEDGE(){//押金费用(使用计时初始押金)
        return iSwimMapper.selectGoodsByGoodsType(GoodsType.CASH_PLEDGE.getGoodsType()).getGoodsPrice();//押金
    }

    public Float swimBySwimTimer(){//计时费用的单价
        return iSwimMapper.selectGoodsByGoodsType(GoodsType.SWIM_TIMER.getGoodsType()).getGoodsPrice();//单次游泳费用
    }

    public String getSwimTime(Timestamp timestamp){//获取游泳时间
        return getSwimTimeByLongTime(System.currentTimeMillis()-timestamp.getTime());
    }

    public Long getSwimTimeByTimeStamp(Timestamp timestamp){//获取游泳时间
        return System.currentTimeMillis()-timestamp.getTime();
    }

    public String getSwimTimeByLongTime(Long time){
        time=time/1000;
        String hour=time/3600+"";
        String minute=(time%3600)/60+"";
        String second=(time%3600)%60+"";
        return hour+"时"+minute+"分"+second+"秒";
    }

    public Float getResultBySwimBySwimTimer(Long longTime) {//计算计时消费应该消费的金额
        Float hourCost=swimBySwimTimer();
        Float time=longTime/(1000*3600f);//游泳的小时
        Float temp=hourCost*time;
        Float cost=Float.parseFloat(String.format("%.2f",temp));//应付金额
        return cost;
    }

    public Integer endSwimVistor(Vistor vistor) {//散客结束游泳
        vistor.setBalance(0f);
        setMoneyData(vistor.getCost(),"散客计时游泳消费",-1);
        return iSwimMapper.endSwimVistor(vistor);
    }

    public User selectUserAndSwimHistoryById(Integer id){
        return iSwimMapper.selectUserAndSwimHistoryById(id);
    }

    @Transactional
    public Object swimStartByTimer(Integer id) {//时间计时
        User user=iSwimMapper.selectUserById(id);
        if(user==null){
            return "操作失败，查无此人";
        }
        if(user.getUserInfo().getStatus()==false){//该账号已被冻结
            return "操作失败，该账号已被冻结";
        }
        if(user.getAccount().getBalance()<=0){//该账号已欠费
            return "操作失败，该账号余额不足，请先充值";
        }
        if(user.getUserInfo().getSwimStatus()){//该账号已在游泳状态
            return "操作失败，该账号已在游泳状态";
        }
        setSwims();
        if(user.getMember().getMemberStatus()&&swimHistoryByTime(id)){//会员直接开始
            return "操作成功，会员享受免费游泳";
        }
        Integer temp=iSwimMapper.setSwimStatusToTrue(id);
        if(temp==1){
            SwimHistory swimHistory=new SwimHistory();//会员的游泳记录视为次数游泳
            swimHistory.setId(id);
            swimHistory.setSwimStart(new Timestamp(System.currentTimeMillis()));
            swimHistory.setSwimEnd(null);
            iSwimMapper.recordSwinHistory(swimHistory);
            return "操作成功，计时开始";
        }else {
            return "操作失败，请重试";
        }
    }

    public Boolean addPointsBySwim(Integer id){//每次游泳均增加10-100积分(10的整数倍)
        PointsHistory pointsHistory=new PointsHistory();
        pointsHistory.setId(id);
        Random random=new Random();
        int points=ADD_POINTS*random.nextInt(10);
        pointsHistory.setPoints(points);
        pointsHistory.setPointsTime(new Timestamp(System.currentTimeMillis()));
        pointsHistory.setSource(POINTS_SOURCE);
        return iPointsMapper.addPoints(id,points)==1&&iPointsMapper.addPointsHistory(pointsHistory)==1?true:false;
    }

    @Transactional
    public Boolean swimHistoryByTime(Integer id){//次数游泳历史记录
        SwimHistory swimHistory=new SwimHistory();//会员的游泳记录视为次数游泳
        swimHistory.setId(id);
        long time=System.currentTimeMillis();
        swimHistory.setSwimStart(new Timestamp(time));
        swimHistory.setSwimEnd(new Timestamp(time));
        if(!addPointsBySwim(id)){
            throw new RuntimeException("增加积分异常");
        }
        return iSwimMapper.recordSwinHistory(swimHistory)==1?true:false;
    }

    @Transactional
    public Object swimStartByTime(Integer id) {//使用次数计费
        User user=iSwimMapper.selectUserById(id);
        if(user==null){
            return "操作失败，查无此人";
        }
        if(user.getUserInfo().getStatus()==false){//该账号已被冻结
            return "操作失败，该账号已被冻结";
        }
        if(user.getAccount().getBalance()<0){//该账号已欠费
            return "操作失败，该账号余额不足，请先充值";
        }
        if(user.getUserInfo().getSwimStatus()){//该账号已在游泳状态
            return "操作失败，该账号已在游泳状态";
        }
        if(user.getMember().getMemberStatus()&&swimHistoryByTime(id)){//会员直接开始
            setSwims();
            return "操作成功，会员享受免费游泳";
        }
        if(user.getAccount().getRemainTime()<=0){//该账号次数不足
            if(user.getAccount().getBalance()>=swimBySwimTime()){//使用余额购买一次次数（余额大于等于一次单次游泳价格）
                if(goodsService.buyGoodsByGoodsId(id, GoodsId.SWIM_ONE_TIME)==1&&swimHistoryByTime(id)){//购买一次余额商品
                    setSwims();
                    return "操作成功，游泳次数不足，自动购买一次";
                }
                return "操作失败，购买余额商品失败";
            }
            return "操作失败，该账号游泳次数不足";
        }else{//该账号还有至少一次的次数
            if(iSwimMapper.useRemainTime(id)==1&&swimHistoryByTime(id)){
                int cishu=iSwimMapper.selectRemainTime(id);//剩余次数
                setSwims();
                return "操作成功，使用一次游泳次数，剩余次数："+cishu;
            }
            return "操作失败，请重试";
        }
    }

    @Transactional
    public Object endSwimUser(Integer id,Float cost,Timestamp swimEnd) {//结束游泳
        if(iSwimMapper.setEndSwimHistory(id,swimEnd)==1
                &&iSwimMapper.swimCostByTimer(id,cost)==1&&iSwimMapper.setSwimStatusToFalse(id)==1
                &&goodsService.buyGoodsByGoodsId(id,GoodsId.GOODS_TIMER,cost)==1
                &&addPointsBySwim(id)){
//            setMoneyData(cost);
            return 1;
        }else{
            throw new RuntimeException("游泳结算异常");
        }
    }

    @Transactional
    public void setMoneyData(Float money,String source,Integer id){//增加收入
        Map<String,Object> data=(Map) redisTemplate.opsForHash().entries("Mydata");
        data.forEach((key,value)->{
            if(key.endsWith("money")){
                Float newMoneyData=Float.parseFloat(data.get(key).toString())+money;
                DecimalFormat decimalFormat=new DecimalFormat("0.00");
                data.put(key,Float.parseFloat(decimalFormat.format(newMoneyData)));
            }
        });
        redisTemplate.opsForHash().putAll("Mydata",data);
        IncomeHistory incomeHistory=new IncomeHistory();
        incomeHistory.setId(id);
        incomeHistory.setIncomeMoney(money);
        incomeHistory.setSource(source);
        incomeHistory.setTime(new Timestamp(System.currentTimeMillis()));
        iIncomeMapper.addIncomeHistory(incomeHistory);
    }

    public void setSwims(){//增加游泳次数
        Map<String,Object> data=(Map) redisTemplate.opsForHash().entries("Mydata");
        data.forEach((key,value)->{
            if(key.endsWith("swim")){
                Integer newSwimData=(Integer) data.get(key)+1;
                data.put(key,newSwimData);
            }
        });
        redisTemplate.opsForHash().putAll("Mydata",data);
    }

    public Object getAllIncomeHistory(Integer page, Integer limit) {
        if(page==null||limit==null){
            return null;
        }
        Integer start=(page-1)*limit;
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iIncomeMapper.getAllCountIncomeHistory());//总数据量
        map.put("msg","");
        map.put("data",iIncomeMapper.getAllIncomeHistory(start,limit));
        return map;
    }

    public Map<String, Object> getAllSwimHistoryByIdOrTime(Integer page, Integer limit, Integer id, String startTime, String endTime) {
        if(page==null||limit==null){
            return null;
        }
        Integer start=(page-1)*limit;
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iIncomeMapper.getAllIncomeHistoryCountByIdOrTime(id,startTime,endTime));//总数据量
        map.put("msg","");
        map.put("data",iIncomeMapper.getAllIncomeHistoryByIdOrTime(id,start,limit,startTime,endTime));
        return map;
    }
}
