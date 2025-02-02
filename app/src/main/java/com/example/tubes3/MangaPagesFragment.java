package com.example.tubes3;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubes3.Adapter.MangaContentAdapter;


public class MangaPagesFragment extends Fragment implements View.OnTouchListener
        , View.OnClickListener, View.OnKeyListener {


    private RecyclerView mangaContentRC;
    private IMainActivity ui;
    private int indeks;

    private Presenter presenter;
    private ImageView back_button;
    private ImageView previous_chapter;
    private ImageView next_chapter;
    private EditText chapterNumber;
    private LinearLayout linearLayout;

    private MangaContentAdapter mangaContentAdapter;
    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector scrollGesture;

    private CountDownTimer timer;

    private boolean onScale = false;

    public static MangaPagesFragment newInstance() {
        return new MangaPagesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manga_content, container, false);
        this.mangaContentRC = view.findViewById(R.id.manga_content_recycler_view);
        this.back_button = view.findViewById(R.id.back_button);
        this.previous_chapter = view.findViewById(R.id.previous_chapter);
        this.next_chapter = view.findViewById(R.id.next_chapter);
        this.chapterNumber = view.findViewById(R.id.chapter_number);
        this.linearLayout = view.findViewById(R.id.footer_manga_content);
        this.ui = (MainActivity) getContext();

        mangaContentAdapter = new MangaContentAdapter(getResources().getDisplayMetrics().widthPixels);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mangaContentRC.setLayoutManager(layoutManager);
        mangaContentRC.setItemViewCacheSize(5);
        mangaContentRC.setHasFixedSize(false);

        CustomZoom customZoom = new CustomZoom();
        this.scaleGestureDetector = new ScaleGestureDetector(getContext(), customZoom);
        this.scrollGesture = new GestureDetector(getContext(), customZoom);
        this.mangaContentRC.setOnTouchListener(this);

        this.back_button.setOnClickListener(this);
        this.previous_chapter.setOnClickListener(this);
        this.next_chapter.setOnClickListener(this);
        this.chapterNumber.setOnKeyListener(this);

        setTimer(getResources().getDisplayMetrics().heightPixels);

        return view;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public void update(String[] listOfMangaContent, int indeks) {
        this.mangaContentRC.setAdapter(this.mangaContentAdapter);
        this.mangaContentAdapter.update(listOfMangaContent);
        this.indeks = indeks;
        this.chapterNumber.setText(indeks + "");
    }

    public void setTimer(final int screenHeight) {
        if(timer!=null) {
            timer.cancel();
        }
        timer = new CountDownTimer(5000, 5000) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                linearLayout.animate().translationY(screenHeight);
            }
        }.start();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        chapterNumber.clearFocus();
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(
                getContext().INPUT_METHOD_SERVICE);
        View focusedView = getActivity().getCurrentFocus();
        if (focusedView != null) {
            inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_UP:
                onScale = false;
        }
        scaleGestureDetector.onTouchEvent(motionEvent);

        if (onScale) return true;

        scrollGesture.onTouchEvent(motionEvent);


        return false;
    }

    @Override
    public void onClick(View view) {
        Log.d("clicked", "clicked");
        int currentChapter = Integer.parseInt(chapterNumber.getText().toString());
        if (view == this.back_button) {
            this.ui.changePage(1);
        } else if (view == this.previous_chapter) {
            this.presenter.changeMangaContent(currentChapter - 1);
        } else if (view == this.next_chapter) {
            this.presenter.changeMangaContent(currentChapter+1);
        }
        mangaContentRC.setAdapter(null);
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                (i == KeyEvent.KEYCODE_ENTER)) {
            mangaContentRC.setAdapter(null);
            presenter.changeMangaContent(Integer.parseInt(chapterNumber.getText().toString()));
            return true;
        }
        return false;
    }

    private class CustomZoom extends ScaleGestureDetector.SimpleOnScaleGestureListener implements
            GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
        private int viewWidth;
        private int viewHeight;
        private final float minZoom = 1.f;
        private final float maxZoom = 2.5f;
        private float scaleFactor = 1.f;
        private float viewPosition = 1.f;
        private float footerTranslationY;

        public CustomZoom() {
            viewWidth = getResources().getDisplayMetrics().widthPixels;
            viewHeight = getResources().getDisplayMetrics().heightPixels;
            footerTranslationY = linearLayout.getTranslationY();
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scale = detector.getScaleFactor();
            scaleFactor *= scale;
            scaleFactor = Math.max(minZoom, Math.min(maxZoom, scaleFactor));
            onScale = true;
            if (detector.getScaleFactor() < 1.f && scaleFactor != 1.f) {
                float scaleStep = (scaleFactor - minZoom);
                viewPosition += -(viewPosition - 1.f) * scaleStep;
            }
            invalidateAll();
            return super.onScale(detector);
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            float distX = Math.round(motionEvent1.getX() - motionEvent.getX());
            if (!isAtEdge(distX)) {
                viewPosition += distX * 2;
                invalidateAll();

            }
            return true;

        }

        public boolean isAtEdge(float direction) {
            float spaceWidth = (Math.abs((mangaContentRC.getWidth() - (mangaContentRC.getWidth() * mangaContentRC.getScaleX())) / 2.f));
            float currentLeft = (mangaContentRC.getLeft() - mangaContentRC.getTranslationX() + spaceWidth);
            float currentRight = (mangaContentRC.getRight() - spaceWidth - mangaContentRC.getTranslationX());

            if (currentLeft - direction * 2 <= 0.f && direction >= 0) {
                if (currentLeft > 0.f) {
                    Math.round(currentLeft);
                    viewPosition += currentLeft;
                    invalidateAll();
                }
                return true;

            } else if (currentRight - direction * 2 >= viewWidth && direction < 0) {
                if (currentRight < viewWidth) {
                    float range = viewWidth - currentRight;
                    Math.round(range);
                    viewPosition -= range;
                    invalidateAll();
                }
                return true;
            }

            return false;
        }


        @Override
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (linearLayout.getTranslationY() != viewHeight) {
                linearLayout.animate().translationY(viewHeight);
                return true;
            }
            linearLayout.animate().translationY(footerTranslationY);

            setTimer(viewHeight);
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (scaleFactor != 1.f) {
                scaleFactor = 1.f;

                viewPosition = 1.f;
                mangaContentRC.setTranslationY(0.f);
                invalidateAll();
                return false;
            }
            scaleFactor = 2.f;
            float translateX = viewWidth / 2 - motionEvent.getX();
            float translateY = viewHeight / 2 - motionEvent.getY();
            Math.round(translateX);
            Math.round(translateY);
            viewPosition += translateX;
            mangaContentRC.setTranslationY(translateY);
            invalidateAll();
            return false;
        }

        public void invalidateAll() {
            mangaContentRC.setScaleX(scaleFactor);
            mangaContentRC.setScaleY(scaleFactor);
            mangaContentRC.setTranslationX(viewPosition);
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
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return false;
        }
    }
}
