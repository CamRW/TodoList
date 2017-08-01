package layout;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.cameronweigel.todolist.R;
import com.cameronweigel.todolist.Task;
import com.cameronweigel.todolist.Task_Adapter;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    protected RecyclerView recyclerView;
    protected RealmList<Task> taskRealmList;



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
        Context context = getActivity();

        final View view = inflater.inflate(R.layout.fragment_list, container, false);

        Realm.init(context);

        Realm realm = Realm.getDefaultInstance();

        final RealmResults<Task> tasks = realm.where(Task.class).findAll();


        //TextView textView = view.findViewById(R.id.listTextView);

        // textView.setText(tasks.get(0).getTaskBody());

        recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());

        Task_Adapter task_adapter = new Task_Adapter(tasks, getActivity(), getFragmentManager());

        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(task_adapter);
        recyclerView.setHasFixedSize(true);

        //realm.close();




        /*
        for (int i = 0; i < tasks.size(); i++) {
            // Add tasks(i) to RecyclerView

        }
        */






        /*
        Task t1 = new Task("title1", "body1");
        Task t2 = new Task("title2", "body2");
        Task t3 = new Task("title3", "body3");

        taskData.add(t1);
        taskData.add(t2);
        taskData.add(t3);

        */

       // LinearLayoutManager llm = new LinearLayoutManager(getActivity());
       // RecyclerView rv = (RecyclerView) view.findViewById(R.id.taskRecyclerView);




        // Inflate the layout for this fragment
        return view;
    }



}
