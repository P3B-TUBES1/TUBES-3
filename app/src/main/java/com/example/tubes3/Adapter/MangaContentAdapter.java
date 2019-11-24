package com.example.tubes3.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes3.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


public class MangaContentAdapter extends RecyclerView.Adapter<MangaContentAdapter.MyViewHolder> {
    private String[] listOfMangaContent;
    private int viewWidth;
    protected final String BASE_URL= "https://cdn.mangaeden.com/mangasimg/";

    public MangaContentAdapter(int width,Bitmap temp){
        this.viewWidth = width;
        this.listOfMangaContent = new String[1];
    }
    public void cancelAll(){
    }
    @NonNull
    @Override
    public MangaContentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manga_content_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, int position) {
        holder.setIm(position);

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
        private int position;
        private Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                im.getLayoutParams().height = bitmap.getHeight();
                im.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.image_view);
        }
        public void setIm(int position){
            Picasso.get().load(BASE_URL+listOfMangaContent[position]).resize(viewWidth,0).into(target);
        }

    }
}
