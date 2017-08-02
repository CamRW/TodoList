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
import io.realm.Realm;
import io.realm.RealmResults;
import layout.ListFragment;
import layout.NoListFragment;
import layout.TaskItemFragment;

public class TaskActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Realm.init(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);



        Task t1 = new Task("task1","body1");
        Task t2 = new Task("task2","body2");

        t1.taskUpdate();
        t2.taskUpdate();




        RealmResults<Task> tasks = Task.taskListQuery();



        if (tasks.size() > 0) {

            Toast.makeText(this,"tasks.size() > 0", Toast.LENGTH_LONG).show();


            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            ListFragment listFragment = new ListFragment();
            fragmentTransaction.add(R.id.fragment_placeholder, listFragment);

            fragmentTransaction.commit();

        }

        else {

            Toast.makeText(this,"tasks.size() not read or < 0", Toast.LENGTH_LONG).show();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            NoListFragment noListFragment = new NoListFragment();

            fragmentTransaction.add(R.id.fragment_placeholder, noListFragment);

            fragmentTransaction.commit();

            Log.d("NoListFragment", "debug");

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
    protected void onPause() {

        super.onPause();
    }

}
