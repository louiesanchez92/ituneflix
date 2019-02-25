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
        arrangeIcons();


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
            arrangeIcons();
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
            arrangeIcons();
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
            arrangeIcons();
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

    /**
     * This will display
     * the proper icon color and style
     * of bottom navigation image and text
     * in short this is to emphasize or indicate
     * that the user is in this tab.
     */
    protected void arrangeIcons() {

        /**
         * Set Default value first
         */
        homeImage.setImageResource(R.drawable.ic_home);
        searchImage.setImageResource(R.drawable.ic_magnifying_glass);
        listImage.setImageResource(R.drawable.ic_list);

        homeLabel.setTextColor(getResources().getColor(R.color.gray));
        searchLabel.setTextColor(getResources().getColor(R.color.gray));
        listLabel.setTextColor(getResources().getColor(R.color.gray));

        /**
         * Set the selected image
         * and white color to emphasize
         * the tab status
         */
        switch (lastTabUserSelected) {
            case 1:
                homeImage.setImageResource(R.drawable.ic_home_selected);
                homeLabel.setTextColor(getResources().getColor(R.color.white));
                break;
            case 2:
                searchImage.setImageResource(R.drawable.ic_magnifying_glass_selected);
                searchLabel.setTextColor(getResources().getColor(R.color.white));
                break;
            case 3:
                listImage.setImageResource(R.drawable.ic_list_selected);
                listLabel.setTextColor(getResources().getColor(R.color.white));
                break;
        }
    }
}
