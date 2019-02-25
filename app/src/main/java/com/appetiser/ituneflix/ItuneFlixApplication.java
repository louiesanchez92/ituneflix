package com.appetiser.ituneflix;

import android.app.Application;

import com.appetiser.ituneflix.api.ApiConstants;
import com.appetiser.ituneflix.db.DBMigration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ItuneFlixApplication extends Application {

    public void onCreate() {
        super.onCreate();

        /**
         * Initialize app sessions
         */
        new AppSessions(this);

        /**
         * Initialize realm
         */
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("mazaya_consumer.realm")
                .schemaVersion(ApiConstants.DB_VERSION)
                .migration(new DBMigration())
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

}
