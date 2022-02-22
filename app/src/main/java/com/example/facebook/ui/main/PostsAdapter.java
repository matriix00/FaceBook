package com.example.facebook.ui.main;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebook.R;
import com.example.facebook.pojo.PostModel;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {
    private List<PostModel>postsList = new ArrayList<>();
    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        holder.titleTV.setText(postsList.get(position).getTitle());
        holder.bodyTv.setText(postsList.get(position).getBody());
        holder.idTv.setText(postsList.get(position).getId()+"");
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }
    public void setList(List<PostModel> postsList){
        this.postsList=postsList;
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder{
         TextView titleTV,idTv,bodyTv;
        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.title_tv);
            idTv = itemView.findViewById(R.id.id_tv);
            bodyTv = itemView.findViewById(R.id.body_tv);
        }
    }
}
