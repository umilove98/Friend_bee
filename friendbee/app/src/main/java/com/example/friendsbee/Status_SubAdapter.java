package com.example.friendsbee;

import android.content.Context;
import android.util.Log;
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
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Status_SubAdapter extends RecyclerView.Adapter<Status_SubAdapter.myviewholder> {


    private ArrayList<StatusSubModel> arrayList;


    public Status_SubAdapter(ArrayList<StatusSubModel> arrayList) {
        this.arrayList = arrayList;
    }



    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_status,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        StatusSubModel statusSubModel = arrayList.get(position);
        holder.title.setText(statusSubModel.getTitle());
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder{
        TextView title;
        //여기서 객체 선언


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView40);

        }
    }
}

