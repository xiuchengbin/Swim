package com.swim.entity;

public enum PointsGoodsType {
    MEMBER(1),//会员
    SWIM_TIME(2),//游泳次数
    POINTS(3),//积分
    ACCOUNT(4),//账户余额
    NOTHING(5);//未获奖

    private Integer pointsGoodsType;
    private String goodsTypeName;

    private PointsGoodsType(Integer goodsType){
         this.pointsGoodsType=goodsType;
         if(goodsType==1) goodsTypeName="会员";
         if(goodsType==2) goodsTypeName="游泳次数";
         if(goodsType==3) goodsTypeName="积分";
         if(goodsType==4) goodsTypeName="账户余额";
         if(goodsType==5) goodsTypeName="未获奖";
    }

    public Integer getGoodsType(){
        return this.pointsGoodsType;
    }
    public String getGoodsTypeName(){
        return this.goodsTypeName;
    }

    public String toString(){
        return pointsGoodsType+"";
    }

}
