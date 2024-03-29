package com.example.friendsbee;

public class RequestInfo {
    private String title;
    private String place;
    private String contents;
    private String date;
    private int price;
    private String category;
    private int hour;
    private int min;
    private String userName;
    private int age;
    private String purl;
    private int condi;
    private String uniq_key;




    public RequestInfo(String title, String place, String contents, String date, int price, String category, int hour, int min, String userName, int age, String purl, int condi, String uniq_key){
        this.title = title;
        this.place = place;
        this.contents = contents;
        this.date = date;
        this.price = price;
        this.category = category;
        this.hour = hour;
        this.min = min;
        this.userName = userName;
        this.age = age;
        this.purl = purl;
        this.condi = condi;
        this.uniq_key = uniq_key;
    }

    public String getTitle() {return this.title;}
    public void setTitle() {this.title = title;}

    public String getPlace() {return this.place;}
    public void setPlace() {this.place = place;}

    public String getContents() {return this.contents;}
    public void setContents() {this.contents = contents;}

    public String getDate() {return this.date;}
    public void setDate() {this.date = date;}

    public int getPrice() {return this.price;}
    public void setPrice() {this.price = price;}

    public String getCategory() {return this.category;}
    public void setCategory() {this.category = category;}

    public int getHour() {return this.hour;}
    public void setHour() {this.hour = hour;}

    public int getMin() {return this.min;}
    public void setMin() {this.min = min;}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
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

    public int getCondi() {
        return condi;
    }

    public void setCondi(int condi) {
        this.condi = condi;
    }

    public String getUniq_key() {
        return uniq_key;
    }

    public void setUniq_key(String uniq_key) {
        this.uniq_key = uniq_key;
    }

}
