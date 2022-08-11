package com.example.friendsbee;

import static java.lang.Integer.parseInt;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;


public class CreateFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Button createButton;    // 요청서 작성 버튼
    private FirebaseDatabase mDatabase;    // 데이터베이스 레퍼런스 객체 생성
    EditText titlebox, placebox, contentsbox, datebox, pricebox;    // 제목, 장소, 요청 사항, 날짜, 친구비
    FirebaseUser user;
    Spinner spinnerCategory, spinnerHour, spinnerMin;   // 요청 항목 선택, 예상 소요 시간, 분
    TextView vDate;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private RadioGroup selectedGender;
    private String gender;
    String name;
    private DatabaseReference DatabaseRef;
    private String userName, purl;
    private int age;



    public CreateFragment() {

    }

    public static CreateFragment newInstance(String param1, String param2) {
        CreateFragment fragment = new CreateFragment();
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
        //initDatePicker();
        //dateButton.setText(getTodaysDate());
        //mDatabase = FirebaseDatabase.getInstance().getReference();  // 파이어베이스에 연결
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_create, container, false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        name = user.getUid();

        mDatabase = FirebaseDatabase.getInstance();
        DatabaseRef = mDatabase.getReference();

        LocalDate now = LocalDate.now();
        int nowYear = now.getYear();

        DatabaseRef.child("profile").child(name).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    Myprofile myprofile = task.getResult().getValue(Myprofile.class);
                    userName = myprofile.getName();
                    String birth = myprofile.getBirth_number();
                    String temp = birth.substring(0, 2);
                    if(parseInt(temp) < 22){
                        temp = "20" + temp.toString();
                    }
                    else{
                        temp = "19" + temp.toString();
                    }
                    age = nowYear - parseInt(temp);
                    purl = myprofile.getProfileImageUrl();
                }
            }
        });



        // 각 요소들 id 맵핑
        createButton = view.findViewById(R.id.create_button);
        createButton.setOnClickListener(this);
        titlebox = view.findViewById(R.id.request_title);
        placebox = view.findViewById(R.id.request_place);
        contentsbox = view.findViewById(R.id.request_contents);
        datebox = view.findViewById(R.id.request_date);
        pricebox = view.findViewById(R.id.request_price);
        spinnerCategory = view.findViewById(R.id.categorySpinner);
        spinnerHour = view.findViewById(R.id.hourSpinner);
        spinnerMin = view.findViewById(R.id.minSpinner);


        selectedGender = view.findViewById(R.id.gender_radiogroup);
        selectedGender.setOnCheckedChangeListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        final String title = titlebox.getText().toString();
        final String place = placebox.getText().toString();
        final String contents = contentsbox.getText().toString();
        final String date = datebox.getText().toString();
        final int price = parseInt(pricebox.getText().toString());
        final String category = spinnerCategory.getSelectedItem().toString();
        final int hour = parseInt(spinnerHour.getSelectedItem().toString().substring(0,2));
        final int min = parseInt(spinnerMin.getSelectedItem().toString().substring(0,2));

        if(view.getId() == R.id.create_button){ // 요청서 작성 버튼을 터치했을 경우

            if(title.length() > 0 && place.length() > 0){   // 필수 요소들 입력 확인
                RequestInfo requestInfo = new RequestInfo(title, place, contents, date, price, category, hour, min, userName, age, purl); // requestInfo 클래스에 지정해둔 데이터 저장 형태에 맞춰 생성
                DatabaseRef.child("requests").push().setValue(requestInfo);   // request 테이블에 생성한 요청서를 등록
                Toast.makeText(getContext(), "등록 성공", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).menu.findItem(R.id.home).setIcon(R.drawable.home_icon);
                ((MainActivity)getActivity()).binding.bottomNavigationView.setSelectedItemId(R.id.recipt);
                //((MainActivity)getActivity()).title.setText("요청서 작성");
                ((MainActivity)getActivity()).replaceFragment(new ReciptFragment());

            }else{
                Toast.makeText(getContext(), "다시 입력해 주세요", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch(i){
            case R.id.radiobutton_none:
                gender = "없음";
                break;
            case R.id.radiobutton_male:
                gender = "남자";
                break;
            case R.id.radiobutton_female:
                gender = "여자";
                break;
        }
    }
}