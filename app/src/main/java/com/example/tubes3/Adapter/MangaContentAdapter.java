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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MangaContentAdapter extends RecyclerView.Adapter<MangaContentAdapter.MyViewHolder> {
    private List<String> listOfMangaContent;
    private int viewWidth;
    protected final String BASE_URL= "https://cdn.mangaeden.com/mangasimg/";
    public MangaContentAdapter(int width){
        this.viewWidth = width;
        Log.d("width",width+"");
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
        Picasso.get().load(BASE_URL+listOfMangaContent.get(position)).resize(this.viewWidth,0).into(holder.im);

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
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.image_view);
        }
    }
}
