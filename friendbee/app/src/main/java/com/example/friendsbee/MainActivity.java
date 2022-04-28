package com.example.friendsbee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.friendsbee.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{

            switch(item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.recipt:
                    replaceFragment((new ReciptFragment()));
                    break;
                case R.id.create:
                    replaceFragment((new CreateFragment()));
                    break;
                case R.id.chat:
                    replaceFragment((new ChatFragment()));
                    break;
                case R.id.mypage:
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
