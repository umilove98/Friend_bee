package com.example.friendsbee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class StatusAdapter extends FirebaseRecyclerAdapter<RequestInfo, StatusAdapter.myviewholder> {


    public StatusAdapter(@NonNull FirebaseRecyclerOptions<RequestInfo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull RequestInfo model) {
        holder.date.setText(model.getDate());
        holder.userName.setText(model.getUserName());
        holder.title.setText(model.getTitle());
        holder.coin.setText(model.getAge());
        holder.place.setText(model.getPlace());
        Glide.with(holder.userImg.getContext()).load(model.getPurl()).into(holder.userImg);

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
        //여기서 객체 선언


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            // findviewbyid 로 객체 연결
            date = itemView.findViewById(R.id.status_time);
            userImg = itemView.findViewById(R.id.status_user_image);
            userName = itemView.findViewById(R.id.status_user_name);
            change_b = itemView.findViewById(R.id.change_button);
            delete_b = itemView.findViewById(R.id.delete_button);
            //coin = itemView.findViewById(R.id.status_coin);
            //place = itemView.findViewById(R.id.status_place);
            //title = itemView.findViewById(R.id.status_title);


        }
    }
}

