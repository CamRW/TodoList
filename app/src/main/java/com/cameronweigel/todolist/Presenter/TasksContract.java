package com.cameronweigel.todolist.Presenter;

import com.cameronweigel.todolist.Model.Task;
import com.cameronweigel.todolist.View.BaseView;

import java.util.List;

/**
 * Created by Cameron on 9/3/2017.
 */

public interface TasksContract {

    interface View extends BaseView<Presenter> {

        void showTasks(List<Task> tasks);

        void showAddTask();

        void showTaskList();

    }

    interface Presenter extends BasePresenter {

        void addNewTask();

        void addTaskList();

    }
}
