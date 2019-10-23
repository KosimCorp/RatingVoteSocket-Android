package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.util.Map;

public class ProsesVote extends AppCompatActivity {

    ImageButton btn_1,btn_2,btn_3,btn_4,btn_5;
    ImageView img_background,img_kota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proses_vote);

        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);

//        img_background = findViewById(R.id.img_backround);
        img_kota =findViewById(R.id.img_foto_kota);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Map<String, Object> _map = Global.client.sendMessage("team-" + getIntent().getIntExtra("team_id", 0));

                    final Team team = new Team();
                    team.setIdTeam((Integer) _map.get("id_team"));
                    team.setName((String) _map.get("nama_team"));
                    team.setrImage(BitmapFactory.decodeStream(new ByteArrayInputStream((byte[]) _map.get("gambar"))));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            img_background.setImageBitmap(team.getrImage());
                            img_kota.setImageBitmap(team.getrImage());
                        }
                    });
                } catch (final Exception ex) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new AlertDialog.Builder(ProsesVote.this)
                                    .setMessage(ex.getMessage())
                                    .show();
                        }
                    });
                }
            }
        }).start();

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vote(20);
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vote(40);
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vote(60);
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vote(80);
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vote(100);
            }
        });
    }

    private void vote(final int rating) {
        final int id_team = getIntent().getIntExtra("team_id", 0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = Global.client.sendMessage("coblos-" + getIntent().getStringExtra("kode") + "-" + id_team + "-" + rating);

                    if (result.split("-")[1].equals("1"))
                    {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new AlertDialog.Builder(ProsesVote.this)
                                        .setMessage("Terima Kasih sudah nyoblos")
                                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                                            @Override
                                            public void onDismiss(DialogInterface dialogInterface) {
                                                startActivity(new Intent(ProsesVote.this, ScanActivity.class));
                                                finish();
                                            }
                                        })
                                        .show();
                            }
                        });
                    }
                    else
                    {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new AlertDialog.Builder(ProsesVote.this)
                                        .setMessage("Gagal")
                                        .show();
                            }
                        });
                    }
                } catch (final Exception ex) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new AlertDialog.Builder(ProsesVote.this)
                                    .setMessage(ex.getMessage())
                                    .show();
                        }
                    });
                }
            }
        }).start();
    }
}
