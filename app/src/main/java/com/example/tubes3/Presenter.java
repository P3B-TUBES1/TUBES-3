package com.example.tubes3;

import com.example.tubes3.model.Manga;
import com.example.tubes3.model.MangaChapterInfoModel;
import com.example.tubes3.model.MangaChapterModel;
import com.example.tubes3.model.MangaModel;

import java.util.ArrayList;
import java.util.List;

public class Presenter {
    private IMainActivity ui;
    private MangaModel[] listManga;
    private List<String> listOfMangaContent;
    protected MangaChapterModel[] arrayChapterManga;
    protected MangaChapterInfoModel mangaChapterInfoModel;
    protected CallVolley callVolley;


    public Presenter(IMainActivity ui) {
        this.ui = ui;
        this.listOfMangaContent = new ArrayList<String>();
        callVolley = new CallVolley(ui);
    }

    public MangaModel[] getListManga() {
        return listManga;
    }

    public void addDummyData() {
        String[] x = {"1e/1e9b52578f05cc26801e4d075091e9fb3efa488b965a9618f8585839.jpg", "f5/f53792e9810c368facd5eb5399fb8c175caed10cfa66b879c5e212d8.jpg",
                "7e/7ec84d81e2a18a02e373b0263a62007394fc0e06b1651267896b8c80.jpg"};
        addMangaContent(x);

    }

    public void addMangaContent(String[] listOfMangeContent) {
        this.listOfMangaContent.clear();
        for (int i = 0; i < listOfMangeContent.length; i++) {
            this.listOfMangaContent.add(listOfMangeContent[i]);
        }
        this.ui.updateMangaContent(this.listOfMangaContent);
    }

    public MangaChapterInfoModel getMangaChapterInfoModel() {
        return mangaChapterInfoModel;
    }

    public void setMangaChapterInfoModel(MangaChapterInfoModel mangaChapterInfoModel) {
        this.mangaChapterInfoModel = mangaChapterInfoModel;
    }

    public MangaChapterModel[] getArrayChapterManga() {
        return arrayChapterManga;
    }
}
