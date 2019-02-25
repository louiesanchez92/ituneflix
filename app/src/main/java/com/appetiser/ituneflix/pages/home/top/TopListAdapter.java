package com.appetiser.ituneflix.pages.home.top;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.appetiser.ituneflix.R;
import com.appetiser.ituneflix.api.models.movies.Movie;
import com.appetiser.ituneflix.views.IFTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface SelectMovieListener {
        void select(Movie movie, int position);
    }

    private SelectMovieListener listener;

    private List<Movie> movies;

    public TopListAdapter(List<Movie> movies, SelectMovieListener listener) {
        this.movies = movies;
        this.listener = listener;
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {

        @BindView(R.id.image_movie)
        ImageView movieImage;
        @BindView(R.id.info_layout)
        RelativeLayout infoLayout;
        @BindView(R.id.mylist_layout)
        RelativeLayout myListLayout;
        @BindView(R.id.title_text)
        IFTextView titleText;
        @BindView(R.id.genre_text)
        IFTextView genreText;
        @BindView(R.id.price_text)
        IFTextView priceText;

        public ViewHolderData(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderData(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setupDataView(((ViewHolderData) holder), position);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    private void setupDataView(final ViewHolderData holder, final int position) {

        final Movie movie = movies.get(position);

    }
}