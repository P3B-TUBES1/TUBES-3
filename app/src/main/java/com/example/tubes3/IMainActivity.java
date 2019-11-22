package com.example.tubes3;

import android.content.Context;

import com.example.tubes3.model.MangaChapterModel;

import java.util.List;

public interface IMainActivity {
    void updateChapterList(List<MangaChapterModel> listChapter);
    void changePage(int i);//merubah page fragment
    void changeToAnotherChapter(int i );// merubah chapter manga
    Context getContext();
    List<MangaChapterModel> getChapterArray();
}
