package com.miniproj.paragchaudhari.tathastu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Class_storedData {

    private String description_id,suggestion_id;
    private int room_id,object_id;
    private Bitmap image_id;
    private String Acceptiblity_id;
    private Float degree_label;
    private String object,room,File_name;

    Class_storedData(int object, int room,Float azimuth, Bitmap Image, String File){
        room_id = room;
        degree_label = azimuth;
        image_id = Image;

        object_id = object;
        File_name  = File;
    }

    private String acceptiblity(int deg){
        switch (deg){
            case 1: return "Perfect";
            case 2: return "Acceptable";
            case 3:return "Poor";

        }return "Acceptability Error";
    }
    public void getDescription_id(){
       switch (room_id){
           case 1:  switch (object_id){
               case 1: object = "Door";
                   room = "Kitchen";
                        suggestion_id =  Resources.getSystem().getString(R.string.Kitchen_door);
                        Object_validity validity = new Object_validity() ;
                        Acceptiblity_id = acceptiblity(validity.check_validation(1,1,degree_label));


                room = "Kitchen";
               break;
               case 2:
                   object = "Window";
                   room = "Kitchen";
                   suggestion_id =  Resources.getSystem().getString(R.string.Kitchen_Window);

                   break;}
           case 2:  switch (object_id){
               case 1:object = "Door";
                   room = "Hall";
                   suggestion_id =  Resources.getSystem().getString(R.string.Hall_door);
                   break;
               case 2:object = "Window";
                   room = "Hall";
                   suggestion_id =  Resources.getSystem().getString(R.string.Hall_Window);
                   break;}
           case 3:  switch (object_id){
               case 1: object = "Door";
                   room = "Bedroom";
                   suggestion_id =  Resources.getSystem().getString(R.string.Bedroom_Window);
                   break;
               case 2:object = "Window";
                   room = "Bedroom";
                   suggestion_id =  Resources.getSystem().getString(R.string.Bedroom_door);
                   break;}

           }

       }


    public void generate_pdf() {
        PdfDocument report = new PdfDocument();
        String directory_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Tathastu";
        getDescription_id();


        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();

        PdfDocument.Page page = report.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawText(room, 150, 30, paint);
        


        report.finishPage(page);
        File file = new File(directory_path, File_name);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();


        }
        try {
            report.writeTo(new FileOutputStream(file));

        } catch (Exception e1) {
            Log.d("error in saving" , "Save error",e1);
        }
        report.close();
    }






}

