package com.cameronweigel.todolist.View;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.cameronweigel.todolist.Model.Task;
import com.cameronweigel.todolist.R;
import com.cameronweigel.todolist.Presenter.TaskAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskListFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.noListLayout)
    FrameLayout noListLayout;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    //@BindView(R.id.swipe_refresh_layout)





    public TaskListFragment() {

    }

    public static TaskListFragment newInstance() {

        Bundle args = new Bundle();

        TaskListFragment fragment = new TaskListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // realm = Realm.getDefaultInstance();
       // realm.beginTransaction();

       // results = realm.where(Task.class).findAll();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        OrderedRealmCollection<Task> results = realm.where(Task.class).findAllAsync();

        realm.commitTransaction();

        if (results == null) {
            noListLayout.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setVisibility(View.GONE);
        } else {
            noListLayout.setVisibility(View.GONE);

            LinearLayoutManager llm = new LinearLayoutManager(getContext());

            TaskAdapter task_adapter = new TaskAdapter(results);

            recyclerView.setLayoutManager(llm);
            recyclerView.setAdapter(task_adapter);
            recyclerView.setHasFixedSize(true);


            swipeRefreshLayout.setVisibility(View.VISIBLE);
        }

        // If results != empty, hide empty list view, show recyclerview




        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout = getActivity().findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("Swipe Refresh", "onRefresh");

                //Realm realm = Realm.getDefaultInstance();
                //realm.beginTransaction();

                //refreshRealm = realm.where(Task.class).findAll();
                //realm.close();

                if (true) {
                    Toast.makeText(getActivity(), "Tasks exist", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    Toast.makeText(getActivity(), "Tasks don't exist", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
