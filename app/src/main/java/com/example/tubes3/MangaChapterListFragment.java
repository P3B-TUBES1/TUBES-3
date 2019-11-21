package com.example.tubes3;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes3.Adapter.AdapterMangaChapter;

public class MangaChapterListFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private IMainActivity iMainActivity;

    public static MangaChapterListFragment newInstance() {
        MangaChapterListFragment mclf = new MangaChapterListFragment();
        return mclf;
    }

    @SuppressLint("NewApi")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manga_chapter_list_page, container, false);
        RecyclerView rv = view.findViewById(R.id.rv_list_chapter);
        iMainActivity = (MainActivity) getContext();
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter rvAdapter = new AdapterMangaChapter(iMainActivity.getChapterArray());
        rv.setAdapter(rvAdapter);
        return view;
    }
}
