package com.example.friendsbee;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URI;
import java.util.ArrayList;


public class MainActivity2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseDatabase mDatabase;
    private DatabaseReference DatabaseRef;
    private TextView text_name, text_age, review_view, gusghkd;
    private String name;
    private ImageView img;
    private Button button;

    public MainActivity2() {
        // Required empty public constructor

    }

    public static MainActivity2 newInstance(String param1, String param2) {
        MainActivity2 fragment = new MainActivity2();
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

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_recipt2, container, false);

        text_name = view.findViewById(R.id.textView4);
        text_age = view.findViewById(R.id.textView5);
        img = view.findViewById(R.id.imageView);
        review_view = view.findViewById(R.id.textView22);
        gusghkd = view.findViewById(R.id.textView24);
        button = view.findViewById(R.id.go_to_review);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        name = user.getUid();


        mDatabase = FirebaseDatabase.getInstance();
        DatabaseRef = mDatabase.getReference();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new ReviewWriteFragment()).addToBackStack(null).commit();
                // 위에 코드에 상태, 고유키 추가
            }
        });




        return view;
    }


}