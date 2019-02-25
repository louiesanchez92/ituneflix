package com.appetiser.ituneflix;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSessions {

    /**
     * Create app session to save last activity
     * If the user close and reopens the app
     * The user will be redirected to last activity the user enters.
     */

    static Context context;

    public AppSessions(Context context) {
        this.context = context;
    }

    private static final String LAST_TRACK_ID = "last_track_tab";
    private static final String LAST_TRACK_ID_KEY = "last_track_id_tab_key";

    private static final String LAST_DATE = "last_date";
    private static final String LAST_DATE_KEY = "last_date_key";

    private static final String LAST_ACTIVITY = "last_activity";
    private static final String LAST_ACTIVITY_KEY = "last_activity_key";

    /**
     * Saving last search date and time
     *
     * @param date
     * @return
     */
    public static boolean saveLastSearchDate(String date) {
        final SharedPreferences.Editor editor = context.getSharedPreferences(LAST_DATE,
                Context.MODE_PRIVATE).edit();
        editor.putString(LAST_DATE_KEY, date);
        return editor.commit();
    }

    /**
     * Restoring last search date and time
     *
     * @return
     */
    public static String restoreLastSearchDate() {
        final SharedPreferences savedSession = context.getSharedPreferences(LAST_DATE,
                Context.MODE_PRIVATE);
        String tab = savedSession.getString(LAST_DATE_KEY, null);
        return tab;
    }

    /**
     * Saving what page the user last entered
     * possible values
     * 1 - Search Page
     * 2 - Detailed Page
     *
     * @param tab
     * @return
     */
    public static boolean saveLastActivity(int tab) {
        final SharedPreferences.Editor editor = context.getSharedPreferences(LAST_ACTIVITY,
                Context.MODE_PRIVATE).edit();
        editor.putInt(LAST_ACTIVITY_KEY, tab);
        return editor.commit();
    }

    /**
     * Restoring what page the user last entered
     *
     * @return
     */
    public static Integer restoreLastActivity() {
        final SharedPreferences savedSession = context.getSharedPreferences(LAST_ACTIVITY,
                Context.MODE_PRIVATE);
        int tab = savedSession.getInt(LAST_ACTIVITY_KEY, AppConstants.MAIN_PAGE);
        return tab;
    }


    /**
     * Saving track ID
     * in order to load the movie
     * user last selected
     *
     * @param trackId
     * @return
     */
    public static boolean saveLastTrackID(int trackId) {
        final SharedPreferences.Editor editor = context.getSharedPreferences(LAST_TRACK_ID,
                Context.MODE_PRIVATE).edit();
        editor.putInt(LAST_TRACK_ID_KEY, trackId);
        return editor.commit();
    }

    /**
     * Restoring track ID
     *
     * @return
     */
    public static Integer restoreLastTrackID() {
        final SharedPreferences savedSession = context.getSharedPreferences(LAST_TRACK_ID,
                Context.MODE_PRIVATE);
        int trackId = savedSession.getInt(LAST_TRACK_ID_KEY, -1);
        return trackId;
    }

}
