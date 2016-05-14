package com.example.android_realm;

import android.app.Application;

import com.example.android_realm.db.RealmDB;

/**
 * Created by docotel on 5/12/16.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RealmDB realmDB = new RealmDB(this);
        realmDB.initWithDeleteIfMigrationNeeded();
    }
}
