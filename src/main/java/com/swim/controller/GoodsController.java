package com.swim.controller;

import com.swim.entity.Goods;
import com.swim.entity.GoodsType;
import com.swim.util.MyUtil;
import com.swim.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;


    @GetMapping("goods-management/goodsList")
    @ResponseBody
    public Object getGoodsList(Integer page,Integer limit){
        return goodsService.getGoodsList(page, limit);
    }

    @PostMapping("goods-management/deleteGoods")
    @ResponseBody
    public Object deleteGoods(Integer goodsId){
        return goodsService.deleteGoods(goodsId);
    }

    @RequestMapping("goods-management/alter-goods.html")
    public String toAlterGoods(Integer goodsId, Model model){
        Goods goods=goodsService.getGoodsByGoodsId(goodsId);
        model.addAttribute("goods",goods);
        String goodsType=null;
        Integer goodsValue=goods.getGoodsValue();
        if(goods.getGoodsType()== GoodsType.MEMBER.getGoodsType()){
            goodsType=GoodsType.MEMBER.getGoodsTypeName();
        }else if(goods.getGoodsType()== GoodsType.SWIM_TIME.getGoodsType()){
            goodsType=GoodsType.SWIM_TIME.getGoodsTypeName();
        }else if(goods.getGoodsType()== GoodsType.SWIM_TIMER.getGoodsType()){
            goodsType=GoodsType.SWIM_TIMER.getGoodsTypeName();
        }else if(goods.getGoodsType()== GoodsType.CASH_PLEDGE.getGoodsType()){
            goodsType=GoodsType.CASH_PLEDGE.getGoodsTypeName();
        }else if(goods.getGoodsType()== GoodsType.OTHER.getGoodsType()){
            goodsType=GoodsType.OTHER.getGoodsTypeName();
        }
        model.addAttribute("goodsType",goodsType);
        return "goods-management/alter-goods";
    }

    @PostMapping("goods-management/alterGoods")
    @ResponseBody
    public Integer alterGoods(Goods goods){
        return goodsService.alterGoods(goods);
    }

    @PostMapping("goods-management/addGoods")
    @ResponseBody
    public Integer addGoods(Goods goods){
        return goodsService.addGoods(goods);
    }






}
