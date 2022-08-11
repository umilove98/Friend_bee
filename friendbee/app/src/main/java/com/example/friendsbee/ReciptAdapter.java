package com.example.friendsbee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ReciptAdapter extends FirebaseRecyclerAdapter<ReciptModel,ReciptAdapter.myviewholder> {


    public ReciptAdapter(@NonNull FirebaseRecyclerOptions<ReciptModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull ReciptModel model) {
        holder.recipt_title.setText(model.getTitle());
        holder.recipt_time.setText(model.getHour());
        holder.recipt_place.setText(model.getPlace());
        holder.recipt_price.setText(model.getPrice());
        holder.recipt_username.setText(model.getUserName());
        Glide.with(holder.userImg.getContext()).load(model.getPurl()).into(holder.userImg);

                holder.recipt_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppCompatActivity activity=(AppCompatActivity) view.getContext();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new RequestFragment(model.getCategory(), model.getContents(), model.getDate(), model.getHour(), model.getMin(), model.getPlace(), model.getTitle(), model.getPrice(), model.getUserName(), model.getAge(), model.getPurl())).addToBackStack(null).commit();

                    }
                });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipt,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder{
        ImageView userImg;
        TextView recipt_title,recipt_time,recipt_place,recipt_price,recipt_username;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            userImg = itemView.findViewById(R.id.recipt_user_image);
            recipt_title = itemView.findViewById(R.id.recipt_title);
            recipt_time = itemView.findViewById(R.id.recipt_time);
            recipt_place = itemView.findViewById(R.id.recipt_place);
            recipt_price = itemView.findViewById(R.id.recipt_price);
            recipt_username = itemView.findViewById(R.id.recipt_user_name);

        }
    }
}

