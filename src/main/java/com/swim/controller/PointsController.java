package com.swim.controller;

import com.swim.dao.IPointsMapper;
import com.swim.entity.PointsGoods;
import com.swim.entity.PointsGoodsType;
import com.swim.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@Controller
public class PointsController {
    @Autowired
    private PointsService pointsService;
    @Autowired(required = false)
    private IPointsMapper iPointsMapper;

    @PostMapping("user/lottery")//抽奖
    @ResponseBody
    public Object lottery(HttpSession session){
        return pointsService.lottery(session,1);
    }

    @PostMapping("user/lottery10")
    @ResponseBody
    public Object lottery2(HttpSession session){
        return pointsService.lottery(session,10);
    }

    @GetMapping("points-management/selectPointsGoods")
    @ResponseBody
    public Map<String, Object> selectPointsGoods(Integer page, Integer limit){
        return pointsService.getPointsGoodsList(page,limit);
    }


    @PostMapping("points-management/deletePointsGoods")
    @ResponseBody
    public Object deletePointsGoods(Integer pointsGoodsId){
        return pointsService.deletePointsGoods(pointsGoodsId);
    }

    @GetMapping("points-management/alter-points-goods.html")
    public Object toAlterPointsGoods(Integer pointsGoodsId, Model model){
        PointsGoods pointsGoods=iPointsMapper.getPointsGoodsById(pointsGoodsId);
        model.addAttribute("pointsGoods",pointsGoods);
        DecimalFormat decimalFormat=new DecimalFormat("0.0000");
        BigDecimal temp=BigDecimal.valueOf((pointsGoods.getPointsGoodsWeight()*100.0/iPointsMapper.getSumWeight()));//计算百分比概率
        model.addAttribute("probability",decimalFormat.format(temp)+"%");
        if(pointsGoods.getPointsGoodsType()== PointsGoodsType.MEMBER.getGoodsType()){
            model.addAttribute("pointsGoodsType",PointsGoodsType.MEMBER.getGoodsTypeName());
        }else if(pointsGoods.getPointsGoodsType()== PointsGoodsType.SWIM_TIME.getGoodsType()){
            model.addAttribute("pointsGoodsType",PointsGoodsType.SWIM_TIME.getGoodsTypeName());
        }else if(pointsGoods.getPointsGoodsType()== PointsGoodsType.POINTS.getGoodsType()){
            model.addAttribute("pointsGoodsType",PointsGoodsType.POINTS.getGoodsTypeName());
        }else if(pointsGoods.getPointsGoodsType()== PointsGoodsType.ACCOUNT.getGoodsType()){
            model.addAttribute("pointsGoodsType",PointsGoodsType.ACCOUNT.getGoodsTypeName());
        }else if(pointsGoods.getPointsGoodsType()== PointsGoodsType.NOTHING.getGoodsType()){
            model.addAttribute("pointsGoodsType",PointsGoodsType.NOTHING.getGoodsTypeName());
        }
        return "points-management/alter-points-goods";
    }

    @PostMapping("points-management/alterPointsGoods")
    @ResponseBody
    public Object alterPointsGoods(PointsGoods pointsGoods){
        return pointsService.alterPointsGoods(pointsGoods);
    }

    @PostMapping("points-management/addPointsGoods")
    @ResponseBody
    public Object addPointsGoods(PointsGoods pointsGoods){
        return pointsService.addPointsGoods(pointsGoods);
    }
}
