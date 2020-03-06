package com.swim.controller;

import com.swim.entity.SwimHistory;
import com.swim.entity.User;
import com.swim.entity.Vistor;
import com.swim.service.SwimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Map;

@Controller
public class SwimController {
    @Autowired
    private SwimService swimService;

    @GetMapping("swim-management/vistorList")
    @ResponseBody
    public Map<String, Object> selectSwimmingVistor(Integer page, Integer limit){
        return swimService.selectVistorSwimming(page, limit);
    }

    @GetMapping("swim-management/userListNoSwim")
    @ResponseBody
    public Map<String, Object> selectUserListNoSwim(Integer page, Integer limit){
        return swimService.selectUserListNoSwim(page, limit);
    }

    @GetMapping("swim-management/userListSwimming")
    @ResponseBody
    public Map<String, Object> selectUserListSwimming(Integer page, Integer limit){
        return swimService.selectUserListSwimming(page, limit);
    }

    @GetMapping("swim-management/vistorListByType")
    @ResponseBody
    public Map<String, Object> selectSwimmingVistorByType(Integer page, Integer limit, String type, String param){
        return swimService.selectVistorSwimmingByType(page, limit,type,param);
    }

    @GetMapping("swim-management/userListNoSwimByType")
    @ResponseBody
    public Map<String, Object> selectUserListNoSwimByType(Integer page, Integer limit, String type, String param){
        return swimService.selectUserListNoSwimByType(page, limit,type,param);
    }

    @GetMapping("swim-management/userListSwimmingByType")
    @ResponseBody
    public Map<String, Object> selectUserListSwimmingByType(Integer page, Integer limit, String type, String param){
        return swimService.selectUserListSwimmingByType(page, limit,type,param);
    }

    @GetMapping("swim-management/user-swimEnd.html")//计时游泳结算页
    public String toUserSwimEnd(Model model, Integer id){
        User user=swimService.selectUserAndSwimHistoryById(id);
        if(user==null){
            return "404";//404
        }
        SwimHistory swimHistory=user.getSwimHistory();
        long swimTime=swimService.getSwimTimeByTimeStamp(swimHistory.getSwimStart());
        Float cost=swimService.getResultBySwimBySwimTimer(swimTime);//应付金额
        if(cost>swimService.swimByCASH_PLEDGE()){//计时游泳消费最高不超过该金额
            cost=swimService.swimByCASH_PLEDGE();
        }
        model.addAttribute("swimTime",swimService.getSwimTime(swimHistory.getSwimStart()));//游泳的时间
        model.addAttribute("user",user);
        model.addAttribute("cost",cost);
        model.addAttribute("swimEnd",new Timestamp(swimHistory.getSwimStart().getTime()+swimTime));
        return "swim-management/user-swim-end";
    }

    @PostMapping("swim-management/endSwimUser")//结算游泳，执行扣款等操作
    @ResponseBody
    public Object endSwimUser(Integer id,Float cost,Timestamp swimEnd){
        return swimService.endSwimUser(id, cost, swimEnd);
    }

    @PostMapping("swim-management/deleteVistor")
    @ResponseBody
    public Integer deleteVistor(Integer vistorId){
        return swimService.deleteVistor(vistorId);
    }

    @PostMapping("swim-management/selectPrice")
    @ResponseBody
    public Object selectPrice(Integer type){
        return swimService.selectGoodsPrice(type);
    }

    @PostMapping("swim-management/addVistor")
    @ResponseBody
    public Object addVistor(Vistor vistor,Integer type){
        return swimService.addVistor(vistor, type);
    }

    @PostMapping("swim-management/endSwimVistor")
    @ResponseBody
    public Object endSwimVistor(Vistor vistor){//散客结束游泳
        return swimService.endSwimVistor(vistor);
    }

    @PostMapping("swim-management/swimStartByTimer")//计时游泳
    @ResponseBody
    public Object swimStartByTimer(Integer id){
        return swimService.swimStartByTimer(id);
    }

    @PostMapping("swim-management/swimStartByTime")//次数游泳
    @ResponseBody
    public Object swimStartByTime(Integer id){
        return swimService.swimStartByTime(id);
    }



}
