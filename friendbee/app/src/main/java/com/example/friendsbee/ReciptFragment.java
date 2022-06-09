package com.example.friendsbee;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReciptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReciptFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private ArrayList<ReciptItem> list = new ArrayList<ReciptItem>();
    private ReciptAdapter adapter;
    private FirebaseDatabase mDatabase;
    private DatabaseReference DatabaseRef;

    public ReciptFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReciptFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipt, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recipt_fragment_recyclerview);

        list = ReciptItem.createContactsList(5);

        recyclerView.setHasFixedSize(true);
        adapter = new ReciptAdapter(getActivity(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        mDatabase = FirebaseDatabase.getInstance();
        DatabaseRef = mDatabase.getReference();

        //값을 받아서 보음
        /*DatabaseRef.child("requests").child("-N2Un7maER8gbqY9Hq4P").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    ReciptItem reciptItem = task.getResult().getValue(ReciptItem.class);
                    //reciptItem.gethour();
                    Intent intent = new Intent();

                }
            }
        });*/


        Log.e("Frag", "MainFragment");
        return view;

    }
}