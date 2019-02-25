package com.appetiser.ituneflix.pages.home;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.appetiser.ituneflix.AppConstants;
import com.appetiser.ituneflix.AppSessions;
import com.appetiser.ituneflix.R;
import com.appetiser.ituneflix.api.ApiConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseHomeActivity {

    /**
     * Binding views much faster than the traditional
     * find view by id method
     * Thanks to butterknife.
     */
    @BindView(R.id.home_image)
    ImageView homeImage;
    @BindView(R.id.list_image)
    ImageView listImage;
    @BindView(R.id.search_image)
    ImageView searchImage;
    @BindView(R.id.list_label)
    TextView listLabel;
    @BindView(R.id.home_label)
    TextView homeLabel;
    @BindView(R.id.search_label)
    TextView searchLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Initialize view binding
         */
        ButterKnife.bind(this);
        getLastTabSelectedByUser();

        /**
         * This will determine what tab to open first
         * when app loads
         */
        setUpInitialFragment();


    }

    /**
     * Get last tab selected by user
     * If app is freshly installed
     * this defaults to Top List of Movies Tab
     */
    private void getLastTabSelectedByUser() {
        lastTabUserSelected = AppSessions.restoreLastTab();
    }

    /**
     * Navigates user to home tab
     * where it can view list of top
     * rated movies in iTunes
     */
    private void homeClick() {
        saveLastTabSelectedByUserInAppSession(AppConstants.TOP_TAB);
    }

    /**
     * Navigates user to search tab
     * where it can search for movies
     * depending on search value
     */
    private void searchClick() {
        //saveLastTabSelectedByUserInAppSession(AppConstants.SEARCH_TAB);
    }

    /**
     * Navigates user to my list tab
     * where it can view list of favorite
     * movies by the user
     */
    private void myListClick() {
        // saveLastTabSelectedByUserInAppSession(AppConstants.LIST_TAB);
    }

    /**
     * This will save an integer indicating the last tab
     * chosen or open by the user
     *
     * @param tab
     */
    private void saveLastTabSelectedByUserInAppSession(int tab) {
        AppSessions.saveLastTab(tab);
    }
}
