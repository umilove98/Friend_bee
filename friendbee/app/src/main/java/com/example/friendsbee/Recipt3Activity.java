package com.example.friendsbee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Recipt3Activity extends AppCompatActivity {

    private Button button1, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_form3);

        button1 = findViewById(R.id.request_form_chat_btn);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(Recipt3Activity.this, ChatRoomActivity2.class);
                //startActivity(intent);

            }
        });



    }

}