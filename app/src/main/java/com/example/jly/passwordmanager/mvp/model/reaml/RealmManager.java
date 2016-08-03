package com.example.jly.passwordmanager.mvp.model.reaml;

import android.content.Context;

import com.example.jly.passwordmanager.mvp.model.bean.Password;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class RealmManager {
    private static RealmManager instances;
    private Context mContext;

    private RealmManager(Context context){
        mContext = context;
    }
    public static RealmManager getInstances(Context context){
        synchronized (RealmManager.class) {
            if (instances == null) {
                instances = new RealmManager(context);
            }
        }
        return instances;
    }

    private static void closeConnect(Realm realm) {
        if (null != realm) {
            try {
                realm.close();
                realm = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static RealmConfiguration secure(Context context) {
        byte[] key = new byte[64];
        new SecureRandom().nextBytes(key);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(context)
                .encryptionKey(key)
                .build();

        // Start with a clean slate every time
        Realm.deleteRealm(realmConfiguration);
        return realmConfiguration;
    }
    public static ArrayList<Password> selector(Context context, int pwType){
        Realm realm = Realm.getInstance(context);
        RealmQuery<Password> realmQuery = realm.where(Password.class);
        RealmQuery<Password> godRealmQuery = realmQuery.equalTo("pwType", pwType);
        RealmResults<Password> realmResults = godRealmQuery.findAll();
        if (realmResults != null && realmResults.size() > 0) {
            ArrayList<Password> godList = new ArrayList<>();
            for (Password Password : realmResults) {
                godList.add(Password);
            }
            Collections.reverse(godList);
            return godList;
        }
        return null;
    }

    public static boolean save(Context context, Password Password) {
        if (check(context, Password)) {
            return true;
        }
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        realm.copyToRealm(Password);
        realm.commitTransaction();
        return false;
    }

    private static boolean check(Context context, Password Password) {
        int pwType = Password.getPwType();
        Realm realm = Realm.getInstance(context);
        RealmQuery<Password> realmQuery = realm.where(Password.class);
        RealmQuery<Password> godRealmQuery = realmQuery.equalTo("pwType", pwType);
        RealmResults<Password> title = godRealmQuery.contains("title", Password.getTitle()).findAll();
        if (title!=null&&title.size()>0) {
            return true;
        }
        return false;
    }

    /**
     * 更新数据库
     * @param context 上下文
     * @param Password bean
     * @return 成功返回true
     */
    public static boolean update(Context context, Password Password) {
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(Password);
        realm.commitTransaction();
        return true;
    }

    public static void delete(Context context, Password Password, int position) {
        Realm realm = Realm.getInstance(context);
        RealmQuery<Password> realmQuery = realm.where(Password.class);
        RealmQuery<Password> godRealmQuery = realmQuery.equalTo("pwType", Password.getPwType());
        RealmResults<Password> realmResults = godRealmQuery.findAll();
        if (realmResults != null ) {
            int size = realmResults.size() - 1;
            int i = size - position;
            realm.beginTransaction();
            realmResults.remove(i);
            realm.commitTransaction();
        }
    }
}
