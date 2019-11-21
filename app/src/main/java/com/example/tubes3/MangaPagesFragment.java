package com.example.tubes3;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes3.Adapter.MangaContentAdapter;

import java.util.List;

public class MangaPagesFragment extends Fragment {


    private RecyclerView mangaContentRC;
    private IMainActivity ui;
    private ImageView back_button;
    private ImageView previous_chapter;
    private ImageView next_chapter;
    private EditText chapterNumber;
    private MangaContentAdapter mangaContentAdapter;
    public static MangaPagesFragment newInstance(){
        return new MangaPagesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manga_content,container,false);
        this.mangaContentRC = view.findViewById(R.id.manga_content_recycler_view);
        mangaContentRC.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mangaContentRC.setLayoutManager(layoutManager);
        mangaContentAdapter = new MangaContentAdapter();
        mangaContentRC.setAdapter(mangaContentAdapter);
        this.ui = (MainActivity)getContext();

        this.back_button = view.findViewById(R.id.back_button);
        this.previous_chapter = view.findViewById(R.id.previous_chapter);
        this.next_chapter  =view.findViewById(R.id.next_chapter);
        this.chapterNumber = view.findViewById(R.id.chapter_number);
        mangaContentRC.setScaleX(1f);
        mangaContentRC.setScaleY(1f);
        return view;
    }
    public void update(List<String> listOfMangaContent){
        this.mangaContentAdapter.update(listOfMangaContent);
    }

}
