package com.example.friendsbee;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.friendsbee.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.Holder> {

    private Context context;
    private ArrayList<Myprofile> users;
    private OnUserClickListener onUserClickListener;
    private int size;

    public ChatAdapter(Context context, ArrayList<Myprofile> users, OnUserClickListener onUserClickListener) {
        this.context = context;
        this.users = users;
        this.onUserClickListener = onUserClickListener;
    }

    interface OnUserClickListener{
        void onUserClicked(int position);
    }
    // ViewHolder 생성
    // row layout을 화면에 뿌려주고 holder에 연결
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        Holder holder = new Holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        // 각 위치에 문자열 세팅
        holder.title.setText(users.get(position).getNick_name());
        Glide.with(context).load(users.get(position).getProfileImageUrl()).error(R.drawable.blackstar_icon).placeholder(R.drawable.blackstar_icon).into(holder.imageView);
        //holder.lastMe.setText(myprofile.getNick_name());

    }


    // 몇개의 데이터를 리스트로 뿌려줘야하는지 반드시 정의해줘야한다
    @Override
    public int getItemCount() {
        return users.size(); // RecyclerView의 size return
    }

    // ViewHolder는 하나의 View를 보존하는 역할을 한다
    public class Holder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView lastMe;
        public ImageView imageView;

        public Holder(View view){
            super(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onUserClickListener.onUserClicked(getAdapterPosition());
                }
            });

            title = (TextView) view.findViewById(R.id.chat_textview_title);
            lastMe = (TextView) view.findViewById(R.id.chat_item_textview_lastmessage);
            imageView = view.findViewById(R.id.chat_item_imageview);
        }
    }



}

