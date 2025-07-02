package com.example.servingwebcontent;

public class User {
    String userName;
    public String getUserName(){
        return userName;

    }

    public void setUserName(String name){
        this.userName = name;

    }

    public void printUserName(User u){
        System.out.println(u.userName);
    }



}
