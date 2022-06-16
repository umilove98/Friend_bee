package com.example.friendsbee;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;


public class ChatRoomActivity2 extends Activity {
    private ArrayList<DataItem> dataList;
    String s;
    ImageButton btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room2);   //나중에 바꾸기

        initData();

        RecyclerView recyvlerv = findViewById(R.id.recyvlerv);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyvlerv.setLayoutManager(manager);
        recyvlerv.setAdapter(new MyAdapter(dataList));
        EditText editText = findViewById(R.id.editText1);
        btn = findViewById(R.id.btn_send1);
        s = editText.getText().toString();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void initData(){
        dataList = new ArrayList<>();
        //dataList.add(new DataItem("시용자1님 입장했음",null,Code.ViewType.CENTER_CONTENT));
        //dataList.add(new DataItem("사용자2님 입장했음",null,Code.ViewType.CENTER_CONTENT));
        dataList.add(new DataItem("안녕하세요!!!","크롱",Code.ViewType.LEFT_CONTENT));
        dataList.add(new DataItem("반가워요~","사용자2",Code.ViewType.RIGHT_CONTENT));



    }
}
