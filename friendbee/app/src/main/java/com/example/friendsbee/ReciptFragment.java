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

public class ReciptFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RecyclerView reciptview;
    ReciptAdapter adapter;


    public ReciptFragment() {

    }

    public static ReciptFragment newInstance(String param1, String param2) {
        ReciptFragment fragment = new ReciptFragment();
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

        View view = inflater.inflate(R.layout.fragment_recipt, container, false);

        reciptview = (RecyclerView)view.findViewById(R.id.recipt_fragment_recyclerview);
        reciptview.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<ReciptModel> options =
                new FirebaseRecyclerOptions.Builder<ReciptModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("requests"), ReciptModel.class)
                        .build();

        adapter = new ReciptAdapter(options);
        reciptview.setAdapter(adapter);

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