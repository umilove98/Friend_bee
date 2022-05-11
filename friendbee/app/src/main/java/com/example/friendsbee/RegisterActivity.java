package com.example.friendsbee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText editTextPhoneNumber;
    private EditText editTextVer;
    private Button regist_button;
    private Button ver_button;
    
    private String s; // otp코드 저장
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private ProgressDialog pd;
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        firebaseAuth = FirebaseAuth.getInstance();

        editTextPhoneNumber = (EditText) findViewById(R.id.phone_Number);
        editTextVer = (EditText) findViewById(R.id.cerTi);

        regist_button = (Button) findViewById(R.id.signupbutton);
        ver_button = (Button) findViewById(R.id.phone_Number_button);

        pd = new ProgressDialog(this);
        pd.setTitle("please wait...");
        pd.setCanceledOnTouchOutside(false);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                //signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(RegisterActivity.this,"fuk",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                s = verificationId;
                forceResendingToken = token;
                pd.dismiss();


            }
        };

        regist_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = editTextVer.getText().toString();
                if(TextUtils.isEmpty(code)){
                    Toast.makeText(RegisterActivity.this,"enter code",Toast.LENGTH_SHORT).show();
                }
                else{
                    verifyPhoneNumberWithCode(s,code);
                }
            }
        });

        ver_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Phone_Number = editTextPhoneNumber.getText().toString();
                if(TextUtils.isEmpty(Phone_Number)){
                    Toast.makeText(RegisterActivity.this,"enter phone number",Toast.LENGTH_SHORT).show();
                }
                else{
                    startPhoneNumberVerification(Phone_Number);
                }
            }
        });
    }

    private void verifyPhoneNumberWithCode(String s, String code) {
        pd.setMessage("Verifiy Code");
        pd.show();

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(s,code);
        signInWithPhoneAuthCredential(credential);
    }

    private void startPhoneNumberVerification(String phone_number) {
        pd.setMessage("verifying Phone Number");
        pd.show();

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phone_number)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        pd.setMessage("Logging In");

        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        pd.dismiss();
                        String phone = firebaseAuth.getCurrentUser().getPhoneNumber();
                        Toast.makeText(RegisterActivity.this,"Logged in as" + phone, Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(RegisterActivity.this,"d", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
