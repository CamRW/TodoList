package com.cameronweigel.todolist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import Model.Task;
import Presenter.FragmentPresenter;
import io.realm.Realm;
import io.realm.RealmResults;
import layout.ListFragment;
import layout.NoListFragment;
import layout.TaskItemFragment;

public class TaskActivity extends AppCompatActivity implements TaskItemFragment.TaskItemFragmentListener {


    @Override
    public void onTaskItemAddition() {
       if(Task.taskListCheck()) {
           FragmentManager fragmentManager = getSupportFragmentManager();
           FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

           ListFragment listFragment = new ListFragment();
           fragmentTransaction.replace(R.id.fragment_placeholder, listFragment);

           fragmentTransaction.commit();
       }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Realm.init(this);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);




        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();


        RealmResults<Task> tasks = Task.taskListQuery();



        if (tasks.size() > 0) {

            Toast.makeText(this,"tasks.size() > 0", Toast.LENGTH_LONG).show();

            FragmentPresenter.listFragmentPresenter(this);

        }

        else {


            FragmentPresenter.noListFragmentPresenter(this);

        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addTaskFab);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                TaskItemFragment taskItemFragment = new TaskItemFragment();

                fragmentTransaction.replace(R.id.fragment_placeholder, taskItemFragment).addToBackStack("taskItemFragment");
                fragmentTransaction.commit();


            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        RealmResults<Task> tasks = Task.taskListQuery();

        if (tasks.size() > 0) {

            FragmentPresenter.listFragmentPresenter(this);

        }

        else {

            FragmentPresenter.noListFragmentPresenter(this);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        RealmResults<Task> tasks = Task.taskListQuery();

        if (tasks.size() > 0) {
            FragmentPresenter.listFragmentPresenter(this);

        }

        else {

            FragmentPresenter.noListFragmentPresenter(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        RealmResults<Task> tasks = Task.taskListQuery();

        if (tasks.size() > 0) {

            FragmentPresenter.listFragmentPresenter(this);

        }

        else {

            FragmentPresenter.noListFragmentPresenter(this);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
