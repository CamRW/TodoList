package com.cameronweigel.todolist.View;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.cameronweigel.todolist.R;

import com.cameronweigel.todolist.Model.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment {

    @BindView(R.id.titleEditText)
    EditText titleEditText;

    @BindView(R.id.descriptionEditText)
    EditText descriptionEditText;

    FloatingActionButton fab;

    private Realm realm;

    private String TAG = "AddTaskFragment";

    public AddTaskFragment() {
        // Required empty public constructor
    }

    public static AddTaskFragment newInstance() {

        Bundle args = new Bundle();

        AddTaskFragment fragment = new AddTaskFragment();
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
        realm = Realm.getDefaultInstance();
        View view = inflater.inflate(R.layout.fragment_taskitem, container, false);
        ButterKnife.bind(this,view);

        getActivity().setTitle("Add Task");

        Log.d(TAG, "Inflate Layout");

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().setTitle("Todo List");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.actionbar,menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.saveActionBarButton:
                onCheckButtonClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void onCheckButtonClick() {

        InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);


    if (!(titleEditText.getText().toString().isEmpty() && descriptionEditText.getText().toString().isEmpty())) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Task task = new Task(titleEditText.getText().toString(),descriptionEditText.getText().toString());
                bgRealm.insertOrUpdate(task);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

            }
        });

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_placeholder, fm.findFragmentByTag("TaskListFragment"))
                .commit();

        fab = getActivity().findViewById(R.id.addTaskFab);
        fab.show();

        Log.d(TAG, "After Realm commit");

    } else {
        Toast.makeText(getActivity(), "Title and Body must not be empty", Toast.LENGTH_LONG).show();
    }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }
}
