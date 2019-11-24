package com.example.tubes3.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes3.R;

import com.example.tubes3.model.MangaChapterModel;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class AdapterMangaChapter extends RecyclerView.Adapter<AdapterMangaChapter.MyViewHolder> {
    private List<MangaChapterModel> listChapter;
    private final String BASE_URL = "https://cdn.mangaeden.com/mangasimg/";
    private Date date;

    public AdapterMangaChapter() {
        this.listChapter = new LinkedList<>();
    }

    public List<MangaChapterModel> getListChapter() {
        return listChapter;
    }

    @NonNull
    @Override
    public AdapterMangaChapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        this.date = new Date();
        //Collections.reverse(listChapter);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.get().load(BASE_URL+listChapter.get(position).getUrlImage()).into(holder.img);
        holder.tvTitle.setText("CH. " + (listChapter.size() - position) + " - " + listChapter.get(position).getChapterTitle());
        String releaseDate = listChapter.get(position).getChapterDate();
        long time = Long.parseLong(releaseDate);
        date.setTime(time);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvReleaseDate.setText(formatter.format(date));
        holder.tvIndex.setText("#"+(listChapter.size()-position));
    }

    public void update(List<MangaChapterModel> listOfMangaContent) {
        this.listChapter.clear();
        this.listChapter.addAll(listOfMangaContent);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listChapter.size();
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

