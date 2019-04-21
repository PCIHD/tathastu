package com.miniproj.paragchaudhari.tathastu;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class Navin extends AppCompatActivity {

    private String path;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navin);
        linearLayout = (LinearLayout) findViewById(R.id.Report_list);

        populate_list();




        }
        private  void populate_list(){

            try {
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Tathastu/";
                File Tathsatu_directory = new File(path);
                File[] files = Tathsatu_directory.listFiles();
                for(int i = 0;i<files.length;i++){
                    TextView textView = new TextView(this);
                    textView.setText(files[i].getName());
                    linearLayout.addView(textView);



                }
            }catch(Exception e1){
                Toast.makeText(this,e1.getMessage(),Toast.LENGTH_LONG).show();
            }



        }

        }



