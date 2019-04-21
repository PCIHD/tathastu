package com.miniproj.paragchaudhari.tathastu;
//vastu corp - > boycott parag chaudhari
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView iv;


    private Button Arcore_button,Knowledge_button,Navin_button,Compass_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        iv= (ImageView)findViewById(R.id.logo);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
        iv.startAnimation(anim);


        Knowledge_button = (Button) findViewById(R.id.Activity_Main_Knowledge_Button);
        Knowledge_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openknowledge();
            }
        });

        Arcore_button = (Button) findViewById(R.id.Activity_main_ArVision_Button);
        Arcore_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arview(view);
            }
        });



        Compass_button = (Button) findViewById(R.id.Activity_main_compass_button);
        Compass_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
                opencomp();
            }
        });
        Navin_button = (Button) findViewById(R.id.Activity_MainNavin_Button);
        Navin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navin();
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



    public void opencomp(){
        Intent intent =new Intent(this,CompassActivity.class);
        startActivity(intent);
    }
    public void navin(){
        Intent intent = new Intent(this,Navin.class);
        startActivity(intent);

    }



}
