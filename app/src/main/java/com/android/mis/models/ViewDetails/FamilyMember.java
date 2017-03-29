package com.android.mis.models.ViewDetails;

/**
 * Created by rajat on 21/3/17.
 */

public class FamilyMember {
    private String name,relationship,dateofbirth,profession,postal_address,status,image_url;
    private int tag;

    public FamilyMember(String name,String relationship,String dateofbirth,String profession,String postal_address,String status,String image_url,int tag)
    {
        this.name = name;
        this.relationship = relationship;
        this.dateofbirth = dateofbirth;
        this.profession = profession;
        this.postal_address = postal_address;
        this.status = status;
        this.image_url = image_url;
        this.tag = tag;
    }

    public int getTag(){
        return tag;
    }

    public String getName()
    {
        return name;
    }

    public String getRelationship()
    {
        return relationship;
    }

    public String getDateofbirth()
    {
        return dateofbirth;
    }

    public String getProfession()
    {
        return profession;
    }

    public String getPostal_address()
    {
        return postal_address;
    }

    public String getStatus()
    {
        return status;
    }

    public String getImage_url(){
        return image_url;
    }
}
