package com.example.tubes3;

import com.example.tubes3.model.MangaChapterModel;

import java.util.List;

public interface IMainActivity {
    void updateMangaContent(List<String> listOfMangaContent);//ganti chapter dan merubah semua list Stringnya
    void updateChapterList(List<MangaChapterModel> listChapter);
    void changePage(int i);//merubah page fragment
    void changeToAnotherChapter(int i );// merubah chapter manga
    List<MangaChapterModel> getChapterArray();
}
