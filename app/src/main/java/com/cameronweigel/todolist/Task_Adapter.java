package com.cameronweigel.todolist;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import Model.Task;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import layout.DeleteDialog;


/**
 * Created by cameronweigel on 7/15/17.
 */

public class Task_Adapter extends RealmRecyclerViewAdapter<Task, Task_Adapter.TaskViewHolder> implements DeleteDialog.DeleteDialogListener {

    private int itemPos;

    private OrderedRealmCollection<Task> taskData;
    private Context context;
    private FragmentManager fragmentManager;
   // private int pos;

    public void showDeleteDialog() {
        DeleteDialog dialog = new DeleteDialog();
        dialog.show(fragmentManager, "DeleteDialog");
    }

    @Override
    public void onDialogPositiveClick(DeleteDialog dialog, int position) {
        removeItem(position);
        dialog.dismiss();

    }
    @Override
    public void onDialogNegativeClick(DeleteDialog dialog) {
        dialog.dismiss();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView taskTitle, taskBody;



        public TaskViewHolder(final View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.titleTextView);
            taskBody =  itemView.findViewById(R.id.bodyTextView);


        }

    }


    public Task_Adapter(final OrderedRealmCollection<Task> realmResults, Context context, FragmentManager fragmentManager) {
        super(realmResults, true);
        this.taskData = realmResults;
        this.context = context;
        this.fragmentManager = fragmentManager;


    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_row, parent, false);

        return new TaskViewHolder(v);

    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, final int position) {

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                Log.d("InsideLongClick", "Long click");
                //TODO add alert dialog

                if (getItemCount() == 1) {
                    removeItem(itemPos);
                    //TODO send to empty frag
                }
                removeItem(itemPos);

                return true;
            }
        });

        Task item = getItem(position);
        if (item != null) {
            holder.taskTitle.setText(item.getTaskTitle());
            holder.taskBody.setText(item.getTaskBody());
        }

    }

    public int getItemCount() {
        return taskData.size();
    }

    private void removeItem(int position) {

        if (taskData.size() > 0) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            taskData.deleteFromRealm(position);
            taskData = Task.taskListQuery();
            //taskData.remove(position);
            realm.commitTransaction();
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, taskData.size());
        }
    }


}
