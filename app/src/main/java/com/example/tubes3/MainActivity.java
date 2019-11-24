package com.example.tubes3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView;


import androidx.fragment.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.tubes3.Adapter.MangaContentAdapter;
import com.example.tubes3.model.MangaChapterInfoModel;
import com.example.tubes3.model.MangaChapterModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainActivity{

    private Presenter presenter;

    private Fragment[] fragmentList;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.presenter = new Presenter(this);
        this.presenter.initListManga();
        this.fragmentList = new Fragment[3];
        this.fragmentList[0] = MangaListFragment.newInstance();
        this.fragmentList[1] = MangaChapterListFragment.newInstance();
        this.fragmentList[2] = MangaPagesFragment.newInstance();
        ((MangaListFragment)this.fragmentList[0]).setPresenter(this.presenter);
        ((MangaChapterListFragment)this.fragmentList[1]).setPresenter(this.presenter);
        ((MangaPagesFragment)(this.fragmentList[2])).setPresenter(this.presenter);
        this.fm = getSupportFragmentManager();
        FragmentTransaction ft = this.fm.beginTransaction();
        ft.add(R.id.fragment_container, (MangaListFragment) this.fragmentList[0]).commit();
    }
    @Override
    public void onWindowFocusChanged(boolean focus) {
        super.onWindowFocusChanged(focus);
    }

    @Override
    public void updateMangaContent(String[] listOfMangaContent, int indeks) {
        ((MangaPagesFragment)this.fragmentList[2]).update(listOfMangaContent,indeks);
    }

    @Override
    public void updateChapterList(List<MangaChapterModel> listChapter, MangaChapterInfoModel mangaChapterInfoModel) {
        ((MangaChapterListFragment)this.fragmentList[1]).update(listChapter,mangaChapterInfoModel);
    }

    @Override
    public void changePage(int i) {
        FragmentTransaction ft = this.fm.beginTransaction();
        for(int j=0;j<fragmentList.length;j++){
            if(fragmentList[j].isAdded())ft.hide(fragmentList[j]);
        }
        fm.popBackStack();
        if(fragmentList[i].isAdded()){
            ft.show(fragmentList[i]);

        }
        else{
            ft.add(R.id.fragment_container,fragmentList[i]);
        }
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void changeToAnotherChapter(int i) {

    }

    @Override
    public void showMangaList() {
        ((MangaListFragment)this.fragmentList[0]).showMangaList();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
