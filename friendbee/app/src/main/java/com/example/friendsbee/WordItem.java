package com.example.friendsbee;

import java.util.ArrayList;

public class WordItem {
    public String word;
    public String meaning;

    // 화면에 표시될 문자열 초기화
    public WordItem(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    // 입력받은 숫자의 리스트생성
    public static ArrayList<WordItem> createContactsList(int numContacts) {
        ArrayList<WordItem> contacts = new ArrayList<WordItem>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new WordItem("Person ", "wohahahaha"));
        }

        return contacts;
    }
}

