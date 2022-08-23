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

import com.bumptech.glide.Glide;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText editTextPhoneNumber;   // 전화번호 입력 필드
    private EditText editTextVer;   // 인증번호 입력 필드
    private Button nick_button; // 닉네임 중복 확인 버튼
    private Button regist_button;   // 회원가입 하기 버튼
    private Button ver_button;  // 전화번호 인증하기 버튼
    private ImageView img_button;   // 프로필 사진 등록 +버튼
    private EditText editTextName;  // 이름 입력 필드
    private EditText editTextNick_Name; //닉네임 입력 필드
    private EditText editTextBirth_Number;  // 주민등록번호 입력 필드
    private Uri imageUri;
    private String pathUri;
    private String uid;
    private String profileImageUrl;

    
    private String s; // otp코드 저장
    private FirebaseDatabase mDatabase;
    private FirebaseStorage mStorage;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private ProgressDialog pd;
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;


    String name;
    String nick_name;
    String birth_number;
    String phone_number;
    String coin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        firebaseAuth = FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        editTextName = findViewById(R.id.signName);
        editTextNick_Name = findViewById(R.id.nickName);
        editTextBirth_Number = findViewById(R.id.R_R_Number);
        editTextPhoneNumber = (EditText) findViewById(R.id.phone_Number);
        editTextVer = (EditText) findViewById(R.id.cerTi);
        img_button = findViewById(R.id.pro_Img);
        nick_button = findViewById(R.id.nickName_button);


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
                    name = editTextName.getText().toString();
                    nick_name = editTextNick_Name.getText().toString();
                    birth_number = editTextBirth_Number.getText().toString();
                    phone_number = editTextPhoneNumber.getText().toString();
                    coin = "0";
                    verifyPhoneNumberWithCode(s, code);
                    Toast.makeText(RegisterActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
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

        nick_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                Toast.makeText(RegisterActivity.this,"name: " + name, Toast.LENGTH_SHORT).show();
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

                        uid = authResult.getUser().getUid();
                        String uid_code = uid.toString();
                        Uri file = Uri.fromFile(new File(pathUri)); // path

                        StorageReference storageReference = mStorage.getReference().child("usersprofileImages").child("uid/"+file.getLastPathSegment());
                        storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                final Task<Uri> imageUrl = task.getResult().getStorage().getDownloadUrl();
                                while (!imageUrl.isComplete()) ;
                                profileImageUrl = imageUrl.getResult().toString();


                                Myprofile myprofile = new Myprofile(name, nick_name, phone_number, birth_number,profileImageUrl, uid, coin);
                                mDatabase.getReference().child("profile").child(uid_code)
                                        .setValue(myprofile);
                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterActivity.this,"프로필 저장 실패 ", Toast.LENGTH_SHORT).show();
                                    }
                                });

                        pd.dismiss();
                        Toast.makeText(RegisterActivity.this,"hi " + uid, Toast.LENGTH_SHORT).show();
                        String phone = firebaseAuth.getCurrentUser().getPhoneNumber();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(RegisterActivity.this,"회원가입 실패", Toast.LENGTH_SHORT).show();
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
