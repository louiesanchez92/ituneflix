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

    private static final String LAST_TAB = "last_tab";
    private static final String LAST_TAB_KEY = "last_tab_key";

    public static boolean saveLastTab(int tab) {
        final SharedPreferences.Editor editor = context.getSharedPreferences(LAST_TAB,
                Context.MODE_PRIVATE).edit();
        editor.putInt(LAST_TAB_KEY, tab);
        return editor.commit();
    }

    public static Integer restoreLastTab() {
        final SharedPreferences savedSession = context.getSharedPreferences(LAST_TAB,
                Context.MODE_PRIVATE);
        int tab = savedSession.getInt(LAST_TAB_KEY, AppConstants.TOP_TAB);
        return tab;
    }

}
