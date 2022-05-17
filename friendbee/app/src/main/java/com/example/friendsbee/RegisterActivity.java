package com.example.friendsbee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;


public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText editTextPhoneNumber;
    private EditText editTextVer;
    private Button regist_button;
    private Button ver_button;
    private ImageView img_button;
    private EditText editTextName;
    private EditText editTextNick_Name;
    private EditText editTextBirth_Number;
    private Uri imageUri;
    private String pathUri;
    
    private String s; // otp코드 저장
    private DatabaseReference mDatabase;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private ProgressDialog pd;
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        editTextName = findViewById(R.id.signName);
        editTextNick_Name = findViewById(R.id.nickName);
        editTextBirth_Number = findViewById(R.id.R_R_Number);
        editTextPhoneNumber = (EditText) findViewById(R.id.phone_Number);
        editTextVer = (EditText) findViewById(R.id.cerTi);
        img_button = findViewById(R.id.pro_Img);

        String name = editTextName.getText().toString();
        String nick_name = editTextNick_Name.getText().toString();
        String birth_number = editTextBirth_Number.getText().toString();
        String phone_number = editTextPhoneNumber.getText().toString();

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

        ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    //result.getResultCode()를 통하여 결과값 확인
                    if(result.getResultCode() == RESULT_OK) {
                        //ToDo
                        imageUri = result.getData().getData();
                        pathUri = getPath(result.getData().getData());
                        img_button.setImageURI(imageUri); // 이미지 띄움
                    }
                    if(result.getResultCode() == RESULT_CANCELED){
                        //ToDo
                    }
                }
        );

        regist_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = editTextVer.getText().toString();
                if(TextUtils.isEmpty(code)){
                    Toast.makeText(RegisterActivity.this,"enter code",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(name.length() > 0) {
                        Myprofile myprofile = new Myprofile(name, nick_name, phone_number, birth_number);
                        mDatabase.child("profile").push().setValue(myprofile);
                        verifyPhoneNumberWithCode(s, code);
                        Toast.makeText(RegisterActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                    }
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

        img_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                mStartForResult.launch(intent);
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
    public String getPath(Uri uri) {

        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(this, uri, proj, null, null, null);

        Cursor cursor = cursorLoader.loadInBackground();
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();
        return cursor.getString(index);
    }


}
