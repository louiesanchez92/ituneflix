package com.appetiser.ituneflix;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSessions {

    /**
     * Create app session to save last tab key value
     * 1 - Top List
     * 2 - Search List
     * 3 - My List
     * It will determine the last tab the user is into
     * If the user close and reopens the app
     * The user will be redirected to last tab open
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

    public static boolean saveLastSearchDate(String date) {
        final SharedPreferences.Editor editor = context.getSharedPreferences(LAST_DATE,
                Context.MODE_PRIVATE).edit();
        editor.putString(LAST_DATE_KEY, date);
        return editor.commit();
    }

    public static String restoreLastSearchDate() {
        final SharedPreferences savedSession = context.getSharedPreferences(LAST_DATE,
                Context.MODE_PRIVATE);
        String tab = savedSession.getString(LAST_DATE_KEY, null);
        return tab;
    }

    public static boolean saveLastActivity(int tab) {
        final SharedPreferences.Editor editor = context.getSharedPreferences(LAST_ACTIVITY,
                Context.MODE_PRIVATE).edit();
        editor.putInt(LAST_ACTIVITY_KEY, tab);
        return editor.commit();
    }

    public static Integer restoreLastActivity() {
        final SharedPreferences savedSession = context.getSharedPreferences(LAST_ACTIVITY,
                Context.MODE_PRIVATE);
        int tab = savedSession.getInt(LAST_ACTIVITY_KEY, AppConstants.MAIN_PAGE);
        return tab;
    }


    public static boolean saveLastTrackID(int trackId) {
        final SharedPreferences.Editor editor = context.getSharedPreferences(LAST_TRACK_ID,
                Context.MODE_PRIVATE).edit();
        editor.putInt(LAST_TRACK_ID_KEY, trackId);
        return editor.commit();
    }

    public static Integer restoreLastTrackID() {
        final SharedPreferences savedSession = context.getSharedPreferences(LAST_TRACK_ID,
                Context.MODE_PRIVATE);
        int trackId = savedSession.getInt(LAST_TRACK_ID_KEY, -1);
        return trackId;
    }

}
