package com.example.friendsbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class RegisterFourth extends AppCompatActivity implements View.OnClickListener {

    EditText registerPhoneNum, registerPassNum;
    TextView registerPassText;
    ImageButton backBtn4;
    Button registerVerBtn, nextBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_fourth);

        registerPhoneNum = (EditText) findViewById(R.id.registerPhoneNum);
        registerPassNum = (EditText) findViewById(R.id.registerPassNum);

        registerPassText = (TextView) findViewById(R.id.registerPassText);

        backBtn4 = (ImageButton) findViewById(R.id.backBtn4);
        backBtn4.setOnClickListener(this);

        registerVerBtn = (Button) findViewById(R.id.registerVerBtn);
        registerVerBtn.setOnClickListener(this);
        nextBtn4 = (Button) findViewById(R.id.nextBtn4);
        nextBtn4.setOnClickListener(this);

        registerPassNum.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 4) {
                    nextBtn4.setClickable(true);
                } else {
                    nextBtn4.setClickable(false);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backBtn4) {
            Intent intent01 = new Intent(RegisterFourth.this, LoginActivity.class);
            startActivity(intent01);
        }
        if (view.getId() == R.id.registerVerBtn) {
            Intent intent02 = new Intent(RegisterFourth.this, RegisterFifth.class);
            intent02.putExtra("phoneNum", "char value");
            startActivity(intent02);
        }
        if (view.getId() == R.id.nextBtn4) {
            Intent intent03 = new Intent(RegisterFourth.this, RegisterFifth.class);
            startActivity(intent03);
        }
    }
}