package com.example.tubes3.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.birin.gridlistviewadapters.Card;
import com.birin.gridlistviewadapters.ListGridAdapter;
import com.birin.gridlistviewadapters.dataholders.CardDataHolder;
import com.birin.gridlistviewadapters.utils.ChildViewsClickHandler;
import com.example.tubes3.IMainActivity;
import com.example.tubes3.Presenter;
import com.example.tubes3.R;
import com.example.tubes3.model.MangaModel;
import com.squareup.picasso.Picasso;

public class MangaListAdapter extends ListGridAdapter<MangaModel, MangaViewHolder> {
    protected final String BASE_URL= "https://cdn.mangaeden.com/mangasimg/";
    Presenter presenter;

    public MangaListAdapter(Context context, int totalCardsInRow, Presenter presenter) {
        super(context, totalCardsInRow);
        this.presenter = presenter;
        Log.d("card","test");
    }

    @Override
    protected Card<MangaViewHolder> getNewCard(int cardwidth) {
        View cardView = getLayoutInflater().inflate(
                R.layout.manga_list_item, null);
        cardView.setMinimumHeight(cardwidth);
        MangaViewHolder viewHolder = new MangaViewHolder((TextView)cardView.findViewById(R.id.tv_manga),(ImageView)cardView.findViewById(R.id.iv_manga));
        Log.d("card","test");
        return new Card<MangaViewHolder>(cardView, viewHolder);
    }

    protected void setCardView(CardDataHolder<MangaModel> cardDataHolder,
                               MangaViewHolder cardViewHolder) {
        MangaModel item = cardDataHolder.getData();
        cardViewHolder.textView.setText(item.getTitle());
        Picasso.get()
                .load(BASE_URL+item.getUrlImage())
                .resize(230,350)
                .placeholder(R.drawable.manga_placeholder)
                .error(R.drawable.manga_error)
                .into(cardViewHolder.imageView);
        Log.d("card","test");
    }

    @Override
    protected void onCardClicked(MangaModel cardData) {
        presenter.setMangaChapterInfo(cardData.getMangaID());
    }

    @Override
    protected void registerChildrenViewClickEvents(MangaViewHolder cardViewHolder, ChildViewsClickHandler childViewsClickHandler) {

    }

    @Override
    protected void onChildViewClicked(View clickedChildView, MangaModel cardData, int eventId) {

    }
}

class MangaViewHolder {
    TextView textView;
    ImageView imageView;

    public MangaViewHolder(TextView textView, ImageView imageView) {
        this.textView = textView;
        this.imageView = imageView;
    }
}




