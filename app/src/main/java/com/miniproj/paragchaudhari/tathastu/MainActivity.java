package com.miniproj.paragchaudhari.tathastu;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    private Button button,button4,button3;
    private Button button2;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openvastulist();
            }
        });

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



        button4 = (Button) findViewById(R.id.button10);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
                opencomp();
            }
        });

        button5 = (Button) findViewById(R.id.button101);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencam();
            }

        });

    }
    public void arview(View view)
    {
        Intent aractivity = new Intent(MainActivity.this,ArActivity.class);
        startActivity(aractivity);
    }

    public void opencam()
    {
        Intent intent = new Intent(this,Cam.class);
        startActivity(intent);
    }

    public void openknowledge(){
        Intent intent =new Intent(this,knowledge.class);
        startActivity(intent);
    }

    public void openvastulist(){
        Intent intent =new Intent(this,vastu_list.class);
        startActivity(intent);
    }


    public void opencomp(){
        Intent intent =new Intent(this,CompassActivity.class);
        startActivity(intent);
    }

}
