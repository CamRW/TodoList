package com.cameronweigel.todolist.View;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cameronweigel.todolist.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoListFragment extends Fragment {


    public NoListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_nolist, container, false);

        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
    }
}
