package com.miniproj.paragchaudhari.tathastu;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

public class Navin extends AppCompatActivity {

    private String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Tathastu/";
    private LinearLayout navigationView;
    private Button button_report;
    private Context context = this;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_activity);
        navigationView = (LinearLayout) findViewById(R.id.Report_list);


        populate_list();


button_report=(Button)findViewById(R.id.rep);
    }

    private void populate_list() {

        try {

            File Tathsatu_directory = new File(path);
            File[] files = Tathsatu_directory.listFiles();
            for (int i = 0; i < files.length; i++) {
                button_report = new Button(this);
                String name = files[i].getAbsolutePath();
                button_report.setText(files[i].getName());
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


            File pdf = new File(filename);
            Toast.makeText(this, filename, Toast.LENGTH_LONG).show();
            Uri path_uri = FileProvider.getUriForFile(this, "com.miniproj.paragchaudhari.tathastu" , pdf);

            Intent intent = new Intent(Intent.ACTION_VIEW);

            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);


            intent.setDataAndType(path_uri,"application/pdf");
            PackageManager packageManager = getPackageManager();
            if(intent.resolveActivity(packageManager)!=null) {


                startActivity(intent);
            }
        }catch (Exception e1){
           // Toast.makeText(this,e1.getMessage(),Toast.LENGTH_LONG).show();
        }

}}




