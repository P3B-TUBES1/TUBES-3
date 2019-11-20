package com.example.tubes3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tubes3.Adapter.MangaContentAdapter;

public class MainActivity extends AppCompatActivity implements IMainActivity{

    private Presenter presenter;

    private Object[] fragmentList;
    private FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.presenter = new Presenter(this);
        this.fragmentList = new Object[3];
        this.fragmentList[2] = MangaPagesFragment.newInstance();
        this.fm = getSupportFragmentManager();
        FragmentTransaction ft = this.fm.beginTransaction();
        ft.add(R.id.fragment_container,(MangaPagesFragment)this.fragmentList[2]).commit();

    }
}
