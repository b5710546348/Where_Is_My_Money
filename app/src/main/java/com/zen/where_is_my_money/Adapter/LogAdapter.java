package com.zen.where_is_my_money.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.zen.where_is_my_money.Models.Purse;
import com.zen.where_is_my_money.Models.Storage;
import com.zen.where_is_my_money.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Zen on 6/5/16.
 */
public class LogAdapter extends ArrayAdapter<Purse> {

    private AlertDialog.Builder deleteDialogBuilder;

    public LogAdapter(Context context, int resource, List<Purse> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.log_cell, null);
        }

        TextView log_info = (TextView)v.findViewById(R.id.log_info_text);
        Button edit_button = (Button)v.findViewById(R.id.edit_log_button);
        Button delete_button = (Button)v.findViewById(R.id.delete_button);
        Purse p = getItem(position);
        log_info.setText(p.getFullInformation());


        return v;
    }


}
