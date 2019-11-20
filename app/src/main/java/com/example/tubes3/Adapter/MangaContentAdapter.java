package com.example.tubes3.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes3.R;

import java.util.ArrayList;
import java.util.List;

public class MangaContentAdapter extends RecyclerView.Adapter<MangaContentAdapter.MyViewHolder> {
    private List<String> listOfMangaContent;
    public MangaContentAdapter(){
        this.listOfMangaContent = new ArrayList<String>();
    }

    @NonNull
    @Override
    public MangaContentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manga_content_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv.setText(listOfMangaContent.get(position));
    }


    @Override
    public int getItemCount() {
        return this.listOfMangaContent.size();
    }
    public void update(List<String> listOfMangaContent){
        this.listOfMangaContent.clear();
        this.listOfMangaContent.addAll(listOfMangaContent);
        this.notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView im;
        private TextView tv;//dummy
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.image_view);
        }
    }
}
