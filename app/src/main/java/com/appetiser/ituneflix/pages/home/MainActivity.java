package com.appetiser.ituneflix.pages.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appetiser.ituneflix.AppConstants;
import com.appetiser.ituneflix.AppSessions;
import com.appetiser.ituneflix.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

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
    @OnClick(R.id.home_layout)
    void home() {
        /**
         * Check whether the user
         * click the same tab where
         * the user is currently viewing
         * if yes no need to replace fragment
         */
        if (lastTabUserSelected != AppConstants.TOP_TAB) {
            lastTabUserSelected = AppConstants.TOP_TAB;
            saveLastTabSelectedByUserInAppSession(AppConstants.TOP_TAB);
            replaceFragment();
        }
    }

    /**
     * Navigates user to search tab
     * where it can search for movies
     * depending on search value
     */
    @OnClick(R.id.search_layout)
    void search() {
        /**
         * Check whether the user
         * click the same tab where
         * the user is currently viewing
         * if yes no need to replace fragment
         */
        if (lastTabUserSelected != AppConstants.SEARCH_TAB) {
            lastTabUserSelected = AppConstants.SEARCH_TAB;
            saveLastTabSelectedByUserInAppSession(AppConstants.SEARCH_TAB);
            replaceFragment();
        }
    }

    /**
     * Navigates user to my list tab
     * where it can view list of favorite
     * movies by the user
     */
    @OnClick(R.id.mylist_layout)
    void mylist() {
        /**
         * Check whether the user
         * click the same tab where
         * the user is currently viewing
         * if yes no need to replace fragment
         */
        if (lastTabUserSelected != AppConstants.LIST_TAB) {
            lastTabUserSelected = AppConstants.LIST_TAB;
            saveLastTabSelectedByUserInAppSession(AppConstants.LIST_TAB);
            replaceFragment();
        }
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
