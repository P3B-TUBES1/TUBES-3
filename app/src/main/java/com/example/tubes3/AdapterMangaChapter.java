package com.example.tubes3;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes3.model.Manga;

public class AdapterMangaChapter extends RecyclerView.Adapter{
    private Manga[] listManga;
    private MyViewHolder holder;

    public AdapterMangaChapter(Manga[] listManga) {
        this.listManga = listManga;
    }

    @NonNull
    @Override
    public AdapterMangaChapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Manga manga = listManga[position];
        holder.img.setImageBitmap(manga.img);
        holder.tvTitle.setText(manga.title);
        holder.tvReleaseDate.setText(manga.releaseDate);
        holder.tvIndex.setText(manga.index);
    }



    @Override
    public int getItemCount() {
        return listManga.length;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        protected ImageView img;
        protected TextView tvTitle;
        protected TextView tvReleaseDate;
        protected TextView tvIndex;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_thumbnail);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvReleaseDate = itemView.findViewById(R.id.tv_releaseDate);
            tvIndex = itemView.findViewById(R.id.tv_index);
        }
    }
}
