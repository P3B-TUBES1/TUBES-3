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
        this.fragmentList = new Fragment[3];
        this.fragmentList[1] = MangaChapterListFragment.newInstance();
        this.fragmentList[2] = MangaPagesFragment.newInstance();
        this.presenter = new Presenter(this);
        ((MangaChapterListFragment)this.fragmentList[1]).setPresenter(this.presenter);
        ((MangaPagesFragment)(this.fragmentList[2])).setPresenter(this.presenter);
        this.fm = getSupportFragmentManager();
        FragmentTransaction ft = this.fm.beginTransaction();
//        ft.add(R.id.fragment_container, (MangaChapterListFragment) this.fragmentList[1]).commit();
//        ft.add(R.id.fragment_container,(MangaPagesFragment)this.fragmentList[2]).commit();
        presenter.initListManga();

    }
    @Override
    public void onWindowFocusChanged(boolean focus) {
        super.onWindowFocusChanged(focus);
        this.presenter.addDummyData();
    }

    @Override
    public void updateMangaContent(String[] listOfMangaContent, int indeks) {
        ((MangaPagesFragment)this.fragmentList[2]).update(listOfMangaContent,indeks);
    }

    @Override
    public void updateChapterList(List<MangaChapterModel> listChapter) {
        ((MangaChapterListFragment)this.fragmentList[1]).update(listChapter);
    }

    @Override
    public void changePage(int i) {
        Log.d("test-1","test-1");
        FragmentTransaction ft = this.fm.beginTransaction();
//        for(int j=0;j<fragmentList.length;j++){
//            if(fragmentList[j].isAdded())ft.hide(fragmentList[j]);
//            Log.d("test","test");
//        }
        if(fragmentList[i].isAdded()){
            Log.d("test2","test2");
            ft.show(fragmentList[i]);

        }
        else{
            Log.d("test2","test2");
            ft.add(R.id.fragment_container,fragmentList[i]);
        }
        ft.commit();

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
