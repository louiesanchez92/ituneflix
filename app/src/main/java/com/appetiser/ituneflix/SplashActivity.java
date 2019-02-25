package com.appetiser.ituneflix;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.appetiser.ituneflix.pages.home.search.SearchListActivity;

public class SplashActivity extends AppCompatActivity {

    /**
     * Transition to next page delay
     * in millis
     */
    private int delay = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        /**
         * execute loader
         * delays showing of next page
         */
        new Loader().execute();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /* Async Task */
    private class Loader extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... none) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {

            } catch (Exception e) {

            }
            return 0;
        }

        @Override
        protected void onPostExecute(Integer result) {
            proceed();
        }
    }

    private void proceed() {
        /**
         * Proceed to home page
         * or detail page it depends on
         * users last viewed page
         */


        Intent intent = new Intent(SplashActivity.this, SearchListActivity.class);
        if (AppSessions.restoreLastActivity().equals(AppConstants.DETAIL_PAGE)) {
            intent.putExtra("proceedToDetailView", true);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        finish();

    }

}
