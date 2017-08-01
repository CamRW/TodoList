package Model;

import io.realm.RealmObject;

/**
 * Created by cameronweigel on 7/15/17.
 */

public class Task extends RealmObject {

   // @PrimaryKey private long id;
    private String taskTitle;
    private String taskBody;
    // private RealmList<Task> tasks;

    public Task() {

    }

    public Task(String taskTitle, String taskBody) {
        // this.id = id;
        this.taskTitle = taskTitle;
        this.taskBody = taskBody;
        // this.tasks = tasks;
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

    /*
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /*

    public RealmList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(RealmList<Task> tasks) {
        this.tasks = tasks;
    }
    */


}
