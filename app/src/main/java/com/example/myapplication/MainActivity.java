package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.grid_view);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Map<String, Object>> _list = Global.client.sendMessage("list");

                    final Team[] teams = new Team[_list.size()];
                    for (int i=0; i<_list.size(); i++) {
                        Map<String, Object> _map = _list.get(i);
                        Team team = new Team();
                        team.setIdTeam((Integer) _map.get("id_team"));
                        team.setName((String) _map.get("nama_team"));
                        team.setrImage(BitmapFactory.decodeStream(new ByteArrayInputStream((byte[]) _map.get("gambar"))));

                        teams[i] = team;
                    }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            MainAdapter adapter = new MainAdapter(MainActivity.this, R.layout.row_item, teams);
                            gridView.setAdapter(adapter);

                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int posisi, long l) {
                                    Intent intent = new Intent(MainActivity.this, ProsesVote.class);

                                    intent.putExtra("team_id", teams[posisi].getIdTeam());
                                    intent.putExtra("kode", getIntent().getStringExtra("kode"));

                                    startActivity(intent);
                                }
                            });
                        }
                    });
                } catch (final Exception ex) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setMessage(ex.getMessage())
                                    .show();
                        }
                    });
                }
            }
        }).start();
    }
}
