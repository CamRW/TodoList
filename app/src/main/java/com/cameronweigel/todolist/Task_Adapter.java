package com.cameronweigel.todolist;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import Model.Task;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import layout.DeleteDialog;

import static layout.DeleteDialog.newInstance;

/**
 * Created by cameronweigel on 7/15/17.
 */

public class Task_Adapter extends RealmRecyclerViewAdapter<Task, Task_Adapter.TaskViewHolder> {

    private OrderedRealmCollection<Task> taskData;
    private Context context;
    private FragmentManager fragmentManager;
    private int pos;

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
    public void onBindViewHolder(final TaskViewHolder holder,final int position) {
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                Log.d("InsideLongClick",holder.taskTitle.getText().toString());
                //TODO add alert dialog
                pos = holder.getAdapterPosition();
                newInstance(pos);
                DeleteDialog deleteDialog = new DeleteDialog();
                deleteDialog.show(fragmentManager, "DeleteDialog");
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

    public void removeItem() {


    }


}
