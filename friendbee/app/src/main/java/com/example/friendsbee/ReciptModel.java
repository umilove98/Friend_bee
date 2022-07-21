package com.example.friendsbee;


public class ReciptModel{
    public String category;
    public String contents;
    public String date;
    public int hour;
    public int min;
    public String place;
    public int price;
    public String title;
    public String userName;
    public int age;
    public String purl;

    public ReciptModel() {
    }

    public ReciptModel(String category, String contents, String date, int hour, int min, String place, int price, String title, String userName, int age, String purl) {
        this.category = category;
        this.contents = contents;
        this.date = date;
        this.hour = hour;
        this.min = min;
        this.place = place;
        this.price = price;
        this.title = title;
        this.userName = userName;
        this.age = age;
        this.purl = purl;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return Integer.toString(hour);
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getMin() {
        return Integer.toString(min);
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPrice() {
        return Integer.toString(price);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}

