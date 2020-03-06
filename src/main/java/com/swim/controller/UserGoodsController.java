package com.swim.controller;

import com.swim.entity.User;
import com.swim.service.GoodsService;
import com.swim.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Calendar;
import java.util.Map;

@Controller
public class UserGoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private PointsService pointsService;


    @GetMapping("user/goods-list.html")
    public String toGoodsList(){
        return "user/goods-list";
    }

    @GetMapping("user/cost-history.html")
    public String toCostHistory(){
        return "user/cost-history";
    }

    @GetMapping("user/swim-history.html")
    public String toSwimHistory(){
        return "user/swim-history";
    }

    @GetMapping("user/points-history.html")
    public String toPointsHistory(){
        return "user/points-history";
    }

    @GetMapping("user/lottery-history.html")
    public String toLotteryHistory(){
        return "user/lottery-history";
    }

    @GetMapping("user/goodsList")
    @ResponseBody
    public Object getGoodsList(Integer page,Integer limit){
        return goodsService.getGoodsList(page, limit);
    }

    @PostMapping("user/buyGoods")
    @ResponseBody
    public Map<String,Object> buyGoods(Integer goodsId, HttpSession session){
        User user= (User) session.getAttribute("user");
        return goodsService.buyGoods(user.getId(),goodsId);
    }

    @GetMapping("user/getCostHistory")
    @ResponseBody
    public Map<String,Object> getUserCostHistory(Integer page, Integer limit,HttpSession session){
        User user= (User) session.getAttribute("user");
        return goodsService.getGoodsHistory(page,limit,user.getId());
    }

    @GetMapping("user/getPointsHistory")
    @ResponseBody
    public Map<String,Object> getUserPointsHistory(Integer page, Integer limit, HttpSession session){
        User user= (User) session.getAttribute("user");
        return pointsService.getUserPointsHistory(page,limit,user.getId());
    }

    @GetMapping("user/getLotteryHistory")
    @ResponseBody
    public Map<String,Object> getUserLotteryHistory(Integer page, Integer limit, HttpSession session){
        User user= (User) session.getAttribute("user");
        return pointsService.getUserLotteryHistory(page,limit,user.getId());
    }

    @GetMapping("user/getCostHistoryByTime")
    @ResponseBody
    public Map<String,Object> getUserCostHistoryByTime(Integer page, Integer limit, String  start,
                                                       String end, HttpSession session){
        User user= (User) session.getAttribute("user");
        return goodsService.getGoodsHistoryByTime(page,limit,start.trim(),end.trim(),user.getId());
    }

    @GetMapping("user/getSwimHistoryByTime")
    @ResponseBody
    public Map<String,Object> getUserSwimHistoryByTime(Integer page, Integer limit, String  start,
                                                       String end, HttpSession session){
        User user= (User) session.getAttribute("user");
        return goodsService.getSwimHistoryByTime(page,limit,start.trim(),end.trim(),user.getId());
    }

    @GetMapping("user/getPointsHistoryByTime")
    @ResponseBody
    public Map<String,Object> getUserPointsHistoryByTime(Integer page, Integer limit, String  start,
                                                       String end, HttpSession session){
        User user= (User) session.getAttribute("user");
        return goodsService.getPointsHistoryByTime(page,limit,start.trim(),end.trim(),user.getId());
    }

    @GetMapping("user/getLotteryHistoryByTime")
    @ResponseBody
    public Map<String,Object> getUserLotteryHistoryByTime(Integer page, Integer limit, String  start,
                                                       String end, HttpSession session){
        User user= (User) session.getAttribute("user");
        return pointsService.getLotteryHistoryByTime(page,limit,start.trim(),end.trim(),user.getId());
    }


    @GetMapping("user/getSwimHistory")
    @ResponseBody
    public Map<String,Object> getUserSwimHistory(Integer page, Integer limit,HttpSession session){
        User user= (User) session.getAttribute("user");
        return goodsService.getSwimHistory(page,limit,user.getId());
    }

}
