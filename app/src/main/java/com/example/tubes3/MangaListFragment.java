package com.example.tubes3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MangaListFragment extends Fragment {
    private Presenter presenter;

    public static MangaListFragment newInstance() {
        MangaListFragment fragment = new MangaListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manga_list_page, container, false);
        return view;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
