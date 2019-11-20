package com.example.tubes3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MangaPagesFragment extends Fragment {

    private ImageView im;
    private String[] mangaImageList;
    public static MangaPagesFragment newInstance(){
        return new MangaPagesFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manga_content,container,false);
        this.im = view.findViewById(R.id.image_view);
        return view;
    }

}
