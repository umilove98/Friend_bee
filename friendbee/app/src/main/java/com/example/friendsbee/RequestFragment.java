package com.example.friendsbee;

import static java.lang.Integer.parseInt;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RequestFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String category, contents, date, place, title, hour, min, price, userName, age, purl;

    public RequestFragment() {

    }

    public RequestFragment(String category, String contents, String date, String hour, String min, String place, String title, String price, String userName, String age, String purl) {
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


        titleholder.setText(title);
        timeholder.setText(hour + "시 " + min + "분");
        placeholder.setText(place);
        contentsholder.setText(contents);
        priceholder.setText(price);
        nameholder.setText(userName);
        ageholder.setText(age);
        Glide.with(getContext()).load(purl).into(imageholder);


        return view;
    }

    public void onBackPressed(){
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new ReciptFragment()).addToBackStack(null).commit();
    }
}