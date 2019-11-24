package com.example.tubes3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes3.Adapter.AdapterMangaChapter;
import com.example.tubes3.model.MangaChapterInfoModel;
import com.example.tubes3.model.MangaChapterModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MangaChapterListFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterMangaChapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MangaChapterInfoModel mangaChapterInfoModel;
    private IMainActivity iMainActivity;
    ImageView img;
    private Presenter presenter;
    private final String BASE_URL = "https://cdn.mangaeden.com/mangasimg/";

    public static MangaChapterListFragment newInstance() {
        MangaChapterListFragment mclf = new MangaChapterListFragment();
        return mclf;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manga_chapter_list_page, container, false);
        this.img = view.findViewById(R.id.iv_pic);
        //img.setImageResource();
        //this.mangaChapterInfoModel = presenter.mangaChapterInfoModel;
        recyclerView = view.findViewById(R.id.rv_list_chapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(20));
        mAdapter = new AdapterMangaChapter();
        recyclerView.setAdapter(mAdapter);
        iMainActivity = (MainActivity)getContext();
        return view;

    }
    public void setPresenter(Presenter presenter){
        this.presenter  = presenter;
    }
    public void update(List<MangaChapterModel> listOfMangaChapter, MangaChapterInfoModel mangaChapterInfoModel){
        this.mAdapter.update(listOfMangaChapter);
        this.mangaChapterInfoModel = mangaChapterInfoModel;
        Picasso.get().load(BASE_URL+mangaChapterInfoModel.getUrlImageCover()).into(img);
    }
}
