package com.example.tubes3;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes3.Adapter.MangaContentAdapter;

import java.util.List;

public class MangaPagesFragment extends Fragment implements View.OnTouchListener {


    private RecyclerView mangaContentRC;
    private IMainActivity ui;
    private ImageView back_button;
    private ImageView previous_chapter;
    private ImageView next_chapter;
    private EditText chapterNumber;

    private MangaContentAdapter mangaContentAdapter;
    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector scrollGesture;
    private int viewWidth;

    private boolean isScroll = false;
    public static MangaPagesFragment newInstance(){
        return new MangaPagesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manga_content,container,false);
        this.mangaContentRC = view.findViewById(R.id.manga_content_recycler_view);
        this.back_button = view.findViewById(R.id.back_button);
        this.previous_chapter = view.findViewById(R.id.previous_chapter);
        this.next_chapter  =view.findViewById(R.id.next_chapter);
        this.chapterNumber = view.findViewById(R.id.chapter_number);

        this.viewWidth = getResources().getDisplayMetrics().widthPixels;
        this.ui = (MainActivity)getContext();

        mangaContentAdapter = new MangaContentAdapter(getResources().getDisplayMetrics().widthPixels);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mangaContentRC.setLayoutManager(layoutManager);
        mangaContentRC.setAdapter(mangaContentAdapter);
        mangaContentRC.setScaleX(1f);
        mangaContentRC.setScaleY(1f);
        mangaContentRC.setItemViewCacheSize(30);
        mangaContentRC.setHasFixedSize(true);
        mangaContentRC.canScrollHorizontally(0);
        CustomZoom customZoom = new CustomZoom();
        this.scaleGestureDetector = new ScaleGestureDetector(this.ui.getContext(),customZoom);
        this.scrollGesture = new GestureDetector(this.ui.getContext(),customZoom);
        this.mangaContentRC.setOnTouchListener(this);
        return view;
    }
    public void update(String[] listOfMangaContent){
        this.mangaContentAdapter.update(listOfMangaContent);
    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        scaleGestureDetector.onTouchEvent(motionEvent);
        scrollGesture.onTouchEvent(motionEvent);
        return false;
    }

    private class CustomZoom extends ScaleGestureDetector.SimpleOnScaleGestureListener implements GestureDetector.OnGestureListener {
        private int viewWidth;
        private int viewHeight;
        private final float minZoom = 1.f;
        private final float maxZoom = 2.f;
        private float scaleFactor = 1.f;
        private float viewPosition = 1.f;
        private float startX=0;
        private float distX=0;
        private float prevDX =0;
        private boolean isScroll=false;
        public CustomZoom(){
            viewWidth = getResources().getDisplayMetrics().widthPixels;
            viewHeight = getResources().getDisplayMetrics().heightPixels;
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scale = detector.getScaleFactor();
            scaleFactor *=scale;
            scaleFactor = Math.max(minZoom,Math.min(maxZoom,scaleFactor));
            mangaContentRC.setScaleX(scaleFactor);
            mangaContentRC.setScaleY(scaleFactor);
            mangaContentRC.invalidate();
            mangaContentRC.requestLayout();
            Log.d("Scale","scale");
            return super.onScale(detector);
        }

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                distX = Math.round(motionEvent1.getX()-motionEvent.getX());
                Log.d("distX",distX+"");
                viewPosition +=distX;
                mangaContentRC.setTranslationX(viewPosition);
                mangaContentRC.invalidate();
                isScroll = true;
                return true;

        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }
    }
}
