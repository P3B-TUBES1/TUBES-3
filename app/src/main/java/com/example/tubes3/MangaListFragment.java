package com.example.tubes3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes3.Adapter.MangaListAdapter;
import com.example.tubes3.model.MangaModel;

import java.util.ArrayList;


public class MangaListFragment extends Fragment implements View.OnClickListener{
    private Presenter presenter;
    private MangaListAdapter adapter;
    private ListView listView;
    private IMainActivity ui;
    private EditText editText;
    private ImageView searchIcon;
    private ImageView mangaLogo;

    public static MangaListFragment newInstance() {
        MangaListFragment fragment = new MangaListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manga_list_page, container, false);
        this.ui = (MainActivity) getContext();
        listView = view.findViewById(R.id.manga_list_view);
        editText = view.findViewById(R.id.my_search_bar);
        searchIcon = view.findViewById(R.id.search_icon);
        mangaLogo = view.findViewById(R.id.manga_eden_im);
        adapter = new MangaListAdapter(this.getContext(),3,presenter);
        listView.setAdapter(adapter);
//        ArrayList<MangaModel> dummyData = new ArrayList<MangaModel>();
//        dummyData.add(new MangaModel("577457ac719a1643eb0447be","Goblin Slayer","c3/c39211cbe7abdb3eb14ed12b355a408764866aa93e5cbdd59489fe76.png"));
//        dummyData.add(new MangaModel("577457ac719a1643eb0447be","Goblin Slayer","c3/c39211cbe7abdb3eb14ed12b355a408764866aa93e5cbdd59489fe76.png"));
//        dummyData.add(new MangaModel("577457ac719a1643eb0447be","Goblin Slayer","c3/c39211cbe7abdb3eb14ed12b355a408764866aa93e5cbdd59489fe76.png"));
//        adapter.addItemsInGrid(dummyData);
//        adapter.addItemsInGrid(dummyData);
        Log.d("inita",presenter.getListManga().toString());
        mangaLogo.setOnClickListener(this);
        searchIcon.setOnClickListener(this);
        return view;
    }
    public void showMangaList(){
        adapter.addItemsInGrid(presenter.getListManga());
    }

    public void updateMangaList(){
        Log.d("updated","updated");
        adapter.clearList();
        adapter.addItemsInGrid(presenter.getSearchList());
    }
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onClick(View view) {
        if(view == mangaLogo){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.dialog_message)
                    .setTitle(R.string.dialog_title);
            builder.setPositiveButton(R.string.open, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mangaeden.com/")));
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
            Log.d("alert", "alert");
        }
        if(view == searchIcon){
            presenter.searchManga(editText.getText().toString());
        }
    }
}
