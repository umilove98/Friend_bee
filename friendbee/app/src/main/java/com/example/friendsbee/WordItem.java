package com.example.friendsbee;

import android.media.Image;
import android.widget.ImageView;

import java.util.ArrayList;

public class WordItem {
    public String word;
    public String meaning;
    public String profileImageUrl; // 사용자 프로필사진

    // 화면에 표시될 문자열 초기화
    public WordItem(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }


    // 입력받은 숫자의 리스트생성
    public static ArrayList<WordItem> createContactsList(int numContacts) {
        ArrayList<WordItem> contacts = new ArrayList<WordItem>();
        contacts.add(new WordItem("크롱", "반가워요~"));


        return contacts;
    }
}

