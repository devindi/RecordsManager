package com.devindi.records;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CallsListFragment extends ListFragment {
    List<Call> files = new ArrayList<Call>();;
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
    int type;

    /**
    *
    * @param type 0 - incoming calls,
    *             1 - outgoing calls
    */
    public static CallsListFragment newInstance(int type){
        CallsListFragment fragment = new CallsListFragment();
        fragment.type = type;
        return fragment;
    }


    private Call parse(String name) {
        int start = name.lastIndexOf("_")+1;
        String number = name.substring(start, name.length()-4);
        return new Call(number,
                name.substring(4, 6) + "/" + name.substring(2, 4) + "/" + name.substring(0, 2),
                name.substring(7, 9) + ":" + name.substring(9, 11), name);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CallsAdapter adapter = new CallsAdapter(files, getActivity());
        setListAdapter(adapter);
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
                showDialog(view.getTag().toString());
                return true;
            }
        });
        this.updateData();
    }

    private void showDialog(String fileName) {
        ConfirmDeleteDialog confirmDialog = ConfirmDeleteDialog.newInstance(fileName, this);
        confirmDialog.show(getFragmentManager(), "confirmDialog");
    }

    void updateData(){
        files.clear();
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
        ((CallsAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        String name = view.getTag().toString();
        Uri playUri = Uri.parse("file:/"+Environment.getExternalStorageDirectory().toString() + "/Sound/" + name);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(playUri, "audio/*");
        startActivity(intent);
    }
}
