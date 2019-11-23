package com.example.tubes3.model;

public class MangaModel {
    protected String mangaID;
    protected String title;
    protected String urlImage;

    public MangaModel(String mangaID, String title, String urlImage) {
        this.mangaID = mangaID;
        this.title = title;
        this.urlImage = urlImage;
    }

    public String getMangaID() {
        return mangaID;
    }

    public void setMangaID(String mangaID) {
        this.mangaID = mangaID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
