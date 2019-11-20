package com.example.tubes3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tubes3.Adapter.MangaContentAdapter;

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
        this.fragmentList[2] = MangaPagesFragment.newInstance();
        this.presenter = new Presenter(this);
        this.fm = getSupportFragmentManager();
        FragmentTransaction ft = this.fm.beginTransaction();
        ft.add(R.id.fragment_container,(MangaPagesFragment)this.fragmentList[2]).commit();
    }
    @Override
    public void onWindowFocusChanged(boolean focus) {
        super.onWindowFocusChanged(focus);
        this.presenter.addDummyData();
    }
    @Override
    public void updateMangaContent(List<String> listOfMangaContent) {
        ((MangaPagesFragment)this.fragmentList[2]).update(listOfMangaContent);
    }

    @Override
    public void changePage(int i) {

    }

    @Override
    public void changeToAnotherChapter(int i) {

    }
}
