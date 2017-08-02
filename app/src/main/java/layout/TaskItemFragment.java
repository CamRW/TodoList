package layout;


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

import com.cameronweigel.todolist.R;

import Model.Task;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskItemFragment extends Fragment {

    private EditText taskTitle;
    private EditText taskBody;

    private String TAG = "TaskItemFragment";

    public TaskItemFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FloatingActionButton fab = getActivity().findViewById(R.id.addTaskFab);
        fab.hide();

        return inflater.inflate(R.layout.fragment_task_item, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();
        FloatingActionButton fab = getActivity().findViewById(R.id.addTaskFab);
        fab.show();
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
                getFragmentManager().popBackStack();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void onCheckButtonClick() {

        Task task = new Task();
        taskTitle = getActivity().findViewById(R.id.editTextTitle);
        taskBody = getActivity().findViewById(R.id.editTextBody);

        task.setTaskTitle(taskTitle.getText().toString());
        task.setTaskBody(taskBody.getText().toString());

        Log.d("task check", task.getTaskBody() + task.getTaskTitle());

        task.taskUpdate();

       /* Realm.init(getActivity());

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        realm.insertOrUpdate(task);

        realm.commitTransaction(); */

        Log.d(TAG, "After Realmcommit");


        //realm.close();




    }
}
