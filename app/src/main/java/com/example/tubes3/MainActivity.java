package com.example.tubes3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.os.Bundle;

import com.example.tubes3.Adapter.MangaContentAdapter;
import com.example.tubes3.model.MangaChapterModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainActivity{

    private Presenter presenter;

    private Object[] fragmentList;
    private FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.fragmentList = new Object[3];
        this.fragmentList[1] = MangaChapterListFragment.newInstance();
        this.fragmentList[2] = MangaPagesFragment.newInstance();
        this.presenter = new Presenter(this);
        this.fm = getSupportFragmentManager();
        FragmentTransaction ft = this.fm.beginTransaction();
//        ft.add(R.id.fragment_container, (MangaChapterListFragment) this.fragmentList[1]).commit();
        ft.add(R.id.fragment_container,(MangaPagesFragment)this.fragmentList[2]).commit();

    }
    @Override
    public void onWindowFocusChanged(boolean focus) {
        super.onWindowFocusChanged(focus);
        this.presenter.addDummyData();
    }
    @Override
    public void updateMangaContent(String[] listOfMangaContent) {
        ((MangaPagesFragment)this.fragmentList[2]).update(listOfMangaContent);
    }

    @Override
    public void updateChapterList(List<MangaChapterModel> listChapter) {
        ((MangaChapterListFragment)this.fragmentList[1]).update(listChapter);
    }

    @Override
    public void changePage(int i) {

    }

    @Override
    public void changeToAnotherChapter(int i) {

    }

    @Override
    public List<MangaChapterModel> getChapterArray() {
        return presenter.getArrayChapterManga();
    }


    @Override
    public Context getContext() {
        return this;
    }
}
