package com.swim.entity;

public enum GoodsType {
    MEMBER(1),//会员
    SWIM_TIME(2),//游泳次数
    SWIM_TIMER(3),//游泳计时
    CASH_PLEDGE(4),//押金
    OTHER(5);//其他

    private Integer goodsType;
    private String goodsTypeName;

    private GoodsType(Integer goodsType){
         this.goodsType=goodsType;
         if(goodsType==1) goodsTypeName="会员";
         if(goodsType==2) goodsTypeName="游泳次数";
         if(goodsType==3) goodsTypeName="游泳计时";
         if(goodsType==4) goodsTypeName="押金";
         if(goodsType==5) goodsTypeName="其他";
    }

    public Integer getGoodsType(){
        return this.goodsType;
    }
    public String getGoodsTypeName(){
        return this.goodsTypeName;
    }

    public String toString(){
        return goodsType+"";
    }

}
