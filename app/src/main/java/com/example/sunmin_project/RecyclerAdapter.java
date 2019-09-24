package com.example.sunmin_project;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {
    private ArrayList<Data> listData = new ArrayList<>();

    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ViewGroup parent = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        //item을 하나하나 보여주는(bind 되는) 함수
        itemViewHolder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        // recyclerview의 총 개수
        return listData.size();
    }

    void addItem(Data data) {
        // 외부에서 item을 추가시킬 함수
        listData.add(data);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView text_num;
        private TextView text_title;
        private TextView text_director;
        private TextView text_date;
        private TextView text_score;

        ItemViewHolder(View itemView) {
            super(itemView);

            text_num=itemView.findViewById(R.id.text_num);
            text_title=itemView.findViewById(R.id.text_title);
            text_director=itemView.findViewById(R.id.text_director);
            text_date=itemView.findViewById(R.id.text_date);
            text_score=itemView.findViewById(R.id.text_score);
        }

        void onBind(Data data){
            text_num.setText(data.getTitle());
            text_title.setText(data.getTitle());
            text_director.setText(data.getDirector());
            text_date.setText(data.getDate());
            text_score.setText(data.getScore());
        }
    }
}
