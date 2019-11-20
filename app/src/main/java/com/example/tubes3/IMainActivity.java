package com.example.tubes3;

import java.util.List;

public interface IMainActivity {
    void updateMangaContent(List<String> listOfMangaContent);//ganti chapter dan merubah semua list Stringnya
    void changePage(int i);//merubah page fragment
    void changeToAnotherChapter(int i );// merubah chapter manga
}
