package com.appetiser.ituneflix.pages.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.appetiser.ituneflix.R;

import butterknife.BindView;

public class MainActivity extends BaseHomeActivity {

    @BindView(R.id.home_image)
    ImageView homeImage;
    @BindView(R.id.list_image)
    ImageView listImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Navigates user to home tab
     * where it can view list of top
     * rated movies in iTunes
     */
    private void homeClick() {

    }

    /**
     * Navigates user to search tab
     * where it can search for movies
     * depending on search value
     */
    private void searchClick() {

    }

    /**
     * Navigates user to my list tab
     * where it can view list of favorite
     * movies by the user
     */
    private void myListClick() {

    }
}
