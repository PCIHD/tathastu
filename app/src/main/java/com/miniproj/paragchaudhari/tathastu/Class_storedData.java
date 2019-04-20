package com.miniproj.paragchaudhari.tathastu;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

public class Class_storedData {

    private String description_id,suggestion_id;
    private int degree_label,room_id,object_id;
    private Bitmap image_id;
    private String Acceptiblity_id;
    private String object,room,File_name;




    Class_storedData(int object ,int room ,int azimuth , Bitmap Image, String Acceptbility,String File){
        room_id = room;
        degree_label = azimuth;
        image_id = Image;
        Acceptiblity_id= Acceptbility;
        object_id = object;
        File_name  = File;
    }


    public void getDescription_id(){
       switch (room_id){
           case 1:  switch (object_id){
               case 1: object = "Door";
                   room = "Kitchen";
                        suggestion_id =  Resources.getSystem().getString(R.string.Kitchen_door);
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

        }
        report.close();
    }






}

