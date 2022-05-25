package com.example.friendsbee;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.DatePicker;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button createButton;    // 요청서 작성 버튼
    private DatabaseReference mDatabase;    // 데이터베이스 레퍼런스 객체 생성
    EditText titlebox, placebox, contentsbox, datebox, pricebox;    // 제목, 장소, 요청 사항, 날짜, 친구비
    FirebaseUser user;
    Spinner spinnerCategory, spinnerHour, spinnerMin;   // 요청 항목 선택, 예상 소요 시간, 분
    TextView vDate;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private RadioGroup selectedGender;
    private String gender;



    public CreateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        mDatabase = FirebaseDatabase.getInstance().getReference();  // 파이어베이스에 연결

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_create, container, false);

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
        vDate = view.findViewById(R.id.editDate);
        vDate.setOnClickListener(this);
        dateButton = view.findViewById(R.id.date_picker_button);
        dateButton.setOnClickListener(this);
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
        final int price = Integer.parseInt(pricebox.getText().toString());
        final String category = spinnerCategory.getSelectedItem().toString();
        final int hour = Integer.parseInt(spinnerHour.getSelectedItem().toString().substring(0,2));
        final int min = Integer.parseInt(spinnerMin.getSelectedItem().toString().substring(0,2));

        if(view.getId() == R.id.create_button){ // 요청서 작성 버튼을 터치했을 경우

            if(title.length() > 0 && place.length() > 0){   // 필수 요소들 입력 확인
                RequestInfo requestInfo = new RequestInfo(title, place, contents, date, price, category, hour, min); // requestInfo 클래스에 지정해둔 데이터 저장 형태에 맞춰 생성
                mDatabase.child("requests").push().setValue(requestInfo);   // request 테이블에 생성한 요청서를 등록
                Toast.makeText(getContext(), "등록 성공", Toast.LENGTH_SHORT).show();

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