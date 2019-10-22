package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    GridView gridView;

    Team[] teams = {
            new Team("Kabupaten Bogor", R.drawable.kab_bogor),
//            new Team("Kabupaten Bandung", R.drawable.kab_bandung),
            new Team("Kabupaten Bekasi", R.drawable.kab_bekasi)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.grid_view);

        MainAdapter adapter = new MainAdapter(this, R.layout.row_item, teams);
        gridView.setAdapter(adapter);
    }
}
