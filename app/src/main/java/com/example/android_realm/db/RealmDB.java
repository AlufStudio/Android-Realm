package com.example.android_realm.db;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by docotel on 5/10/16.
 */
public class RealmDB {

    private Realm realm;
    private Context context;

    public RealmDB(Context context) {
        this.context = context;
        init(context);
    }

    private void init(Context context) {
        RealmConfiguration config = new RealmConfiguration.Builder(context)
                .name("example.realm")
                .build();

        realm = Realm.getInstance(config);
    }

    public void initWithDeleteIfMigrationNeeded() {
        RealmConfiguration config = new RealmConfiguration.Builder(context)
                .name("example.realm")
                .deleteRealmIfMigrationNeeded()
                .build();

        realm = Realm.getInstance(config);
    }

    public Realm getRealm(){
        return realm;
    }

    @SuppressWarnings("unchecked")
    public <T extends RealmObject> T getById(Class<? extends RealmObject> cls, int id) {
        return (T) this.getRealm().where(cls.asSubclass(RealmObject.class)).equalTo("id", id).findFirst();
    }

    public RealmResults<? extends RealmObject> getAllData(Class<? extends RealmObject> cls) {
        return this.getRealm().where(cls.asSubclass(RealmObject.class)).findAll();
    }

    public void add(RealmObject object) {
        this.getRealm().beginTransaction();
        this.getRealm().copyToRealm(object);
        this.getRealm().commitTransaction();
    }

    public void add(List<RealmObject> listObject) {
        this.getRealm().beginTransaction();
        this.getRealm().copyToRealm(listObject);
        this.getRealm().commitTransaction();
    }

    public void delete(Class<? extends RealmObject> cls, int id) {
        RealmResults results = this.getRealm().where(cls.asSubclass(RealmObject.class)).equalTo("id", id).findAll();
        this.getRealm().beginTransaction();
        results.clear();
        this.getRealm().commitTransaction();
    }

    public void update(RealmObject object){
        this.getRealm().beginTransaction();
        this.getRealm().copyToRealmOrUpdate(object);
        this.getRealm().commitTransaction();
    }
}
