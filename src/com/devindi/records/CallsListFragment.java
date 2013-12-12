package com.devindi.records;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ListFragment;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CallsListFragment extends ListFragment {
    List<Call> files;
    Context context;

    /**
    *
    * @param context app contect
    * @param type 0 - incoming calls,
    *             1 - outgoing calls
    */
    public CallsListFragment(Context context, int type) {
        this.context = context;
        files = new ArrayList<Call>();
        FilenameFilter inFilter = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.contains("IN");
            }
        };
        FilenameFilter outFilter = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.contains("OUT");
            }
        };
        String root_sd = Environment.getExternalStorageDirectory().toString();
        File file = new File( root_sd + "/Sound" );
        List<String> names;
        switch (type){
            case 0:
                names = Arrays.asList(file.list(inFilter));
                break;
            case 1:
                names = Arrays.asList(file.list(outFilter));
                break;
            default:
                names = new ArrayList<String>();
        }
        for(String name : names){
            files.add(parse(name));
        }
    }

    private Call parse(String name) {
        int start = name.lastIndexOf("_")+1;
        String number = name.substring(start, name.length()-4);
        String date = name.substring(0, 6);
        return new Call(number, date, name.substring(7, 9) + ":" + name.substring(9, 11));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CallsAdapter adapter = new CallsAdapter(files, context);
        setListAdapter(adapter);
    }
}
