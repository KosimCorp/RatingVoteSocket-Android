package com.example.myapplication;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText edt_ip,edt_port;
    Button btn_connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_ip = findViewById(R.id.edt_ip);
        edt_port = findViewById(R.id.edt_port);
        btn_connection = findViewById(R.id.btn_connect);



        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);

        btn_connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Global.client = new Client("192.168.10.112", 9999);

                                LoginActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(LoginActivity.this, ScanActivity.class));
                                        finish();
                                    }
                                });
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                LoginActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        new AlertDialog.Builder(LoginActivity.this)
                                                .setMessage("Error")
                                                .show();
                                    }
                                });
                            }
                        }
                    }).start();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    new AlertDialog.Builder(LoginActivity.this)
                            .setMessage("Error")
                            .show();
                }
            }
        });

    }
}
