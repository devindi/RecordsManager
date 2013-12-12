package com.devindi.records;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CallsAdapter extends BaseAdapter {
    List<Call> files;
    LayoutInflater inflater;
    Call current;

    public CallsAdapter(List<Call> files, Context context) {
        this.files = files;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return files.size();
    }

    @Override
    public Object getItem(int i) {
        return files.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = inflater.inflate(R.layout.call_item, parent, false);
        current = files.get(position);
        assert view != null;
        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView dateView = (TextView) view.findViewById(R.id.date);
        TextView timeView = (TextView) view.findViewById(R.id.time);
        nameView.setText(current.getNumber());
        dateView.setText(current.getDate());
        timeView.setText(current.getTime());
        view.setTag(current.getFile());
        return view;
    }
}
