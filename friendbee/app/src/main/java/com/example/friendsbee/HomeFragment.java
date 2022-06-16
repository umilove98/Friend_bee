package com.example.friendsbee;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button Eating, cafe, walking, game, exer, study, sing, talk;
    private Spinner spinnerCategory;
    private ImageView HomeBanner;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        View view2 = inflater.inflate(R.layout.fragment_create, container, false);
        spinnerCategory = view2.findViewById(R.id.categorySpinner);

        Eating = view.findViewById(R.id.homeBtn1);
        Eating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                ((MainActivity)getActivity()).binding.bottomNavigationView.setSelectedItemId(R.id.recipt);
                //((MainActivity)getActivity()).title.setText("이용내역");
                //String str = spinnerCategory.getItemAtPosition(1).toString();
                spinnerCategory.setSelection(1);
                //Toast.makeText(getContext(),str,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),request_list.class);
                startActivity(intent);
            }
        });

        cafe = view.findViewById(R.id.homeBtn2);
        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                ((MainActivity)getActivity()).binding.bottomNavigationView.setSelectedItemId(R.id.create);
                //((MainActivity)getActivity()).title.setText("요청서 작성");

                ((MainActivity)getActivity()).replaceFragment(new CreateFragment());
            }
        });

        walking = view.findViewById(R.id.homeBtn3);
        walking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                ((MainActivity)getActivity()).binding.bottomNavigationView.setSelectedItemId(R.id.create);
                ((MainActivity)getActivity()).title.setText("요청서 작성");
                ((MainActivity)getActivity()).replaceFragment(new CreateFragment());
            }
        });

        game = view.findViewById(R.id.homeBtn4);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                ((MainActivity)getActivity()).binding.bottomNavigationView.setSelectedItemId(R.id.create);
                ((MainActivity)getActivity()).title.setText("요청서 작성");
                ((MainActivity)getActivity()).replaceFragment(new CreateFragment());
            }
        });

        exer = view.findViewById(R.id.homeBtn5);
        exer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                ((MainActivity)getActivity()).binding.bottomNavigationView.setSelectedItemId(R.id.create);
                ((MainActivity)getActivity()).title.setText("요청서 작성");
                ((MainActivity)getActivity()).replaceFragment(new CreateFragment());
            }
        });

        study = view.findViewById(R.id.homeBtn6);
        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                ((MainActivity)getActivity()).binding.bottomNavigationView.setSelectedItemId(R.id.create);
                ((MainActivity)getActivity()).title.setText("요청서 작성");
                ((MainActivity)getActivity()).replaceFragment(new CreateFragment());
            }
        });

        sing = view.findViewById(R.id.homeBtn7);
        sing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                ((MainActivity)getActivity()).binding.bottomNavigationView.setSelectedItemId(R.id.create);
                ((MainActivity)getActivity()).title.setText("요청서 작성");
                ((MainActivity)getActivity()).replaceFragment(new CreateFragment());
            }
        });

        talk = view.findViewById(R.id.homeBtn8);
        talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                ((MainActivity)getActivity()).binding.bottomNavigationView.setSelectedItemId(R.id.create);
                ((MainActivity)getActivity()).title.setText("요청서 작성");
                ((MainActivity)getActivity()).replaceFragment(new CreateFragment());
            }
        });

        HomeBanner = view.findViewById(R.id.HomeBanner);
        HomeBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                String url = "http://www.naver.com";
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });



        return view;
    }

}