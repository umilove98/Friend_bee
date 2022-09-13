package com.example.friendsbee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.friendsbee.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    BottomNavigationView bottomNavigationView;
    Menu menu;
    ImageView menuIcon;
    ImageView searchIcon;
    TextView title;
    DatabaseReference DatabaseRef;

    private Spinner spinner;
//  2. 학교 근처 에서 같이 식사를 한 후 학교 근처 카페에서 노트북으로 학원 수업을 듣는다. 3. 학교 끝나자마자 같이 학원에 간다

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        DatabaseRef = FirebaseDatabase.getInstance().getReference();
        spinner = findViewById(R.id.categorySpinner);


        menuIcon = findViewById(R.id.menu_icon);
        searchIcon = findViewById(R.id.search_icon);
        //title = findViewById(R.id.toolbar_title);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        menu = bottomNavigationView.getMenu();



        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "메뉴 클릭됨", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), FragmentHomeDetailMenuActivity.class);
                startActivity(intent);
            }
        });
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
                SearchView searchView = findViewById(R.id.search_view);
                searchView.setVisibility(View.VISIBLE);
                searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
                Toast.makeText(MainActivity.this, "검색 버튼 클릭됨", Toast.LENGTH_SHORT).show();
            }
        });

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{

            switch(item.getItemId()){
                case R.id.home:
                    item.setIcon(R.drawable.selected_home_icon);
                    menu.findItem(R.id.recipt).setIcon(R.drawable.recipt_icon);
                    menu.findItem(R.id.create).setIcon(R.drawable.create_icon);
                    menu.findItem(R.id.chat).setIcon(R.drawable.chat_icon);
                    menu.findItem(R.id.mypage).setIcon(R.drawable.mypage_icon);
                    //title.setText("홈");
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.recipt:
                    item.setIcon(R.drawable.selected_recipt_icon);
                    menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                    menu.findItem(R.id.create).setIcon(R.drawable.create_icon);
                    menu.findItem(R.id.chat).setIcon(R.drawable.chat_icon);
                    menu.findItem(R.id.mypage).setIcon(R.drawable.mypage_icon);
                    //title.setText("이용내역");
                    replaceFragment((new ReciptFragment()));
                    break;
                case R.id.create:
                    item.setIcon(R.drawable.selected_create_icon);
                    menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                    menu.findItem(R.id.recipt).setIcon(R.drawable.recipt_icon);
                    menu.findItem(R.id.chat).setIcon(R.drawable.chat_icon);
                    menu.findItem(R.id.mypage).setIcon(R.drawable.mypage_icon);
                    //title.setText("요청서 작성");
                    replaceFragment((new CreateFragment()));
                    break;
                case R.id.chat:
                    item.setIcon(R.drawable.selected_chat_icon);
                    menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                    menu.findItem(R.id.recipt).setIcon(R.drawable.recipt_icon);
                    menu.findItem(R.id.create).setIcon(R.drawable.create_icon);
                    menu.findItem(R.id.mypage).setIcon(R.drawable.mypage_icon);
                    //title.setText("채팅");
                    replaceFragment((new ChatFragment()));
                    break;
                case R.id.mypage:
                    item.setIcon(R.drawable.selected_mypage_icon);
                    menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                    menu.findItem(R.id.recipt).setIcon(R.drawable.recipt_icon);
                    menu.findItem(R.id.create).setIcon(R.drawable.create_icon);
                    menu.findItem(R.id.chat).setIcon(R.drawable.chat_icon);
                    //title.setText("마이페이지");
                    replaceFragment((new ReviewWriteFragment()));
                    break;
            }

            return true;
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


}
