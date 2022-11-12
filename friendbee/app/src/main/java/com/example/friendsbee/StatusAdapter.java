package com.example.friendsbee;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StatusAdapter extends FirebaseRecyclerAdapter<StatusModel, StatusAdapter.myviewholder> {

    StatusFragment statusFragment;
    String apply1;
    DatabaseReference mDatabase;
    Status_SubAdapter adapter;
    String name;


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

        FirebaseDatabase.getInstance().getReference().child("profile").child(model.getApply1()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Myprofile myprofile = snapshot.getValue(Myprofile.class);
                name = myprofile.getName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.change_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new MainActivity2()).addToBackStack(null).commit();
                // 위에 코드에 상태, 고유키 추가
            }
        });


        holder.delete_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.recyclerView.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        ArrayList<StatusSubModel> arrayList = new ArrayList<>();





        StatusSubModel subItem = new StatusSubModel(name);
        arrayList.add(subItem);

        adapter = new Status_SubAdapter(arrayList);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(adapter);

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
            recyclerView= itemView.findViewById(R.id.status_recyclerview1);



        }
    }


}

