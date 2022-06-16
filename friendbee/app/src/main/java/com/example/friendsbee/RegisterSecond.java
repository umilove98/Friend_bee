package com.example.friendsbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class RegisterSecond extends AppCompatActivity implements View.OnClickListener {

    EditText registerNickName;
    ImageButton backBtn2;
    Button nickNameCheckBtn, nextBtn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_second);

        registerNickName = (EditText) findViewById(R.id.registerNickName);

        backBtn2 = (ImageButton) findViewById(R.id.backBtn2);
        backBtn2.setOnClickListener(this);

        nickNameCheckBtn = (Button) findViewById(R.id.nickNameCheckBtn);
        nickNameCheckBtn.setOnClickListener(this);

        nextBtn2 = (Button) findViewById(R.id.nextBtn2);
        nextBtn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backBtn2) {
            Intent intent01 = new Intent(RegisterSecond.this, LoginActivity.class);
            startActivity(intent01);
        }
        if (view.getId() == R.id.nickNameCheckBtn) {
            
        }
        if (view.getId() == R.id.nextBtn2) {
            Intent intent03 = new Intent(RegisterSecond.this, RegisterThird.class);
            startActivity(intent03);
        }
    }
}