package com.example.mycompany.householdbook;

import android.app.Application;

public class Common extends Application {
    private String UserName;

    @Override
    public void onCreate(){
        super.onCreate();
    }

    public String getUserName(){
        return UserName;
    }

    public void setUserName(String name){
        UserName = name;
    }
}
