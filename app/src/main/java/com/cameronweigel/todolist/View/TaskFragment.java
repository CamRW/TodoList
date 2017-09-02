package com.cameronweigel.todolist.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.cameronweigel.todolist.R;

import com.cameronweigel.todolist.Model.Task;

import butterknife.BindView;


/** Meant to implement Editing features on a task **/


public class TaskFragment extends Fragment {

    @BindView(R.id.taskTitleEditText)
    EditText taskTitleEditText;


    @BindView(R.id.taskDescriptionEditText)
    EditText taskDescriptionEditText;


    public TaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false);

    }
}