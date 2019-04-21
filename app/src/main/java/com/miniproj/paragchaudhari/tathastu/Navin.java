package com.miniproj.paragchaudhari.tathastu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

public class Navin extends AppCompatActivity {

    private String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Tathastu/";
    private LinearLayout navigationView;
    private Context context = this;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navin);
        navigationView = (LinearLayout) findViewById(R.id.Report_list);


        populate_list();



    }

    private void populate_list() {

        try {

            File Tathsatu_directory = new File(path);
            File[] files = Tathsatu_directory.listFiles();
            for (int i = 0; i < files.length; i++) {
                Button button_report = new Button(this);
                String name = files[i].getName();
                button_report.setText(name);
                navigationView.addView(button_report);
                button_report.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openRenderer(name);
                    }
                });




            }

        } catch (Exception e1) {
            Toast.makeText(this, e1.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    public void openRenderer(String filename) {
        try {
            Toast.makeText(this, path + filename, Toast.LENGTH_LONG).show();
            String file = path + filename;
            File pdf = new File(file);

            Intent intent = new Intent(Intent.ACTION_VIEW);

            intent.setDataAndType(Uri.fromFile(pdf),"application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }catch (Exception e1){
            Toast.makeText(this,e1.getMessage(),Toast.LENGTH_LONG).show();
        }

}}




