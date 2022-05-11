package com.example.friendsbee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
<<<<<<< Updated upstream
=======
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
>>>>>>> Stashed changes

import com.example.friendsbee.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
<<<<<<< Updated upstream
=======
    BottomNavigationView bottomNavigationView;
    Menu menu;
    ImageView menuIcon;
    ImageView searchIcon;
    TextView title;
    private Spinner spinner;
>>>>>>> Stashed changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

<<<<<<< Updated upstream
=======
        spinner = findViewById(R.id.categorySpinner);

        menuIcon = findViewById(R.id.menu_icon);
        searchIcon = findViewById(R.id.search_icon);
        title = findViewById(R.id.toolbar_title);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        menu = bottomNavigationView.getMenu();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "메뉴 클릭됨", Toast.LENGTH_SHORT).show();
            }
        });
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "검색 버튼 클릭됨", Toast.LENGTH_SHORT).show();
            }
        });

>>>>>>> Stashed changes
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
