package com.example.friendsbee;

public class Review1Model {
    private String review;
    private float rating;
    private String date;
    private String userName;
    private int age;
    private String purl;


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

    public Review1Model(){

    }

    public Review1Model(String review, float rating, String date, String userName, int age, String purl){
        this.review = review;
        this.rating = rating;
        this.date = date;
        this.userName = userName;
        this.age = age;
        this.purl = purl;
    }
}