package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView zXingScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        zXingScannerView = findViewById(R.id.scanner);
        zXingScannerView.setAutoFocus(true);
        zXingScannerView.setResultHandler(this);
    }

    @Override
    protected void onStart() {
        zXingScannerView.startCamera();
        super.onStart();
    }

    @Override
    protected void onPause() {
        zXingScannerView.stopCamera();
        super.onPause();
    }

    @Override
    public void handleResult(Result result) {
       final String input = result.getText();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   final String hasil = Global.client.sendMessage("login-" + input);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (hasil.split("-")[1].equals("1"))
                            {
                                Intent intent = new Intent(ScanActivity.this, MainActivity.class);
                                intent.putExtra("kode", input);
                                startActivity(intent);

                                finish();
                            }
                            else if (hasil.split("-")[1].equals("2")) {
                                new AlertDialog.Builder(ScanActivity.this)
                                        .setMessage("Sudah dipakai, tidak bisa pakai qr yang itu lagi ")
                                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                                            @Override
                                            public void onDismiss(DialogInterface dialogInterface) {
                                                recreate();
                                            }
                                        })
                                        .show();
                            }
                            else {
                                new AlertDialog.Builder(ScanActivity.this)
                                        .setMessage("Kode QR Salah")
                                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                                            @Override
                                            public void onDismiss(DialogInterface dialogInterface) {
                                                recreate();
                                            }
                                        })
                                        .show();
                            }

                        }
                    });
                } catch (final Exception ex) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new AlertDialog.Builder(ScanActivity.this)
                                    .setMessage(ex.getMessage())
                                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                                        @Override
                                        public void onDismiss(DialogInterface dialogInterface) {
                                            recreate();
                                        }
                                    })
                                    .show();

                        }
                    });
                }
            }
        }).start();
    }
}
