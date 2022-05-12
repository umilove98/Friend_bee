package com.example.friendsbee;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReciptItem {
    public String title;
    public String time;
    public String userName;
    public String place;
    public String price;
    public String status;
    private DatabaseReference mDatabase;


    // 화면에 표시될 문자열 초기화
    public ReciptItem(String title, String time, String userName, String place, String price, String status) {
        this.title = title;
        this.time = time;
        this.userName = userName;
        this.place = place;
        this.price = price;
        this.status = status;

    }


    // 입력받은 숫자의 리스트생성
    public static ArrayList<ReciptItem> createContactsList(int numContacts) {
        ArrayList<ReciptItem> contacts = new ArrayList<ReciptItem>();


        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new ReciptItem("고기 잘 굽는 사람","22-03-31\n오후 8:00 ~ 10:00 (2시간)","Pooh","서울시 동대문구", "20", "모집중"));
        }
        return contacts;
    }
}

