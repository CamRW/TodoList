package Model;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by cameronweigel on 8/1/17.
 */

public class DBManager {

    public DBManager() {

    }

    public RealmResults<Task> taskListQuery() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Task.class).findAllAsync();
    }

    public void taskUpdate(final Task task) {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction(){
            @Override
            public void execute(Realm asRealm) {
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
}

