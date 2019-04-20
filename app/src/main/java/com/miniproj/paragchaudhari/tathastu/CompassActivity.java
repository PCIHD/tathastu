package com.miniproj.paragchaudhari.tathastu;

import android.Manifest;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.ar.core.Frame;



import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CompassActivity extends AppCompatActivity {



    private static final String REQUIRED_PERMISSIONS[] = { Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.CAMERA};
    private Custom_arFragment fragment;
    private ImageButton door_button,window_button;
    SnackbarHelper snackbarHelper = new SnackbarHelper();




    private static final String TAG = "CompassActivity";

    private Compass compass;
    private ImageView arrowView;
    private TextView sotwLabel;

    private float currentAzimuth;
    private SOTWFormatter sotwFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listcompass);
        checkpermission();
        fragment = (Custom_arFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        fragment.getPlaneDiscoveryController().hide();
        door_button = (ImageButton) findViewById(R.id.Compass_doorbutton);
        window_button=(ImageButton) findViewById(R.id.Compass_windowButton);
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
    //    Log.d(TAG, "will set rotation from " + currentAzimuth + " to "+ azimuth);

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
    private String generatePdfName(){
        return  "Report" + new SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.getDefault()).format(new Date());


    }
    private void takeDoorPhoto(){
        try{
            Frame currentFrame = fragment.getArSceneView().getArFrame();
            Image currentImage = currentFrame.acquireCameraImage();
            String name = generateFileName("Door");
            snackbarHelper.showMessageWithDismiss(this,name);
            Save(currentImage , name);
            currentImage.close();
        }catch (Exception exception){
            snackbarHelper.showMessageWithDismiss(this,"Error Acquiring image");
        }
    }
    private void takeWindowPhoto(){
        try{
            Frame currentFrame = fragment.getArSceneView().getArFrame();
            Image currentImage = currentFrame.acquireCameraImage();
            String name = generateFileName("Window");
            snackbarHelper.showMessageWithDismiss(this,name);
            byte[] data = null;
          /*  data = NV21toJPEG(YUV_420_888toNV21(currentImage),
                    currentImage.getWidth(), currentImage.getHeight());
            Bitmap bitmap = BitmapFactory.decodeByteArray(data ,0,data.length);
            Class_storedData class_storedData = new Class_storedData(2,1,currentAzimuth,bitmap,generatePdfName());
            snackbarHelper.showMessageWithDismiss(this,"Successfully savved id");
            class_storedData.generate_pdf();
            */


            Save( currentImage, name);
            currentImage.close();
        }catch (Exception exception){
            snackbarHelper.showMessageWithDismiss(this,exception.getMessage());
        }
    }
    private static byte[] NV21toJPEG(byte[] nv21, int width, int height) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        YuvImage yuv = new YuvImage(nv21, ImageFormat.NV21, width, height, null);
        yuv.compressToJpeg(new Rect(0, 0, width, height), 100, out);
        return out.toByteArray();
    }
    private static byte[] YUV_420_888toNV21(Image image) {
        byte[] nv21;
        ByteBuffer yBuffer = image.getPlanes()[0].getBuffer();
        ByteBuffer uBuffer = image.getPlanes()[1].getBuffer();
        ByteBuffer vBuffer = image.getPlanes()[2].getBuffer();

        int ySize = yBuffer.remaining();
        int uSize = uBuffer.remaining();
        int vSize = vBuffer.remaining();

        nv21 = new byte[ySize + uSize + vSize];

        //U and V are swapped
        yBuffer.get(nv21, 0, ySize);
        vBuffer.get(nv21, ySize, vSize);
        uBuffer.get(nv21, ySize + vSize, uSize);

        return nv21;
    }

    private void Save(Image image , String name){
        byte[] data = null;
        data = NV21toJPEG(YUV_420_888toNV21(image),
                image.getWidth(), image.getHeight());

        Bitmap bitmap = BitmapFactory.decodeByteArray(data ,0,data.length);
        final File out =  new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+File.separator + "Tathastu/",name + ".png");
        if(!out.getParentFile().exists()){
            out.getParentFile().mkdirs();
            snackbarHelper.showMessageWithDismiss(this,"created directory");
        }


        try{
            FileOutputStream fos = new FileOutputStream(out);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.flush();
            fos.close();
            snackbarHelper.showMessageWithDismiss(this,out.getName());
        }catch (Exception e1){
            snackbarHelper.showMessageWithDismiss(this , e1.getMessage());


        }
    }

    private void checkpermission(){
        if(ContextCompat.checkSelfPermission(this , Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, 1);
        }
            if(ContextCompat.checkSelfPermission(this , Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(this,REQUIRED_PERMISSIONS,2);
        }
    }



}
