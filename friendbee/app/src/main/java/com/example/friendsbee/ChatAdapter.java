package com.example.friendsbee;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.Holder> {

    private Context context;
    private static OnItemClickListener itemClickListener;
    private List<WordItem> list = new ArrayList<>();

    public interface OnItemClickListener {
        //클릭시 동작할 함수
        void onItemClick(View v, int pos);
    }

    public static void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }

    public ChatAdapter(Context context, List<WordItem> list) {
        this.context = context;
        this.list = list;
    }

    // ViewHolder 생성
    // row layout을 화면에 뿌려주고 holder에 연결
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
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
        holder.wordText.setText(list.get(itemposition).word);
        holder.meaningText.setText(list.get(itemposition).meaning);
        Log.e("StudyApp", "onBindViewHolder" + itemposition);
    }


    // 몇개의 데이터를 리스트로 뿌려줘야하는지 반드시 정의해줘야한다
    @Override
    public int getItemCount() {
        return list.size(); // RecyclerView의 size return
    }

    // ViewHolder는 하나의 View를 보존하는 역할을 한다
    public class Holder extends RecyclerView.ViewHolder {
        public TextView wordText;
        public TextView meaningText;

        public Holder(View view) {
            super(view);
<<<<<<< Updated upstream
                wordText = (TextView) view.findViewById(R.id.chat_textview_title);
                meaningText = (TextView) view.findViewById(R.id.chat_item_textview_lastmessage);
=======
            wordText = (TextView) view.findViewById(R.id.chat_textview_title);
            meaningText = (TextView) view.findViewById(R.id.chat_item_textview_lastmessage);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {
                        //동작 호출 (onItemClick 함수 호출)
                        if (itemClickListener != null) {
                            itemClickListener.onItemClick(view, pos);
                        }
                    }
                }

            });
>>>>>>> Stashed changes
        }
    }
}

<<<<<<< Updated upstream
=======


>>>>>>> Stashed changes
