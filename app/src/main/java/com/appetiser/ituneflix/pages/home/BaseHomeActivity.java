package com.appetiser.ituneflix.pages.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.appetiser.ituneflix.AppConstants;
import com.appetiser.ituneflix.R;
import com.appetiser.ituneflix.pages.home.list.MyListFragment;
import com.appetiser.ituneflix.pages.home.search.SearchFragment;
import com.appetiser.ituneflix.pages.home.top.TopListFragment;

public class BaseHomeActivity extends AppCompatActivity {


    /**
     * Initializing fragments
     */
    protected SearchFragment searchFragment = new SearchFragment();
    protected TopListFragment topListFragment = new TopListFragment();
    protected MyListFragment myListFragment = new MyListFragment();
    protected Fragment fragment;

    protected int lastTabUserSelected = AppConstants.TOP_TAB;

    protected void setUpInitialFragment() {

        switch (lastTabUserSelected) {
            case 1:
                /**
                 * show top tab
                 */
                fragment = topListFragment;
                break;
            case 2:
                /**
                 * show search tab
                 */
                fragment = searchFragment;
                break;
            case 3:
                /**
                 * show list tab
                 */
                fragment = myListFragment;
                break;
        }
        getSupportFragmentManager().beginTransaction().add(R.id.content_layout, fragment).commit();

    }

    protected void replaceFragment() {

        switch (lastTabUserSelected) {
            case 1:
                /**
                 * replace current fragment to Top List Fragment
                 */
                fragment = topListFragment;
                break;
            case 2:
                /**
                 * replace current fragment to Search List Fragment
                 */
                fragment = searchFragment;
                break;
            case 3:
                /**
                 * replace current fragment to My List Fragment
                 */
                fragment = myListFragment;
                break;
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_layout, fragment).commit();
    }

}
