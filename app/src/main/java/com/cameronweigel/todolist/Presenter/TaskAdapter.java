package com.cameronweigel.todolist.Presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;

import com.cameronweigel.todolist.Model.Task;
import com.cameronweigel.todolist.R;
import com.cameronweigel.todolist.View.DeleteDialog;
import com.cameronweigel.todolist.View.TaskFragment;


/**
 * Created by cameronweigel on 7/15/17.
 */

public class TaskAdapter extends RealmRecyclerViewAdapter<Task, TaskAdapter.TaskViewHolder> {

    @BindView(R.id.titleTextView) TextView titleTextView;
    @BindView(R.id.descriptionTextView) TextView descriptionTextView;

    private int itemPos;

    private OrderedRealmCollection<Task> taskData;

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView taskTitle, taskBody;

        public TaskViewHolder(final View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.titleTextView);
            taskBody =  itemView.findViewById(R.id.descriptionTextView);


        }

    }


    public TaskAdapter(final OrderedRealmCollection<Task> realmResults) {
        super(realmResults, true);
        this.taskData = realmResults;


    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_row, parent, false);

        return new TaskViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final TaskViewHolder holder, final int position) {


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                itemPos = holder.getAdapterPosition();
                Log.d("InsideLongClick", "Long click");
                //TODO add alert dialog

                if (getItemCount() == 1) {
                    removeItem(itemPos);
                    //TODO send to empty frag
                    return  true;
                }
                removeItem(itemPos);

                return true;
            }
        });

        Task item = getItem(position);
        if (item != null) {
            holder.taskTitle.setText(item.getTitle());
            holder.taskBody.setText(item.getDescription());
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
            taskData = realm.where(Task.class).findAll();
            realm.commitTransaction();
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, taskData.size());
            realm.close();

        }
    }




}
