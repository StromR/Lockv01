package com.example.lockv01;

public class Password {

    private String app;
    private String id;
    private String password;

    public Password(String app, String id, String password) {
        this.app = app;
        this.id = id;
        this.password = password;

    }

    public String getapp() {
        return app;
    }

    public void setapp(String app) {
        this.app = app;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }
}

