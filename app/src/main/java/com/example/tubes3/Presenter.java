package com.example.tubes3;


import android.util.Log;

import com.example.tubes3.model.MangaChapterInfoModel;
import com.example.tubes3.model.MangaChapterModel;
import com.example.tubes3.model.MangaModel;

import java.util.ArrayList;
import java.util.List;

public class Presenter {
    private IMainActivity ui;
    private ArrayList<MangaModel> listManga;
    private ArrayList<MangaModel> searchListManga;
    private String[] listOfMangaContent;
    protected List<MangaChapterModel> listChapterManga;
    protected MangaChapterInfoModel mangaChapterInfoModel;
    protected CallVolley callVolley;

    protected SearchFunction searchFunction;
    public Presenter(IMainActivity ui){
        this.ui = ui;
        this.callVolley = new CallVolley(ui.getContext(),this);
        this.listChapterManga = new ArrayList<MangaChapterModel>();
        this.listManga = new ArrayList<MangaModel>();
        this.searchListManga = new ArrayList<MangaModel>();
    }

    public List<MangaChapterModel> getListChapterManga() {
        return listChapterManga;
    }

    public void initListManga(){
        this.callVolley.getMangaList();
        Log.d("inita",listManga.toString());
    }
    public ArrayList<MangaModel> getSearchList(){
        return searchListManga;
    }
    public void searchManga(String keyword){
        this.searchListManga.clear();
        this.searchListManga.addAll(this.listManga);
        this.searchFunction = new SearchFunction(searchListManga);
        this.searchListManga=searchFunction.search(keyword);
        this.ui.getSearchList();
    }

    public void addManga(ArrayList<MangaModel> manga){
        this.listManga = manga;
        this.ui.showMangaList();
        Log.d("inita",listManga.toString());
    }

    public ArrayList<MangaModel> getListManga() {
        return this.listManga;
    }

    public void setMangaChapterInfo(String mangaID){
        this.callVolley.getMangaInfo(mangaID);
        this.ui.changePage(1);
    }

    public void updateMangaChapterInfo(List<MangaChapterModel> listChapterManga,MangaChapterInfoModel mangaChapterInfoModel){
        this.listChapterManga.clear();
        this.listChapterManga.addAll(listChapterManga);
        this.mangaChapterInfoModel = mangaChapterInfoModel;
        this.ui.updateChapterList(this.listChapterManga,this.mangaChapterInfoModel);
    }

    public void changeMangaContent(int i){
        if(i<=0){
            i=1;
        }
        else if(i>=listChapterManga.size()){
            i= listChapterManga.size();
        }

        this.callVolley.getChapter(listChapterManga.get(listChapterManga.size()-i).getChapterId(),i);
    }


    public void updateMangaContent(String[] listOfMangeContent, int indeks) {
        this.listOfMangaContent = listOfMangeContent;
        this.ui.updateMangaContent(this.listOfMangaContent,indeks);
    }
    public void fetchMangaContent(String chapterId,int chapterIndeks){
        this.ui.changePage(2);
        this.callVolley.getChapter(chapterId,chapterIndeks);
    }

}
