package com.swim.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PointsAspect {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

//    @AfterReturning("")
//    public void setMoneyData(){
//
//    }

}
