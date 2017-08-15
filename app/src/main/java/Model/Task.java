package Model;

import android.app.Activity;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.cameronweigel.todolist.R;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import layout.ListFragment;

/**
 * Created by cameronweigel on 7/15/17.
 */

public class Task extends RealmObject {


    @PrimaryKey
    private long timeStamp;


    private String taskTitle;

    private String taskBody;


    public Task() {

    }


    public Task(long timeStamp, String taskTitle, String taskBody) {
        this.timeStamp = timeStamp;
        this.taskTitle = taskTitle;
        this.taskBody = taskBody;
    }


    /** Getters and Setters **/

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskBody() {
        return taskBody;
    }

    public void setTaskBody(String taskBody) {
        this.taskBody = taskBody;
    }

    /** Database managers **/

    public void taskUpdate() {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {

            @Override
            public void execute(Realm asRealm) {
                Task task = new Task(getTimeStamp(),getTaskTitle(),getTaskBody());
                asRealm.insertOrUpdate(task);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d("onSuccess", "Realm Async Transaction");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d("onError", error.toString());
            }
        });
    }

    public static RealmResults<Task> taskListQuery() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Task.class).findAllSorted("timeStamp");
    }

    public void taskDelete() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {

            @Override
            public void execute(Realm asRealm) {
                RealmResults<Task> deleteTask = asRealm.where(Task.class).equalTo("timeStamp",getTimeStamp()).findAllSorted("timeStamp");
                deleteTask.deleteAllFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d("onSuccess", "Realm Async Transaction");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d("onError", error.toString());
            }
        });



    }

    public static boolean taskListCheck() {
       RealmResults<Task> taskList = Task.taskListQuery();
        if (taskList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }



}
