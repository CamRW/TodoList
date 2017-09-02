package com.cameronweigel.todolist;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Cameron on 8/26/2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
