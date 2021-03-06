package com.example.lockv01;

import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;

public class Password implements Serializable {

    @Exclude
    private String app;
    private String username;
    private String password;
    public Password(){};
    private String key;

    public Password(String app, String username, String password) {
        this.app = app;
        this.username = username;
        this.password = password;
    }


    public String getapp() {
        return app;
    }

    public void setapp(String app) {
        this.app = app;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }
}

