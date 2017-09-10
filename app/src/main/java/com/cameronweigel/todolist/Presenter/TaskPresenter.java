package com.cameronweigel.todolist.Presenter;

import com.cameronweigel.todolist.Model.Task;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;

/**
 * Created by Cameron on 9/3/2017.
 */

public class TaskPresenter implements TasksContract.Presenter {

    private final TasksContract.View mTasksView;

    public void addNewTask(){



    }


    public TaskPresenter(TasksContract.View mTasksView) {
        this.mTasksView = mTasksView;

        mTasksView.setPresenter(this);
    }

    public void start() {
    }


    public static OrderedRealmCollection<Task> getRealmResults() {

        OrderedRealmCollection<Task> tasks;
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        tasks = realm.where(Task.class).findAll();
        realm.commitTransaction();

        return tasks;
    }


}
