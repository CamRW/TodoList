package layout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cameronweigel.todolist.R;
import com.cameronweigel.todolist.Task_Adapter;

import Model.Task;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

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


        final RealmResults<Task> tasks = Task.taskListQuery();


        recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());

        Task_Adapter task_adapter = new Task_Adapter(tasks, getActivity(), getFragmentManager());

        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(task_adapter);
        recyclerView.setHasFixedSize(true);

        return view;
    }



}
