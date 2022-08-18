package com.example.friendsbee;

import static java.lang.Integer.parseInt;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;

public class ReviewWriteFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText reviewbox;    // 리뷰 내용
    private String name;    // 작성자 uid
    private FirebaseDatabase mDatabase;
    private DatabaseReference DatabaseRef;
    private String userName, purl;  // 작성자 이름, 프로필 사진 주소
    private int age;    // 작성자 나이
    private RatingBar ratingbarbox;    // 별점
    private MaterialButton reviewbtn;   // 리뷰 작성 완료 버튼
    private String date;

    public ReviewWriteFragment() {

    }

    public static ReviewWriteFragment newInstance(String param1, String param2) {
        ReviewWriteFragment fragment = new ReviewWriteFragment();
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_review_write, container, false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        name = user.getUid();

        mDatabase = FirebaseDatabase.getInstance();
        DatabaseRef = mDatabase.getReference();

        LocalDate now = LocalDate.now();
        int nowYear = now.getYear();

        DatabaseRef.child("profile").child(name).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    Myprofile myprofile = task.getResult().getValue(Myprofile.class);
                    userName = myprofile.getName();
                    String birth = myprofile.getBirth_number();
                    String temp = birth.substring(0, 2);
                    if(parseInt(temp) < 22){
                        temp = "20" + temp.toString();
                    }
                    else{
                        temp = "19" + temp.toString();
                    }
                    age = nowYear - parseInt(temp);
                    purl = myprofile.getProfileImageUrl();
                }
            }
        });

        reviewbox = view.findViewById(R.id.reviewwrite);
        ratingbarbox = view.findViewById(R.id.ratingBar);
        reviewbtn = view.findViewById(R.id.reviewbtn);
        reviewbtn.setOnClickListener(this);
        date = now.toString();
        return view;
    }

    @Override
    public void onClick(View view) {
        final String review = reviewbox.getText().toString();
        final float ratingbar = ratingbarbox.getRating();

        if(view.getId() == R.id.reviewbtn){
            ReviewInfo reviewInfo = new ReviewInfo(review, ratingbar, date, userName, age, purl);
            DatabaseRef.child("review").push().setValue(reviewInfo);
            Toast.makeText(getContext(), "리뷰 등록 성공", Toast.LENGTH_SHORT).show();
            ((MainActivity)getActivity()).binding.bottomNavigationView.setSelectedItemId(R.id.recipt);
            ((MainActivity)getActivity()).replaceFragment(new Review1Fragment());
        }

    }
}