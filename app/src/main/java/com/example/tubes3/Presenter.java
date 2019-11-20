package com.example.tubes3;

import com.example.tubes3.model.Manga;

public class Presenter {
    private IMainActivity ui;
    private Manga[] listManga;
    public Presenter(IMainActivity ui){
        this.ui = ui;
    }

    public Manga[] getListManga() {
        return listManga;
    }
}
