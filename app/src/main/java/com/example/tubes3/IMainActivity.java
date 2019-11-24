package com.example.tubes3;

import android.content.Context;

import com.example.tubes3.model.MangaChapterInfoModel;
import com.example.tubes3.model.MangaChapterModel;

import java.util.List;

public interface IMainActivity {
    void updateMangaContent(String[] listOfMangaContent,int indeks);//ganti chapter dan merubah semua list Stringnya
    void updateChapterList(List<MangaChapterModel> listChapter, MangaChapterInfoModel mangaChapterInfoModel);
    void changePage(int i);//merubah page fragment
    void changeToAnotherChapter(int i );// merubah chapter manga
    void showMangaList();
    Context getContext();
}
