package com.cameronweigel.todolist.View;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

import com.cameronweigel.todolist.R;

public class TaskActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar myToolbar;

    @BindView(R.id.addTaskFab)
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task);
        Realm.deleteRealm(Realm.getDefaultConfiguration());
        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_placeholder, TaskListFragment.newInstance(), "TaskListFragment")
                .commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick(R.id.addTaskFab)
    public void addTaskFab() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_placeholder, TaskItemFragment.newInstance(), "TaskItemFragment")
                .addToBackStack("TaskListFragment")
                .commit();
        fab.hide();

    }

}
