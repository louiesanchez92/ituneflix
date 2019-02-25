package com.appetiser.ituneflix.pages.home;

import android.os.Bundle;

import com.appetiser.ituneflix.R;

import butterknife.ButterKnife;

public class MainActivity extends BaseHomeActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Initialize view binding
         */
        ButterKnife.bind(this);

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
