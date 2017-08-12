package Presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.cameronweigel.todolist.R;

import layout.NoListFragment;

/**
 * Created by Cameron on 8/11/2017.
 */

public class FragmentPresenter {

    public static void noListFragmentPresenter(Fragment fragment) {

        FragmentManager fragmentManager = fragment.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        NoListFragment noListFragment = new NoListFragment();

        fragmentTransaction.replace(R.id.fragment_placeholder, noListFragment, "noListFragment");

        fragmentTransaction.commit();

    }

    public static void noListFragmentPresenter(AppCompatActivity activity) {

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        NoListFragment noListFragment = new NoListFragment();

        fragmentTransaction.replace(R.id.fragment_placeholder, noListFragment, "noListFragment");
        fragmentTransaction.commit();

    }

    public static void listFragmentPresenter(Fragment fragment) {

        FragmentManager fragmentManager = fragment.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ListFragment ListFragment = new ListFragment();

        fragmentTransaction.replace(R.id.fragment_placeholder, ListFragment, "listFragment");

        fragmentTransaction.commit();

    }

    public static void listFragmentPresenter(AppCompatActivity activity) {

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ListFragment ListFragment = new ListFragment();

        fragmentTransaction.replace(R.id.fragment_placeholder, ListFragment, "listFragment");
        fragmentManager.saveFragmentInstanceState(ListFragment);

        fragmentTransaction.commit();

    }

    public static void listFragmentResume(AppCompatActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //fragmentTransaction.hide(fragmentManager.)
        }
    }





}
