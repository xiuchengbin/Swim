package com.swim.schedule;

import com.swim.dao.IDataMapper;
import com.swim.dao.IMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class DataSchedule {
    @Autowired(required = false)
    private IMemberMapper iMemberMapper;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @Scheduled(fixedRate = 600000,initialDelay = 0)
    public void member(){
        iMemberMapper.verifyMemberStatus();
        System.out.println("定时器执行了");
    }
    @PostConstruct
    public void setDataEveryDay2(){
//        Map map=new HashMap<>();
//        map.put("day_click",0);
//        map.put("sum_click",0);
//        map.put("day_money",0);
//        map.put("sum_money",0);
//        map.put("day_swim",0);
//        map.put("sum_swim",0);
//        redisTemplate.opsForHash().putAll("Mydata",map);
        Map<String,Object> data=(Map) redisTemplate.opsForHash().entries("Mydata");
        data.forEach((key,value)->{
//            System.out.println(key);
            if(key.startsWith("day")){
                data.put(key,0);
            }
        });
        redisTemplate.opsForHash().putAll("Mydata",data);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void setDataEveryDay(){
        Map<String,Object> data=(Map) redisTemplate.opsForHash().entries("Mydata");
        data.forEach((key,value)->{
            if(key.startsWith("day")){
                data.put(key,0);
            }
        });
        redisTemplate.opsForHash().putAll("Mydata",data);
        System.out.println("定时器执行了");
    }


}
