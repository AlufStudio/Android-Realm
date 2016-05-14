package com.example.android_realm;

import android.app.Application;

import com.example.android_realm.db.RealmDB;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by wim on 5/12/16.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RealmDB realmDB = new RealmDB(this);
        realmDB.setMigration(new DataMigration());
    }

    private class DataMigration implements RealmMigration {
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

            //Mengambil schema
            RealmSchema schema = realm.getSchema();

            //membuat schema baru jika versi 0
            if (oldVersion == 0) {
                schema.create("Note")
                        .addField("id", int.class)
                        .addField("note", String.class)
                        .addField("dateModified", String.class);
                oldVersion++;
            }

        }
    }
}
