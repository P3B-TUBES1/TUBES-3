package com.example.tubes3;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes3.Adapter.MangaListAdapter;
import com.example.tubes3.model.MangaModel;

import java.util.ArrayList;


public class MangaListFragment extends Fragment {
    private Presenter presenter;
    private MangaListAdapter adapter;
    private ListView listView;
    private IMainActivity ui;
    public static MangaListFragment newInstance() {
        MangaListFragment fragment = new MangaListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manga_list_page, container, false);
        this.ui = (MainActivity) getContext();
        listView = view.findViewById(R.id.manga_list_view);
        adapter = new MangaListAdapter(this.getContext(),3,ui);
        listView.setAdapter(adapter);
//        ArrayList<MangaModel> dummyData = new ArrayList<MangaModel>();
//        dummyData.add(new MangaModel("577457ac719a1643eb0447be","Goblin Slayer","c3/c39211cbe7abdb3eb14ed12b355a408764866aa93e5cbdd59489fe76.png"));
//        dummyData.add(new MangaModel("577457ac719a1643eb0447be","Goblin Slayer","c3/c39211cbe7abdb3eb14ed12b355a408764866aa93e5cbdd59489fe76.png"));
//        dummyData.add(new MangaModel("577457ac719a1643eb0447be","Goblin Slayer","c3/c39211cbe7abdb3eb14ed12b355a408764866aa93e5cbdd59489fe76.png"));
//        adapter.addItemsInGrid(dummyData);
//        adapter.addItemsInGrid(dummyData);
        Log.d("inita",presenter.getListManga().toString());
        return view;
    }

    public void showMangaList(){
        adapter.addItemsInGrid(presenter.getListManga());
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

}
