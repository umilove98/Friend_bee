package com.example.friendsbee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        //holder.review.setText(model.getReview());
        //holder.rating.setRating(model.getRating());
        holder.date.setText(model.getDate());
        holder.userName.setText(model.getUserName());
        holder.age.setText(model.getAge());
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
        TextView review, date, userName, age;
        RatingBar rating;
        //여기서 객체 선언


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            // findviewbyid 로 객체 연결
            userImg = itemView.findViewById(R.id.reveiw1_user_image);
            review = itemView.findViewById(R.id.review1_contents);
            //rating = itemView.findViewById(R.id.review1_ratingBar);
            date = itemView.findViewById(R.id.review1_date_time);
            userName = itemView.findViewById(R.id.review1_user_name);
            age = itemView.findViewById(R.id.review1_age_gender);

        }
    }
}

