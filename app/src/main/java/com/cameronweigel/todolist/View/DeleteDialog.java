package com.cameronweigel.todolist.View;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.cameronweigel.todolist.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteDialog extends DialogFragment {

    public interface DeleteDialogListener {
        void onDialogPositiveClick(DeleteDialog dialog, int position);
        void onDialogNegativeClick(DeleteDialog dialog);
    }

    DeleteDialogListener dListener;

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        try {
            dListener = (DeleteDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement DeleteDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialogTitle)
                .setPositiveButton(R.string.deleteDialogButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        Log.d("DeleteDialog", "Delete Button Pressed");


                        dListener.onDialogPositiveClick(DeleteDialog.this, position);
                        //TODO Delete task

                    }
                })
                .setNegativeButton(R.string.cancelDialogButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("DeleteDialog", "Cancel Button Pressed");
                        dListener.onDialogNegativeClick(DeleteDialog.this);
                    }
                });
        return builder.create();
    }





}
