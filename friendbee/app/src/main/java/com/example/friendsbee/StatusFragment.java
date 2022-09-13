package com.example.friendsbee;

import static java.lang.Integer.parseInt;

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

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String category, contents, date, place, title, hour, min, price, userName, age, purl, uniq_key;
    int condi;

    RecyclerView review1view;
    StatusAdapter adapter;





    public StatusFragment() {

    }

    public StatusFragment(String category, String contents, String date, String hour, String min, String place, String title, String price, String userName, String age, String purl,int condi, String uniq_key) {
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

    public static StatusFragment newInstance(String param1, String param2) {
        StatusFragment fragment = new StatusFragment();
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
                             Bundle savedInstanceStatdnfle) {
        View view = inflater.inflate(R.layout.application_status_tes, container, false);


        /*review1view = (RecyclerView)view.findViewById(R.id.recycler_status);
        review1view.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<RequestInfo> options =
                new FirebaseRecyclerOptions.Builder<RequestInfo>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("requests"), RequestInfo.class)
                        .build();
        adapter = new StatusAdapter(options);
        review1view.setAdapter(adapter);*/


        return view;
    }

    /*@Override
    public void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }*/

}