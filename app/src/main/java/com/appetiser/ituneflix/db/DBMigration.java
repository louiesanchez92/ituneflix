package com.appetiser.ituneflix.db;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

public class DBMigration implements RealmMigration {

    /**
     * This is very useful..
     * This class will allow us to add
     * new Object or field in our DB
     * even if the app has the old DB Version
     *
     * @param realm
     * @param oldVersion
     * @param newVersion
     */
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {


    }

}
