package com.example.tubes3.model;

public class MangaChapterInfoModel {
    protected String mangaTitle;
    protected String[] genre;
    protected String description;
    protected String artistName;
    protected String urlImageCover;
    protected String releaseDate;

    public MangaChapterInfoModel(String mangaTitle, String[] genre, String description, String artistName, String urlImageCover, String releaseDate) {
        this.mangaTitle = mangaTitle;
        this.genre = genre;
        this.description = description;
        this.artistName = artistName;
        this.urlImageCover = urlImageCover;
        this.releaseDate = releaseDate;
    }

}
