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
import io.realm.Realm;



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

    private Realm realm;

    String TAG = "TaskListFragment";



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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        realm = Realm.getDefaultInstance();
        final View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);



        Log.d(TAG, "onCreateView1");
        if (realm.isEmpty()) {
            noListLayout.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setVisibility(View.GONE);
        } else {
            noListLayout.setVisibility(View.GONE);

            LinearLayoutManager llm = new LinearLayoutManager(getContext());

            Log.d(TAG, "onCreateView2");

            TaskAdapter task_adapter = new TaskAdapter(realm.where(Task.class).findAllSortedAsync("updatedAt"));

            recyclerView.setLayoutManager(llm);
            recyclerView.setAdapter(task_adapter);
            recyclerView.setHasFixedSize(true);


            swipeRefreshLayout.setVisibility(View.VISIBLE);
        }


        Log.d(TAG, "onCreateView3");

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onViewCreated1");

        swipeRefreshLayout = getActivity().findViewById(R.id.swipe_refresh_layout);

        Log.d(TAG, "onViewCreated2");


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Log.d("Swipe Refresh", "onRefresh");

                if (!(realm.where(Task.class).findAllAsync().isEmpty())) {
                    Toast.makeText(getActivity(), "Tasks exist", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    Toast.makeText(getActivity(), "Tasks don't exist", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                    noListLayout.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setVisibility(View.GONE);
                }

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }
}
