package com.example.friendsbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class RegisterFirst extends AppCompatActivity implements View.OnClickListener {

    EditText registerName;
    ImageButton backBtn1;
    Button nextBtn1;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_first);

        registerName = (EditText) findViewById(R.id.registerName);

        backBtn1 = (ImageButton) findViewById(R.id.backBtn1);
        backBtn1.setOnClickListener(this);

        nextBtn1 = (Button) findViewById(R.id.nextBtn1);
        nextBtn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backBtn1) {
            Intent intent01 = new Intent(RegisterFirst.this, LoginActivity.class);
            startActivity(intent01);
        }
        if (view.getId() == R.id.nextBtn1) {
            Intent intent02 = new Intent(RegisterFirst.this, RegisterSecond.class);
            name = registerName.getText().toString();
            intent02.putExtra("name", name);
            startActivity(intent02);
        }
    }
}