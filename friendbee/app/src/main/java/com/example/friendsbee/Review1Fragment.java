package com.example.friendsbee;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Review1Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RecyclerView review1view;
    Review1Adapter adapter;


    public Review1Fragment() {

    }

    public static Review1Fragment newInstance(String param1, String param2) {
        Review1Fragment fragment = new Review1Fragment();
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
        View view = inflater.inflate(R.layout.fragment_review1, container, false);

        review1view = (RecyclerView)view.findViewById(R.id.review1_fragment_recyclerview);
        review1view.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Review1Model> options =
                new FirebaseRecyclerOptions.Builder<Review1Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("review"), Review1Model.class)
                        .build();
        adapter = new Review1Adapter(options);
        review1view.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}