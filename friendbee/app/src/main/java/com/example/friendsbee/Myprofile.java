package com.example.friendsbee;

public class Myprofile {
    private String name;
    private String nick_name;
    private String phone_number;
    private String birth_number;
    public String profileImageUrl; // 사용자 프로필사진
    public String uid; // 현재 사용자(로그인한)


    public Myprofile(String name, String nick_name, String phone_number, String birth_number,String profileImageUrl, String uid){
        this.name = name;
        this.nick_name = nick_name;
        this.phone_number = phone_number;
        this.birth_number = birth_number;
        this.profileImageUrl = profileImageUrl;
        this.uid = uid;
    }

    public String getName() {return this.name;}
    public void setName() {this.name = name;}

    public String getNick_name() {return this.nick_name;}
    public void setNick_name() {this.nick_name = nick_name;}

    public String getPhone_number() {return this.phone_number;}
    public void setPhone_number() {this.phone_number = phone_number;}

    public String getBirth_number() {return this.birth_number;}
    public void setBirth_number() {this.birth_number = birth_number;}

    public String getProfileImageUrl() {return this.profileImageUrl;}
    public void setProfileImageUrl() {this.profileImageUrl = profileImageUrl;}

    public String getUid() {return this.uid;}
    public void setUid() {this.uid = uid;}
}

