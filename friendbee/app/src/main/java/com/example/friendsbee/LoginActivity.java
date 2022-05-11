package com.example.friendsbee;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogIn;
    private Button buttonSignUp;
    private Button ver_btn;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.login_email);
        editTextPassword = (EditText) findViewById(R.id.login_password);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                //signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(LoginActivity.this,"fuk",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                s1 = verificationId;

            }
        };



        buttonSignUp = (Button) findViewById(R.id.join_button);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SignUpActivity 연결
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        buttonLogIn = (Button) findViewById(R.id.login_button);
        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextPassword.getText().toString();
                if(TextUtils.isEmpty(code)){
                    Toast.makeText(LoginActivity.this,"enter code",Toast.LENGTH_SHORT).show();
                }
                else{
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(s1,code);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

        ver_btn = (Button) findViewById(R.id.button);
        ver_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Phone_Number = editTextEmail.getText().toString();
                if(TextUtils.isEmpty(Phone_Number)){
                    Toast.makeText(LoginActivity.this,"enter phone number",Toast.LENGTH_SHORT).show();
                }
                else{
                    startPhoneNumberVerification(Phone_Number);
                }
            }
        });

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,"실패",Toast.LENGTH_SHORT).show();
                }
            }
        };
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        String phone = firebaseAuth.getCurrentUser().getPhoneNumber();
                        Toast.makeText(LoginActivity.this,"Logged in", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this,"코드 틀림", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void startPhoneNumberVerification(String phone_number) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phone_number)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }



}