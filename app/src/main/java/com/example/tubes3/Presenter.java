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
    private String[] listOfMangaContent;
    protected List<MangaChapterModel> listChapterManga;
    protected MangaChapterInfoModel mangaChapterInfoModel;
    protected CallVolley callVolley;


    public Presenter(IMainActivity ui){
        this.ui = ui;
        this.callVolley = new CallVolley(ui.getContext(),this);
        this.listChapterManga = new ArrayList<MangaChapterModel>();
        this.listManga = new ArrayList<MangaModel>();
    }

    public void initListManga(){
        this.callVolley.getMangaList();
        Log.d("inita",listManga.toString());
    }


    public void addManga(ArrayList<MangaModel> manga){
        this.listManga = manga;
        this.ui.showMangaList();
        Log.d("inita",listManga.toString());
    }

    public ArrayList<MangaModel> getListManga() {
        return this.listManga;
    }

    public void addDummyData() {

//        String[] x = {"1e/1e9b52578f05cc26801e4d075091e9fb3efa488b965a9618f8585839.jpg", "f5/f53792e9810c368facd5eb5399fb8c175caed10cfa66b879c5e212d8.jpg",
//                "7e/7ec84d81e2a18a02e373b0263a62007394fc0e06b1651267896b8c80.jpg"};
//        addMangaContent(x);
//        this.callVolley.getChapter("5c41e6f5719a161e8d68f334",6);

//        addChapter(listChapterManga);


//        listChapterManga.add(new MangaChapterModel("lalala","yeyeye","fa/fa40c083f83215a8e0b605e3706895197103ef4ce0aeda12e698babd.png",0));
        listChapterManga.add(new MangaChapterModel("5c50641c719a167856c5f193","lolololol","yeyeye","fa/fa40c083f83215a8e0b605e3706895197103ef4ce0aeda12e698babd.png",0));
//        addChapter(listChapterManga);

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
//
//    public void addChapter(List listChapter){
//
//        this.ui.updateChapterList(listChapter);
//    }

    public void changeMangaContent(int i){
        if(i<=0){
            i=1;
        }
        this.callVolley.getChapter("5c41e6c1719a161e8d68f23c",i);
    }


    public void updateMangaContent(String[] listOfMangeContent, int indeks) {
        this.listOfMangaContent = listOfMangeContent;

        this.ui.updateMangaContent(this.listOfMangaContent,indeks);
    }

//    public MangaChapterInfoModel getMangaChapterInfoModel() {
//        return mangaChapterInfoModel;
//    }

//    public void setMangaChapterInfoModel(MangaChapterInfoModel mangaChapterInfoModel) {
//        this.mangaChapterInfoModel = mangaChapterInfoModel;
//    }

//    public List<MangaChapterModel> getArrayChapterManga() {
//        return listChapterManga;
//    }



}
