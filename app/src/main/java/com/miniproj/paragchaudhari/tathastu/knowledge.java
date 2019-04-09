package com.miniproj.paragchaudhari.tathastu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class knowledge extends AppCompatActivity {

    private Button button1,button2,button3,button4,button5,button6,button7,button8,button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);

        button9= (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openknow9();
            }
        });

        button8= (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openknow8();
            }
        });

        button7= (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openknow7();
            }
        });

        button6= (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openknow6();
            }
        });

        button5= (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openknow5();
            }
        });

        button4= (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openknow4();
            }
        });

        button3= (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openknow3();
            }
        });

        button2= (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openknow2();
            }
        });

        button1=(Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openknow1();
            }
        });
    }
    public void openknow1(){
        Intent intent =new Intent(this,know1.class);
        startActivity(intent);
    }

    public void openknow2(){
        Intent intent =new Intent(this,know2.class);
        startActivity(intent);
    }

    public void openknow3(){
        Intent intent =new Intent(this,know3.class);
        startActivity(intent);
    }

    public void openknow4(){
        Intent intent =new Intent(this,know4.class);
        startActivity(intent);
    }

    public void openknow5(){
        Intent intent =new Intent(this,know5.class);
        startActivity(intent);
    }

    public void openknow6(){
        Intent intent =new Intent(this,know6.class);
        startActivity(intent);
    }

    public void openknow7(){
        Intent intent =new Intent(this,know7.class);
        startActivity(intent);
    }

    public void openknow8(){
        Intent intent =new Intent(this,know8.class);
        startActivity(intent);
    }

    public void openknow9(){
        Intent intent =new Intent(this,know9.class);
        startActivity(intent);
    }
}

