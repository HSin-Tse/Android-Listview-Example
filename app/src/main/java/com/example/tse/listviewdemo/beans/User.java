package com.example.tse.listviewdemo.beans;

public class User
{
    private String id;

    private String userName;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getUserName ()
    {
        return userName;
    }

    public void setUserName (String userName)
    {
        this.userName = userName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", userName = "+userName+"]";
    }
}