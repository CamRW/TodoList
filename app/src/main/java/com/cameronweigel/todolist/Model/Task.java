package com.cameronweigel.todolist.Model;

import android.util.Log;

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

    private boolean complete;
    private long createdAt;
    private long updatedAt;
    private String title;
    private String description;


    public Task() {

    }


    public Task(String id, boolean complete, long createdAt, long updatedAt, String title, String description) {
        this.id = id;
        this.complete = complete;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.title = title;
        this.description = description;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
