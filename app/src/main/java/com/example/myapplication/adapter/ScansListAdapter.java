package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Scan;
import com.example.myapplication.utilities.Constants;

import java.util.ArrayList;

/**
 * Created by Aakash on 08/05/2019.
 */

public class ScansListAdapter  extends BaseAdapter {

    private Context ctx;
    private ArrayList<Scan> scans = new ArrayList<Scan>();


    public ScansListAdapter(Context context, ArrayList<Scan> objects) {
        this.ctx = context;
        this.scans = objects;
    }

    @Override
    public int getCount() {
        return scans.size();
    }

    @Override
    public Object getItem(int position) {
        return scans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_scan, null);
            ViewHolder holder = new ViewHolder();

            holder.txtScanName = (TextView) convertView.findViewById(R.id.txtScanName);
            holder.txtScanStatus = (TextView) convertView.findViewById(R.id.txtScanStatus);
            convertView.setTag(holder);
        }

        final ViewHolder holder = (ViewHolder) convertView.getTag();


        holder.txtScanName.setText(scans.get(position).getName());
        holder.txtScanStatus.setText(scans.get(position).getTag());

        if(scans.get(position).getColor().equals(Constants.green))
            holder.txtScanStatus.setTextColor(ctx.getResources().getColor(R.color.green));
        else
            holder.txtScanStatus.setTextColor(ctx.getResources().getColor(R.color.red));

        return convertView;

    }


    private class ViewHolder {
        TextView txtScanName, txtScanStatus;
    }

}