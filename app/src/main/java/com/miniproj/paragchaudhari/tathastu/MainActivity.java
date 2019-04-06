package com.miniproj.paragchaudhari.tathastu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openknowledge();
            }
        });

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arview(view);
            }
        });


    }
    public void arview(View view)
    {
        Intent aractivity = new Intent(MainActivity.this,ArActivity.class);
        startActivity(aractivity);
    }

    public void openknowledge(){
        Intent intent =new Intent(this,knowledge.class);
        startActivity(intent);
    }
}
