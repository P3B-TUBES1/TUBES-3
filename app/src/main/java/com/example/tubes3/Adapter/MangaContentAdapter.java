package com.example.tubes3.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes3.IMainActivity;
import com.example.tubes3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MangaContentAdapter extends RecyclerView.Adapter<MangaContentAdapter.MyViewHolder> {
    private String[] listOfMangaContent;
    private int viewWidth;
    protected final String BASE_URL= "https://cdn.mangaeden.com/mangasimg/";
    public MangaContentAdapter(int width){
        this.viewWidth = width;
        this.listOfMangaContent = new String[1];
    }

    @NonNull
    @Override
    public MangaContentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manga_content_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Picasso.get().load(BASE_URL+listOfMangaContent[position]).
                resize(this.viewWidth,0).into(holder.im);

    }


    @Override
    public int getItemCount() {
        return this.listOfMangaContent.length;
    }
    public void update(String[] listOfMangaContent){
        this.listOfMangaContent = listOfMangaContent;
        this.notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView im;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.image_view);

        }

    }
}
