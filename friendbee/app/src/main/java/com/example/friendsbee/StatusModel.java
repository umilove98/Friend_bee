package com.example.friendsbee;

public class StatusModel {
    private String review;
    private float rating;
    private String date;
    private String userName;
    private int age;
    private String purl;
    private String apply1;


    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return Integer.toString(age);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getApply1() {
        return apply1;
    }

    public void setApply1(String apply1) {
        this.apply1 = apply1;
    }


}