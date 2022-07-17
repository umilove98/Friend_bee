package com.example.friendsbee;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class RegisterFifth extends AppCompatActivity implements View.OnClickListener {

    private Uri imageUri;
    private String pathUri;
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

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                //result.getResultCode()를 통하여 결과값 확인
                if(result.getResultCode() == RESULT_OK) {
                    //ToDo
                    imageUri = result.getData().getData();
                    pathUri = getPath(result.getData().getData());
                    registerProfImgBtn.setImageURI(imageUri); // 이미지 띄움
                }
                if(result.getResultCode() == RESULT_CANCELED){
                    //ToDo
                }
            }
    );


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.registerProfImgBtn) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            mStartForResult.launch(intent);

        }

        if (view.getId() == R.id.finishBtn) {
            Intent intent02 = new Intent(RegisterFifth.this, LoginActivity.class);
            startActivity(intent02);
        }
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