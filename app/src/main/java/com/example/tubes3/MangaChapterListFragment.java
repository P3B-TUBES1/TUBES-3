package com.example.tubes3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes3.Adapter.AdapterMangaChapter;
import com.example.tubes3.model.MangaChapterInfoModel;
import com.example.tubes3.model.MangaChapterModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MangaChapterListFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private AdapterMangaChapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MangaChapterInfoModel mangaChapterInfoModel;
    private IMainActivity iMainActivity;
    ImageView img;
    private Presenter presenter;
    private final String BASE_URL = "https://cdn.mangaeden.com/mangasimg/";
    private ImageView back_button;
    private IMainActivity ui;


    public static MangaChapterListFragment newInstance() {
        MangaChapterListFragment mclf = new MangaChapterListFragment();
        return mclf;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manga_chapter_list_page, container, false);
        this.img = view.findViewById(R.id.iv_pic);
        RelativeLayout rl_toolbar = view.findViewById(R.id.rl_toolbar);
        ImageView img = new ImageView(getContext());

        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.download);
        drawable.setColorFilter(new
                PorterDuffColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN));
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        img.setAdjustViewBounds(true);
        img.setLayoutParams(lp);
        img.setImageDrawable(drawable);
        rl_toolbar.addView(img);
        //img.setImageResource();
        //this.mangaChapterInfoModel = presenter.mangaChapterInfoModel;
        recyclerView = view.findViewById(R.id.rv_list_chapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(20));
        mAdapter = new AdapterMangaChapter(this.presenter);
        recyclerView.setAdapter(mAdapter);
        iMainActivity = (MainActivity)getContext();
        this.back_button = view.findViewById(R.id.back_button);
        this.back_button.setOnClickListener(this);
        this.ui = (MainActivity) getContext();
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

    public void onClick(View view) {
        if (view == this.back_button) {
            this.ui.changePage(0);
        }
    }
}
