package layout;


import android.app.AlertDialog;
import android.app.Dialog;
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


    public static DeleteDialog newInstance(int position) {
        DeleteDialog frag = new DeleteDialog();
        Bundle args = new Bundle();
        args.putInt("position", position);
        frag.setArguments(args);
        return frag;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int pos = getArguments().getInt("position");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialogTitle)
                .setPositiveButton(R.string.deleteDialogButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("DeleteDialog", "Delete Button Pressed");
                        //TODO use pos somehow to delete the item from the recyclerview

                        //TODO Delete task

                    }
                })
                .setNegativeButton(R.string.cancelDialogButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("DeleteDialog", "Cancel Button Pressed");
                    }
                });
        return builder.create();
    }





}
