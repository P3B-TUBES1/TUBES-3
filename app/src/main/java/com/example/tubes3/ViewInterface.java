package com.example.tubes3;

import com.example.tubes3.model.MangaChapterModel;

import java.util.List;

public interface ViewInterface {
    void updateMangaContent(String[] listOfMangaContent,int indeks);
    void updateChapterList(List<MangaChapterModel> listChapter);
    void changeToAnotherChapter(int i );
}
