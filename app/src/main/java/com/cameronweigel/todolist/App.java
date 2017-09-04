package com.cameronweigel.todolist;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by Cameron on 8/26/2017.
 */

@Module
public class App extends Application {

    Application mApplication;

    //public AppModule(Application application) {
     //   mApplication = application;
    //}

    @Provides
    @Singleton
    Application providesApplication(){
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
