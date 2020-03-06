package com.swim.controller;

import com.swim.service.GoodsService;
import com.swim.service.SwimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HistorySearchController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SwimService swimService;

    @GetMapping("/history-search/swim-history.html")
    public String toSwimHistory(){
        return "history-search/swim-history";
    }

    @GetMapping("/history-search/income-history.html")
    public String toIncomeHistory(){
        return "history-search/income-history";
    }

    @GetMapping("/history-search/getSwimHistory")
    @ResponseBody
    public Object getSwimHistory(Integer page,Integer limit){
        return goodsService.getAllSwimHistory(page,limit);
    }


    @GetMapping("/history-search/getSwimHistoryByIdOrTime")
    @ResponseBody
    public Object getSwimHistoryByIdOrTime(Integer page, Integer limit, Integer id, String  start,String end){
        return goodsService.getAllSwimHistoryByIdOrTime(page,limit,id,start,end);
    }

    @GetMapping("/history-search/getIncomeHistory")
    @ResponseBody
    public Object getIncomeHistory(Integer page,Integer limit){
        return swimService.getAllIncomeHistory(page,limit);
    }

    @GetMapping("/history-search/getIncomeHistoryByIdOrTime")
    @ResponseBody
    public Object getIncomeHistoryByIdOrTime(Integer page, Integer limit, Integer id, String  start,String end){
        return swimService.getAllSwimHistoryByIdOrTime(page,limit,id,start,end);
    }

}
