package com.example.psm.bestfood;

import android.app.Application;
import android.os.StrictMode;

import com.example.psm.bestfood.item.FoodInfoItem;
import com.example.psm.bestfood.item.MemberInfoItem;

public class MyApp extends Application{
    private MemberInfoItem memberInfoItem;
    private FoodInfoItem foodInfoItem;

    @Override
    public void onCreate(){
        super.onCreate();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    public MemberInfoItem getMemberInfoItem() {
        if(memberInfoItem==null)memberInfoItem = new MemberInfoItem();
        return memberInfoItem;
    }
    public void setMemberInfoItem(MemberInfoItem item){
        this.memberInfoItem = item;
    }
    public int getMemberSeq(){
        return memberInfoItem.seq;
    }
    public void setFoodInfoItem(FoodInfoItem foodInfoItem){
        this.foodInfoItem = foodInfoItem;
    }

    public FoodInfoItem getFoodInfoItem() {
        return foodInfoItem;
    }
}
