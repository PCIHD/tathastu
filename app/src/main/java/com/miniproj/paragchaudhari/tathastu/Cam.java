package com.miniproj.paragchaudhari.tathastu;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

public class Cam extends AppCompatActivity {

    private RunTimePermission runTimePermission = new RunTimePermission();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        runTimePermission.requestPermission(new String[]{Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, new RunTimePermission.RunTimePermissionListener() {

            @Override
            public void permissionGranted() {
                // First we need to check availability of play services
                startActivity(new Intent(Cam.this, WhatsappCameraActivity.class));
                finish();

            }

            @Override
            public void permissionDenied() {

                finish();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (runTimePermission != null) {
            runTimePermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }


}