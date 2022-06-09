package com.example.friendsbee;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReciptAdapter extends RecyclerView.Adapter<ReciptAdapter.Holder> {

    private Context context;
    private List<ReciptItem> list = new ArrayList<>();


    public ReciptAdapter(Context context, List<ReciptItem> list) {
        this.context = context;
        this.list = list;
    }

    // ViewHolder 생성
    // row layout을 화면에 뿌려주고 holder에 연결
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipt, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    /*
     * Todo 만들어진 ViewHolder에 data 삽입 ListView의 getView와 동일
     *
     * */
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        // 각 위치에 문자열 세팅
        int itemposition = position;
        holder.title.setText(list.get(itemposition).title);
        holder.time.setText(list.get(itemposition).time);
        holder.userName.setText(list.get(itemposition).userName);
        holder.place.setText(list.get(itemposition).place);
        holder.price.setText(list.get(itemposition).price);
        holder.status.setText(list.get(itemposition).status);

        Log.e("StudyApp", "onBindViewHolder" + itemposition);
    }


    // 몇개의 데이터를 리스트로 뿌려줘야하는지 반드시 정의해줘야한다
    @Override
    public int getItemCount() {
        return list.size(); // RecyclerView의 size return
    }

    // ViewHolder는 하나의 View를 보존하는 역할을 한다
    public class Holder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView time;
        public TextView userName;
        public TextView place;
        public TextView price;
        public TextView status;


        public Holder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.recipt_title);
            time = (TextView) view.findViewById(R.id.recipt_time);
            userName = view.findViewById(R.id.recipt_user_name);
            place = view.findViewById(R.id.recipt_place);
            price = view.findViewById(R.id.recipt_price);
            status = view.findViewById(R.id.recipt_status);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        // 아무것도 없으면
                        //어댑터에 직접 리스너 인터페이스를 정의하고 액티비티 or 프래그먼트에서 해당 리스너 객체를 생성하고 어댑터에 전달해 호출되게 하는 것이다.
                    }
                    if (pos == 1){
                        Intent intent = new Intent(context, RequestActivity.class);
                        context.startActivity(intent);
                    }
                    else
                        Toast.makeText(context, "클릭이벤트", Toast.LENGTH_SHORT).show();
                }


            });
        }
    }
}

