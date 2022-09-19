package com.example.friendsbee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Status_SubAdapter extends FirebaseRecyclerAdapter<StatusModel, Status_SubAdapter.myviewholder> {


    public Status_SubAdapter(@NonNull FirebaseRecyclerOptions<StatusModel> options, String apply1) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull StatusModel model) {

        holder.userName.setText(model.getUserName());
        Glide.with(holder.userImg.getContext()).load(model.getPurl()).into(holder.userImg);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_status,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder{
        ImageView userImg;
        TextView userName;
        Button change_b, delete_b;
        //여기서 객체 선언


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            userImg = itemView.findViewById(R.id.imageButton5);
            userName = itemView.findViewById(R.id.textView40);
            change_b = itemView.findViewById(R.id.button5);
            delete_b = itemView.findViewById(R.id.button6);

        }
    }
}

