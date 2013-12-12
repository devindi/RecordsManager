package com.devindi.records;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import java.io.File;

public class ConfirmDeleteDialog extends DialogFragment implements DialogInterface.OnClickListener {
    private String fileName;
    private CallsListFragment parent;

    public static ConfirmDeleteDialog newInstance(String fileName, CallsListFragment parent) {
        ConfirmDeleteDialog myFragment = new ConfirmDeleteDialog();
        myFragment.setFileName(fileName);
        myFragment.setParent(parent);
        return myFragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.confirm_delete)
                .setPositiveButton(android.R.string.yes, this)
                .setNegativeButton(android.R.string.no, this);
        return adb.create();
    }


    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        switch (which){
            case DialogInterface.BUTTON_POSITIVE:
                String root_sd = Environment.getExternalStorageDirectory().toString();
                File file = new File( root_sd + "/Sound/" + fileName);
                if(file.delete()){
                    Toast.makeText(getActivity(), "Deleted "+fileName, Toast.LENGTH_SHORT).show();
                    parent.updateData();
                }
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                break;
        }
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setParent(CallsListFragment parent) {
        this.parent = parent;
    }
}
