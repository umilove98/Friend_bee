package com.example.friendsbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class RegisterFifth extends AppCompatActivity implements View.OnClickListener {

    ImageButton backBtn5, registerProfImgBtn;
    Button finishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_fifth);

        backBtn5 = (ImageButton) findViewById(R.id.backBtn5);
        backBtn5.setOnClickListener(this);

        registerProfImgBtn = (ImageButton) findViewById(R.id.registerProfImgBtn);
        registerProfImgBtn.setOnClickListener(this);

        finishBtn = (Button) findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backBtn5) {
            Intent intent01 = new Intent(RegisterFifth.this, LoginActivity.class);
            startActivity(intent01);
        }

        if (view.getId() == R.id.finishBtn) {
            Intent intent02 = new Intent(RegisterFifth.this, LoginActivity.class);
            startActivity(intent02);
        }
    }
}