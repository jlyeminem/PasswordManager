package com.example.jly.passwordmanager.mvp.model.reaml;

import android.content.Context;

import com.example.jly.passwordmanager.mvp.model.bean.Password;

import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class RealmHelper {
    private static RealmHelper instance;
    private Context mContext;

    private RealmHelper(Context context) {
        mContext = context;
    }
    public static RealmHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (RealmHelper.class) {
                if (instance == null) {
                    instance = new RealmHelper(context);
                }
            }
        }
        return instance;
    }

    public static ArrayList<Password> getPasswords (Context context,int pwType) {
        Realm realm = Realm.getInstance(context);
        RealmQuery<Password> realmQuery = realm.where(Password.class);
        RealmQuery<Password> pwRealmQuerr = realmQuery.equalTo("pwType",pwType);
        RealmResults<Password> realmResults = pwRealmQuerr.findAll();
        if(realmResults != null && realmResults.size() > 0) {
            ArrayList<Password> list = new ArrayList<>();
            for (Password pw:realmResults) {
                list.add(pw);
            }
            Collections.reverse(list);
            return list;
        }
        return null;
    }

    public static void addPassword (Context context, Password pw) {
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        realm.copyToRealm(pw);
        realm.commitTransaction();
    }

    public static void deletePassword (Context context,Password pw,int position) {
        Realm realm = Realm.getInstance(context);
        RealmQuery<Password> realmQuery = realm.where(Password.class);
        RealmQuery<Password> pwRealmQuery = realmQuery.equalTo("pwType",pw.getPwType());
        RealmResults<Password> realmResults = pwRealmQuery.findAll();
        if(realmResults != null) {
            int size = realmResults.size() - 1;
            int t = size - position;
            realm.beginTransaction();
            realmResults.remove(t);
            realm.commitTransaction();
        }
    }
}
