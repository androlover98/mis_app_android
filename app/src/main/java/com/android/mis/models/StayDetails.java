package com.android.mis.models;

/**
 * Created by rajat on 21/3/17.
 */

public class StayDetails {
    private String from,to,address,headquarters;

    public StayDetails(String from,String to,String address,String headquarters)
    {
        this.from = from;
        this.to = to;
        this.address = address;
        this.headquarters = headquarters;
    }

    public String getFrom()
    {
        return from;
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
