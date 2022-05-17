package com.example.friendsbee;

public class Myprofile {
    private String name;
    private String nick_name;
    private String phone_number;
    private String birth_number;


    public Myprofile(String name, String nick_name, String phone_number, String birth_number){
        this.name = name;
        this.nick_name = nick_name;
        this.phone_number = phone_number;
        this.birth_number = birth_number;
    }

    public String getTitle() {return this.name;}
    public void setTitle() {this.name = name;}

    public String getPlace() {return this.nick_name;}
    public void setPlace() {this.nick_name = nick_name;}

    public String getContents() {return this.phone_number;}
    public void setContents() {this.phone_number = phone_number;}

    public String getDate() {return this.birth_number;}
    public void setDate() {this.birth_number = birth_number;}


}

