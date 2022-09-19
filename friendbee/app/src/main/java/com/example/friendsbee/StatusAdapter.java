package com.example.friendsbee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusAdapter extends FirebaseRecyclerAdapter<StatusModel, StatusAdapter.myviewholder> {

    StatusFragment statusFragment;
    FirebaseRecyclerOptions<StatusModel> options;
    String apply1;
    DatabaseReference mDatabase;

    public StatusAdapter(@NonNull FirebaseRecyclerOptions<StatusModel> options, StatusFragment statusFragment) {

        super(options);
        this.statusFragment = statusFragment;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull StatusModel model) {

        holder.date.setText(model.getDate());
        holder.userName.setText(model.getUserName());
       // holder.title.setText(model.getTitle());
       // holder.coin.setText(model.getAge());
      //  holder.place.setText(model.getPlace());
        holder.change_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new ReviewWriteFragment()).addToBackStack(null).commit();
                // 위에 코드에 상태, 고유키 추가
            }
        });

        holder.delete_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("requests").removeValue();
            }
        });

        Glide.with(holder.userImg.getContext()).load(model.getPurl()).into(holder.userImg);
        apply1 = model.getApply1();



    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_status,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder{
        ImageView userImg;
        TextView date, userName;
        Button change_b, delete_b, coin, place, title;
        RecyclerView recyclerView;


        //여기서 객체 선언


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            // findviewbyid 로 객체 연결
            date = itemView.findViewById(R.id.status_time);
            userImg = itemView.findViewById(R.id.status_user_image);
            userName = itemView.findViewById(R.id.status_user_name);
            change_b = itemView.findViewById(R.id.change_button);
            delete_b = itemView.findViewById(R.id.delete_button);
            recyclerView= itemView.findViewById(R.id.recycler_status);


            // 라사이클러뷰
            //coin = itemView.findViewById(R.id.status_coin);
            //place = itemView.findViewById(R.id.status_place);
           // title = itemView.findViewById(R.id.status_title);


        }
    }
}

