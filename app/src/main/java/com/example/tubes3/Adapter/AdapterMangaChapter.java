package com.example.tubes3.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes3.R;
import com.example.tubes3.model.MangaChapterInfoModel;
import com.example.tubes3.model.MangaChapterModel;
import com.squareup.picasso.Picasso;

public class AdapterMangaChapter extends RecyclerView.Adapter<AdapterMangaChapter.MyViewHolder> {
    private MangaChapterModel[] listChapter;
    private MyViewHolder holder;
    private final String BASE_URL = "https://cdn.mangaeden.com/mangasimg/";

    public AdapterMangaChapter(MangaChapterModel[] listChapter) {
        this.listChapter = listChapter;
    }

    @NonNull
    @Override
    public AdapterMangaChapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.get().load(BASE_URL+listChapter[position].getUrlImage()).into(holder.img);
        holder.tvTitle.setText("CH. "+(position+1)+" - "+listChapter[position].getChapterTitle());
        holder.tvReleaseDate.setText(listChapter[position].getChapterDate());
        holder.tvIndex.setText(position+1+"");
    }


    @Override
    public int getItemCount() {
        return listChapter.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
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

