package com.example.tubes3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.example.tubes3.model.MangaChapterInfoModel;
import com.example.tubes3.model.MangaChapterModel;

import java.util.List;

/*
    DAVID CHRISTOPHER SENTOSA, 2017730015
    Stephen Hadi, 2017730016
    NICHOLAS ADITYA HALIM, 2017730018
 */
public class MainActivity extends AppCompatActivity implements IMainActivity{

    private Presenter presenter;

    private Fragment[] fragmentList;
    private FragmentManager fm;
    private int backStackVal=1;
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
        boolean stats= fragmentList[2].isVisible();
        if(stats){
            ft.remove(fragmentList[2]);
        }
        for(int j=0;j<fragmentList.length;j++){
            if(fragmentList[j].isVisible() && j!=2){
                ft.hide(fragmentList[j]);
                ft.addToBackStack(null);
            }
        }
        if(fragmentList[i].isAdded()){
            ft.show(fragmentList[i]);
        }
        else{
            ft.add(R.id.fragment_container,fragmentList[i]);
        }
        if(!stats || i!=0) ft.addToBackStack(null);
        ft.commit();
    }


    @Override
    public void showMangaList() {
        ((MangaListFragment)this.fragmentList[0]).showMangaList();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void getSearchList() {
        ((MangaListFragment)this.fragmentList[0]).updateMangaList();
        changePage(0);
    }
}
