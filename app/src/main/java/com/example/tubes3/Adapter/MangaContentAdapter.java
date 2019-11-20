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

public class MangaContentAdapter extends RecyclerView.Adapter<MangaContentAdapter.MyViewHolder> {
    private String[] n;
    public MangaContentAdapter(){
        String[] t= {"9a/9a1bece0638af100ac8f6c92cf922a940d0f2e00795646bbb909cc77.jpg","71/7196c1e8d858991e9962d5b1d44dc1bc2c47577a7b8ab7c08c933799.jpg",
                "fd/fd7dda0798659f9ba3c48e779ac603af62b5c48b8f9c90e007cdfcbe.jpg"}; //dummy data
        this.n = t;
        Log.d("test",n.length+"");
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
        holder.tv.setText(n[position]);
    }


    @Override
    public int getItemCount() {
        return this.n.length;
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
