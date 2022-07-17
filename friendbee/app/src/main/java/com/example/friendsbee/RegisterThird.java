package com.example.friendsbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class RegisterThird extends AppCompatActivity implements View.OnClickListener {

    EditText registerBirth, registerBirth2;
    ImageButton backBtn3;
    Button nextBtn3;
    String name, nickname, birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_third);

        registerBirth = (EditText) findViewById(R.id.registerBirth);
        registerBirth2 = (EditText) findViewById(R.id.registerBirth2);

        backBtn3 = (ImageButton) findViewById(R.id.backBtn3);
        backBtn3.setOnClickListener(this);

        nextBtn3 = (Button) findViewById(R.id.nextBtn3);
        nextBtn3.setOnClickListener(this);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        nickname = intent.getStringExtra("nickname");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backBtn3) {
            Intent intent01 = new Intent(RegisterThird.this, RegisterSecond.class);
            startActivity(intent01);
        }
        if (view.getId() == R.id.nextBtn3) {
            Intent intent02 = new Intent(RegisterThird.this, RegisterFourth.class);
            birth = registerBirth.getText().toString() + registerBirth2.getText().toString();
            intent02.putExtra("name", name);
            intent02.putExtra("nickname", nickname);
            intent02.putExtra("birth", birth);
            startActivity(intent02);
        }
    }
}