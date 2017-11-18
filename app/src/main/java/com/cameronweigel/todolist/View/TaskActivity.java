package com.cameronweigel.todolist.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.cameronweigel.todolist.Model.Task;
import com.cameronweigel.todolist.Presenter.TaskPresenter;
import com.cameronweigel.todolist.Presenter.TasksContract;
import com.cameronweigel.todolist.R;

import java.util.List;

public class TaskActivity extends AppCompatActivity implements TasksContract.View{

    @BindView(R.id.toolbar)
    Toolbar myToolbar;

    @BindView(R.id.addTaskFab)
    FloatingActionButton fab;

    @Nullable
    private TaskPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new TaskPresenter(this);

        setContentView(R.layout.activity_task);
        //Realm.deleteRealm(Realm.getDefaultConfiguration());
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
        presenter = null;
    }


    @OnClick(R.id.addTaskFab)
    public void addTaskFab() {
        presenter.addNewTask();
    }

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        //
    }

    @Override
    public void showTasks(List<Task> tasks) {

    }

    @Override
    public void showAddTask() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_placeholder, AddTaskFragment.newInstance(), "AddTaskFragment")
                .addToBackStack("TaskListFragment")
                .commit();
        fab.hide();

    }

}
