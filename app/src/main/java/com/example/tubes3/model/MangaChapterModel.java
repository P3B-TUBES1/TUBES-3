package com.example.tubes3.model;

public class MangaChapterModel {
    protected String chapterId;
    protected String chapterTitle;
    protected String chapterDate;
    protected String urlImage;
    protected int index;

    public MangaChapterModel(String chapterId, String chapterTitle, String chapterDate, String urlImage, int index) {
        this.chapterId = chapterId;
        this.chapterTitle = chapterTitle;
        this.chapterDate = chapterDate;
        this.urlImage = urlImage;
        this.index = index;
    }

    public String getChapterId() {
        return chapterId;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public String getChapterDate() {
        return chapterDate;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public int getIndex() {
        return index;
    }
}
