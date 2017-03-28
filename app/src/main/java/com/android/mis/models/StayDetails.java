package com.android.mis.models;

/**
 * Created by rajat on 21/3/17.
 */

public class StayDetails {
    private String from,to,address,headquarters;
    private int tag;

    public StayDetails(String from,String to,String address,String headquarters,int tag)
    {
        this.from = from;
        this.to = to;
        this.address = address;
        this.headquarters = headquarters;
        this.tag = tag;
    }

    public String getFrom()
    {
        return from;
    }

    public int getTag(){
        return tag;
    }

    public String getTo()
    {
        return to;
    }

    public String getAddress()
    {
        return address;
    }

    public String getHeadquarters()
    {
        return headquarters;
    }
}
