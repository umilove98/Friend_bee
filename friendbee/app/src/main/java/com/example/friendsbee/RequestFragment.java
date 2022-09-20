package com.example.friendsbee;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RequestFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String category, contents, date, place, title, hour, min, price, userName, age, purl, uniq_key;
    int condi, cnt;
    private Button apply, chat;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    ChatAdapter.OnUserClickListener onUserClickListener;
    private ChatAdapter usersAdapter;



    


    public RequestFragment() {

    }

    public RequestFragment(String category, String contents, String date, String hour, String min, String place, String title, String price, String userName, String age, String purl,int condi, String uniq_key) {
        this.category = category;
        this.contents = contents;
        this.date = date;
        this.hour = hour;
        this.min = min;
        this.place = place;
        this.title = title;
        this.price = price;
        this.userName = userName;
        this.age = age;
        this.purl = purl;
        this.condi = condi;
        this.uniq_key = uniq_key;
    }

    public static RequestFragment newInstance(String param1, String param2) {
        RequestFragment fragment = new RequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request, container, false);

        ImageView imageholder = view.findViewById(R.id.recipt_user_image);
        TextView titleholder = view.findViewById(R.id.request_form_title);
        TextView timeholder = view.findViewById(R.id.request_form_time);
        TextView placeholder = view.findViewById(R.id.request_form_place);
        TextView contentsholder = view.findViewById(R.id.request_form_contents);
        TextView priceholder = view.findViewById(R.id.request_form_price);
        TextView nameholder = view.findViewById(R.id.request_form_nickname);
        TextView ageholder = view.findViewById(R.id.request_form_age);
        apply = view.findViewById(R.id.request_form_apply_btn);
        //check = view.findViewById(R.id.nickName_button);
        chat = view.findViewById(R.id.request_form_chat_btn);


        titleholder.setText(title);
        timeholder.setText(hour + "시 " + min + "분");
        placeholder.setText(place);
        contentsholder.setText(contents);
        priceholder.setText(price);
        nameholder.setText(userName);
        ageholder.setText(age);
        Glide.with(getContext()).load(purl).into(imageholder);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        cnt = 1;
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 고유키로 찾은다음에 데이터 베이스 현재 계정 불러와서 uid 데이터베이스에 넣기
                String cur_uid = firebaseAuth.getCurrentUser().getUid();
                mDatabase.child("requests").child(uniq_key).child("apply"+ cnt).setValue(cur_uid);
                cnt++;
                Toast.makeText(getContext(), "지원되었습니다" , Toast.LENGTH_SHORT).show();

                //AppCompatActivity activity=(AppCompatActivity) view.getContext();
                //activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new StatusFragment(category, contents, date, place, title, hour, min, price, userName, age, purl, condi,uniq_key)).addToBackStack(null).commit();


            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getContext(), MessageActivity.class));
                Toast.makeText(getContext(), "채팅방을 확인해주세요" , Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


}