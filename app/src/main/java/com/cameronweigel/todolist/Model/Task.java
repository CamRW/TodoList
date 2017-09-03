package com.cameronweigel.todolist.Model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by cameronweigel on 7/15/17.
 */

public class Task extends RealmObject {

    // TODO set up task model to be primarily a data holder, no data handling should happen here
    // see Tim's email for class vars


    @PrimaryKey
    private String id;

    @Nullable
    private String title;

    @Nullable
    private String description;

    private boolean complete;
    private long createdAt;
    private long updatedAt;

    public Task() {

    }


    public Task(@Nullable String title, @Nullable String description){
        this(UUID.randomUUID().toString(), title, description,  false, System.currentTimeMillis(), System.currentTimeMillis());
    }


    public Task(@NonNull String mId, @Nullable String mTitle, @Nullable String mDescription , boolean mComplete, Long mCreatedAt, Long mUpdatedAt) {
        id = mId;
        title = mTitle;
        description = mDescription;
        complete = mComplete;
        createdAt = mCreatedAt;
        updatedAt = mUpdatedAt;
    }


    public String getId() {
        return id;
    }

    public boolean isComplete() {
        return complete;
    }


    public long getCreatedAt() {
        return createdAt;
    }


    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Nullable
    public String getTitle() {
        return title;
    }


    @Nullable
    public String  getDescription() {
        return description;
    }

    public boolean isEmpty() {
        return (title == null && description == null);
    }



}
