package com.example.friendsbee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class RegisterFourth extends AppCompatActivity implements View.OnClickListener {

    EditText registerPhoneNum, registerPassNum;
    TextView registerPassText;
    private EditText editTextVer;
    ImageButton backBtn4;
    Button registerVerBtn, nextBtn4;
    String name, nickname, birth, phone_number, profileImageUrl;;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;
    private ProgressDialog pd;
    private String s; // otp코드 저장
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage mStorage;
    private FirebaseDatabase mDatabase;
    private String uid;
    private Uri imageUri;
    private String pathUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_fourth);

        registerPhoneNum = (EditText) findViewById(R.id.registerPhoneNum);
        registerPassNum = (EditText) findViewById(R.id.registerPassNum);

        registerPassText = (TextView) findViewById(R.id.registerPassText);
        registerPassText.setOnClickListener(this)
        ;
        backBtn4 = (ImageButton) findViewById(R.id.backBtn4);
        backBtn4.setOnClickListener(this);

        registerVerBtn = (Button) findViewById(R.id.registerVerBtn);
        registerVerBtn.setOnClickListener(this);
        nextBtn4 = (Button) findViewById(R.id.nextBtn4);
        nextBtn4.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance();
        mDatabase = FirebaseDatabase.getInstance();



        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                //signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(RegisterFourth.this,"fail",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                s = verificationId;
                forceResendingToken = token;
                pd.dismiss();
            }
        };



        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        nickname = intent.getStringExtra("nickname");
        birth = intent.getStringExtra("birth");
        phone_number = registerPhoneNum.getText().toString();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backBtn4) {
            Intent intent01 = new Intent(RegisterFourth.this, RegisterThird.class);
            startActivity(intent01);
        }
        if (view.getId() == R.id.registerPassText) {

        }
        if (view.getId() == R.id.registerVerBtn) {
            Intent intent02 = new Intent(RegisterFourth.this, RegisterFifth.class);
            String code = editTextVer.getText().toString();
            verifyPhoneNumberWithCode(s, code);
            Toast.makeText(RegisterFourth.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
            startActivity(intent02);
        }
        if (view.getId() == R.id.nextBtn4) {
            Intent intent03 = new Intent(RegisterFourth.this, RegisterFifth.class);
            startActivity(intent03);
        }
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

                        uid = authResult.getUser().getUid();
                        String uid_code = uid.toString();
                        Uri file = Uri.fromFile(new File(pathUri)); // path

                        StorageReference storageReference = mStorage.getReference().child("usersprofileImages").child("uid/"+file.getLastPathSegment());

                        storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                                Myprofile myprofile = new Myprofile(name, nickname, phone_number, birth ,profileImageUrl, uid);
                                mDatabase.getReference().child("profile").child(uid_code)
                                        .setValue(myprofile);
                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterFourth.this,"프로필 저장 실패 ", Toast.LENGTH_SHORT).show();
                                    }
                                });

                        pd.dismiss();
                        Toast.makeText(RegisterFourth.this,"hi " + uid, Toast.LENGTH_SHORT).show();
                        String phone = firebaseAuth.getCurrentUser().getPhoneNumber();
                        startActivity(new Intent(RegisterFourth.this, LoginActivity.class));
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(RegisterFourth.this,"회원가입 실패", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}