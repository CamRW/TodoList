package Model;

import android.util.Log;

import io.reactivex.Flowable;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by cameronweigel on 7/15/17.
 */

public class Task extends RealmObject {

    private String taskTitle;
    private String taskBody;

    public Task() {

    }

    public Task(String taskTitle, String taskBody) {
        this.taskTitle = taskTitle;
        this.taskBody = taskBody;
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

    public void taskUpdate() {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {

            @Override
            public void execute(Realm asRealm) {
                Task task = new Task(getTaskTitle(),getTaskBody());
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
        return realm.where(Task.class).findAll();
    }



}
