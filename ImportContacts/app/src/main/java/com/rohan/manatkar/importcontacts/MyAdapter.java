package com.rohan.manatkar.importcontacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<DataClass> dataClassArrayList;

    public MyAdapter(Context context, ArrayList<DataClass> dataClassArrayList) {
       this.context = context;
       this.dataClassArrayList = dataClassArrayList;
    }

    @Override
    public Object getItem(int position) {
        return dataClassArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return dataClassArrayList.size();
    }

    private static class ViewHolder {
        protected TextView tv_name, tv_number;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_view_item, viewGroup, false);

            viewHolder.tv_name = (TextView) view.findViewById(R.id.name);
            viewHolder.tv_number = (TextView) view.findViewById(R.id.number);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tv_name.setText(dataClassArrayList.get(position).getName());
        viewHolder.tv_number.setText(dataClassArrayList.get(position).getNumber());

        return view;
    }
}
