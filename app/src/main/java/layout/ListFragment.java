package layout;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cameronweigel.todolist.R;
import com.cameronweigel.todolist.Task_Adapter;

import Model.Task;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    private SwipeRefreshLayout mySwipeRefreshLayout;

    protected RecyclerView recyclerView;


    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_list, container, false);

        mySwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);


        RealmResults<Task> tasks = Task.taskListQuery();


        recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());

        Task_Adapter task_adapter = new Task_Adapter(tasks, getActivity(), getFragmentManager());

        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(task_adapter);
        recyclerView.setHasFixedSize(true);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i("Swipe Refresh", "onRefresh");
                Activity activity = getActivity();
                if (!Task.taskListCheck()) {
                    Toast.makeText(activity,"tasks.size() not read or < 0", Toast.LENGTH_LONG).show();

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    NoListFragment noListFragment = new NoListFragment();

                    fragmentTransaction.replace(R.id.fragment_placeholder, noListFragment);

                    fragmentTransaction.commit();

                    Log.d("NoListFragment", "debug");
                    mySwipeRefreshLayout.setRefreshing(false);
                } else {
                    Toast.makeText(activity, "Refreshed", Toast.LENGTH_LONG).show();
                    mySwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }
}
