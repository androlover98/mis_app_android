package com.android.mis.models.ViewDetails;

/**
 * Created by rajat on 21/3/17.
 */

public class Address {

    private String line1,line2,city,state,pincode,country,contact_no,type;

    public Address(String line1,String line2,String city,String state,String pincode,String country,String contact_no,String type){
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.country = country;
        this.contact_no = contact_no;
        this.type = type;
    }

    public String getLine1(){
        return line1;
    }

    public String getLine2(){
        return line2;
    }

    public String getCity(){
        return city;
    }

    public String getState(){
        return state;
    }

    public String getPincode(){
        return pincode;
    }

    public String getCountry(){
        return country;
    }

    public String getContact_no(){
        return contact_no;
    }

    public String getType()
    {
        return type;
    }

}
