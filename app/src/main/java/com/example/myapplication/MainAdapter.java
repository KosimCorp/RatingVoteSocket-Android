package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class MainAdapter extends ArrayAdapter<Team> {
    private Context context;
    private int resource;
    private Team[] teams;

    public MainAdapter(@NonNull Context context, int resource, Team[] teams) {
        super(context, resource);

        this.context = context;
        this.resource = resource;
        this.teams = teams;
    }


    @Override
    public int getCount() {
        return teams.length;
    }

    @Override
    public Team getItem(int i) {
        return teams[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null, true);

        ImageView imageView = convertView.findViewById(R.id.image_view);
        TextView textView = convertView.findViewById(R.id.text_view);

        if (position == 0 || position == 1)
            imageView.setImageResource(teams[position].getrImage());

        textView.setText(teams[position].getName());

        return convertView;
    }
}
