package com.example.friendsbee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.friendsbee.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    BottomNavigationView bottomNavigationView;
    Menu menu;
    Menu menu1;
    Toolbar myToolbar; // 상단

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        myToolbar = findViewById(R.id.toolbar);

        menu = bottomNavigationView.getMenu();


        binding.bottomNavigationView.setOnItemSelectedListener(item ->{

            switch(item.getItemId()){
                case R.id.home:
                    item.setIcon(R.drawable.selected_home_icon);
                    menu.findItem(R.id.recipt).setIcon(R.drawable.recipt_icon);
                    menu.findItem(R.id.create).setIcon(R.drawable.create_icon);
                    menu.findItem(R.id.chat).setIcon(R.drawable.chat_icon);
                    menu.findItem(R.id.mypage).setIcon(R.drawable.mypage_icon);
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.recipt:
                    item.setIcon(R.drawable.selected_recipt_icon);
                    menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                    menu.findItem(R.id.create).setIcon(R.drawable.create_icon);
                    menu.findItem(R.id.chat).setIcon(R.drawable.chat_icon);
                    menu.findItem(R.id.mypage).setIcon(R.drawable.mypage_icon);
                    replaceFragment((new ReciptFragment()));
                    break;
                case R.id.create:
                    item.setIcon(R.drawable.selected_create_icon);
                    menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                    menu.findItem(R.id.recipt).setIcon(R.drawable.recipt_icon);
                    menu.findItem(R.id.chat).setIcon(R.drawable.chat_icon);
                    menu.findItem(R.id.mypage).setIcon(R.drawable.mypage_icon);
                    replaceFragment((new CreateFragment()));
                    break;
                case R.id.chat:
                    item.setIcon(R.drawable.selected_chat_icon);
                    menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                    menu.findItem(R.id.recipt).setIcon(R.drawable.recipt_icon);
                    menu.findItem(R.id.create).setIcon(R.drawable.create_icon);
                    menu.findItem(R.id.mypage).setIcon(R.drawable.mypage_icon);
                    replaceFragment((new ChatFragment()));
                    break;
                case R.id.mypage:
                    item.setIcon(R.drawable.selected_mypage_icon);
                    menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                    menu.findItem(R.id.recipt).setIcon(R.drawable.recipt_icon);
                    menu.findItem(R.id.create).setIcon(R.drawable.create_icon);
                    menu.findItem(R.id.chat).setIcon(R.drawable.chat_icon);
                    replaceFragment((new MypageFragment()));
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


}
