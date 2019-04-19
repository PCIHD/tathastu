package com.miniproj.paragchaudhari.tathastu;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.ar.core.Frame;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CompassActivity extends AppCompatActivity {
    private Custom_arFragment fragment;
    private Button door_button,window_button;
    SnackbarHelper snackbarHelper = new SnackbarHelper();

    private static final String TAG = "CompassActivity";

    private Compass compass;
    private ImageView arrowView;
    private TextView sotwLabel;  // SOTW is for "side of the world"

    private float currentAzimuth;
    private SOTWFormatter sotwFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        fragment = (Custom_arFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        fragment.getPlaneDiscoveryController().hide();
        door_button = (Button) findViewById(R.id.Compass_doorbutton);
        window_button=(Button) findViewById(R.id.Compass_windowButton);
        door_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeDoorPhoto();
            }
        });
        window_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeWindowPhoto();
            }
        });
        sotwFormatter = new SOTWFormatter(this);

        arrowView = findViewById(R.id.main_image_hands);
        sotwLabel = findViewById(R.id.sotw_label);
        setupCompass();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "start compass");
        compass.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        compass.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        compass.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "stop compass");
        compass.stop();
    }

    private void setupCompass() {
        compass = new Compass(this);
        Compass.CompassListener cl = getCompassListener();
        compass.setListener(cl);
    }

    private void adjustArrow(float azimuth) {
        Log.d(TAG, "will set rotation from " + currentAzimuth + " to "
                + azimuth);

        Animation an = new RotateAnimation(-currentAzimuth, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        currentAzimuth = azimuth;

        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);

        arrowView.startAnimation(an);
    }

    private void adjustSotwLabel(float azimuth) {
        sotwLabel.setText(sotwFormatter.format(azimuth));
    }

    private Compass.CompassListener getCompassListener() {
        return azimuth -> {
            // UI updates only in UI thread
            // https://stackoverflow.com/q/11140285/444966
            runOnUiThread(() -> {
                adjustArrow(azimuth);
                adjustSotwLabel(azimuth);
            });
        };
    }
    private String generateFileName(String object){
        return  object + new SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.getDefault()).format(new Date());


    }
    private void takeDoorPhoto(){
        try{
            Frame currentFrame = fragment.getArSceneView().getArFrame();
            Image currentImage = currentFrame.acquireCameraImage();
            String name = generateFileName("Door");
            snackbarHelper.showMessage(this,name);
        }catch (Exception exception){
            snackbarHelper.showMessage(this,"Error Acquiring image");
        }
    }
    private void takeWindowPhoto(){
        try{
            Frame currentFrame = fragment.getArSceneView().getArFrame();
            Image currentImage = currentFrame.acquireCameraImage();
            String name = generateFileName("Window");
            snackbarHelper.showMessage(this,name);
        }catch (Exception exception){
            snackbarHelper.showMessage(this,"Error Acquiring image");
        }
    }


}
