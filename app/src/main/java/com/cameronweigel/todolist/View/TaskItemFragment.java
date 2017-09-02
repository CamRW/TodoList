package com.cameronweigel.todolist.View;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.cameronweigel.todolist.R;

import com.cameronweigel.todolist.Model.Task;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskItemFragment extends Fragment {

    @BindView(R.id.titleEditText) EditText titleEditText;
    @BindView(R.id.descriptionEditText) EditText descriptionEditText;

    @Nullable
    @BindView(R.id.addTaskFab) FloatingActionButton fab;

    private String TAG = "TaskItemFragment";

    public TaskItemFragment() {
        // Required empty public constructor
    }

    public static TaskItemFragment newInstance() {

        Bundle args = new Bundle();

        TaskItemFragment fragment = new TaskItemFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task_item, container, false);
        ButterKnife.bind(this,view);

        // Inflate the layout for this fragment
        getActivity().setTitle("Add Task");
        //fab.setVisibility(View.GONE);

        Log.d(TAG, "Inflate Layout");

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().setTitle("Todo List");
        //fab.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.actionbar,menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean buttonClick;
        switch(item.getItemId()){
            case R.id.saveActionBarButton:
                buttonClick = onCheckButtonClick();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public boolean onCheckButtonClick() {

        Task task = new Task();

        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();


    if (!(title.isEmpty() && description.isEmpty())) {

        task.setId(UUID.randomUUID().toString());
        task.setCreatedAt(System.currentTimeMillis());
        task.setUpdatedAt(System.currentTimeMillis());
        task.setComplete(false);
        task.setTitle(titleEditText.getText().toString());
        task.setDescription(descriptionEditText.getText().toString());

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.insertOrUpdate(task);
        realm.commitTransaction();
        realm.close();

        Log.d(TAG, "After Realm commit");

        return true;

    } else {
        Toast.makeText(getActivity(), "Title and Body must not be empty", Toast.LENGTH_LONG).show();
        return false;
    }

    }
}