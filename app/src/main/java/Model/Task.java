package Model;

import android.util.Log;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by cameronweigel on 7/15/17.
 */

public class Task extends RealmObject {

    private String taskTitle;
    private String taskBody;

    @Inject
    public Task() {

    }

    @Inject
    public Task(String taskTitle, String taskBody) {
        this.taskTitle = taskTitle;
        this.taskBody = taskBody;
    }

    @Inject
    public String getTaskTitle() {
        return taskTitle;
    }
    @Inject
    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }
    @Inject
    public String getTaskBody() {
        return taskBody;
    }
    @Inject
    public void setTaskBody(String taskBody) {
        this.taskBody = taskBody;
    }
    @Inject
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
    @Inject
    public static RealmResults<Task> taskListQuery() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Task.class).findAll();
    }
    @Inject
    public void taskDelete() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {

            @Override
            public void execute(Realm asRealm) {
                RealmResults<Task> deleteTask = asRealm.where(Task.class).equalTo(taskTitle,getTaskTitle()).findAll();
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



}
