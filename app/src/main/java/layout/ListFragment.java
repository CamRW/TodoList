package layout;


import android.app.Activity;
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

import com.cameronweigel.todolist.R;
import com.cameronweigel.todolist.TaskAdapter;

import Model.Task;
import Presenter.FragmentPresenter;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    private FrameLayout noListLayout, listLayout;

    private SwipeRefreshLayout mySwipeRefreshLayout;

    protected RecyclerView recyclerView;

    private Fragment fragment;




    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("onCreate", "Before Assignment");
        fragment = this;

        /*
        noListLayout = getActivity().findViewById(R.id.noListLayout);
        listLayout = getActivity().findViewById(R.id.listLayout);
        noListLayout.setVisibility(View.INVISIBLE);
        */

        Log.d("onCreate", "After Assignment");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_list, container, false);

        noListLayout = view.findViewById(R.id.noListLayout);
        listLayout = view.findViewById(R.id.listLayout);


        RealmResults<Task> tasks = Task.taskListQuery();



        if (tasks.size() > 0) {

            noListLayout.setVisibility(View.INVISIBLE);



            mySwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);


            // RealmResults<Task> tasks = Task.taskListQuery();


            recyclerView = view.findViewById(R.id.recyclerView);

            LinearLayoutManager llm = new LinearLayoutManager(getContext());

            TaskAdapter task_adapter = new TaskAdapter(tasks, getActivity(), getFragmentManager());

            recyclerView.setLayoutManager(llm);
            recyclerView.setAdapter(task_adapter);
            recyclerView.setHasFixedSize(true);

            Toast.makeText(getActivity(), "" + getFragmentManager().getBackStackEntryCount(), Toast.LENGTH_LONG).show();
        } else {
            listLayout.setVisibility(View.INVISIBLE);
            noListLayout.setVisibility(View.VISIBLE);

        }

        // Call this in onClickMethod!!
        //bundle = task_adapter.getArguments();



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("Swipe Refresh", "onRefresh");
                if (!Task.taskListCheck()) {
                    //FragmentPresenter.noListFragmentPresenter(fragment);

                    Log.d("NoListFragment", "debug");
                    mySwipeRefreshLayout.setRefreshing(false);
                } else {
                    //Toast.makeText(activity, "Refreshed", Toast.LENGTH_LONG).show();
                    mySwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }


}
