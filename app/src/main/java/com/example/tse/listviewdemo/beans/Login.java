package com.example.tse.listviewdemo.beans;

public class Login
{
    private Body body;

    private String code;

    private String msg;

    public Body getBody ()
    {
        return body;
    }

    public void setBody (Body body)
    {
        this.body = body;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getMsg ()
    {
        return msg;
    }

    public void setMsg (String msg)
    {
        this.msg = msg;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [body = "+body+", code = "+code+", msg = "+msg+"]";
    }
}