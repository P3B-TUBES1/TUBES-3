package com.example.tubes3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes3.Adapter.MangaContentAdapter;

public class MangaPagesFragment extends Fragment {


    private String[] mangaImageList;
    private RecyclerView mangaContentRC;
    private MangaContentAdapter mangaContentAdapter;
    public static MangaPagesFragment newInstance(){
        return new MangaPagesFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manga_content,container,false);
        this.mangaContentRC = view.findViewById(R.id.manga_content_recycler_view);
        this.mangaContentAdapter = new MangaContentAdapter();
        mangaContentRC.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mangaContentRC.setLayoutManager(layoutManager);
        mangaContentAdapter = new MangaContentAdapter();
        mangaContentRC.setAdapter(mangaContentAdapter);
        return view;
    }

}
