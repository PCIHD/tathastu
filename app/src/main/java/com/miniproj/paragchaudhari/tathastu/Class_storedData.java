package com.miniproj.paragchaudhari.tathastu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

final public  class Class_storedData {





    private String description_id,suggestion_id;
    private int room_id,object_id;
    private Bitmap image_id;
    private String Acceptiblity_id;
    private Float degree_label;
    private String room ,object,File_name;

    private Context context;
    private TextView  room_textview;



   public Class_storedData(int obj, int room,Float azimuth, Bitmap Image, String File,Context activity){
        room_id = room;
        degree_label = azimuth;
        image_id = Image;

        object_id = obj;
        File_name  = File;
        context = activity;
       //Toast.makeText(context,"message",Toast.LENGTH_LONG).show();
    }

    private static String acceptiblity(int deg){
        switch (deg){
            case 1: return "Perfect";
            case 2: return "Acceptable";
            case 3:return "Poor";

        }return "Acceptability Error";
    }
    public  void getDescription_id(){
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
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1400,2500,1).create();
        PdfDocument.Page page = report.startPage(pageInfo);


        LayoutInflater inflator = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View content = inflator.inflate(R.layout.report_layout,null);
        content.measure(1400,2500);
        content.layout(0,0,1400,2500);
        room_textview = (TextView) content.findViewById(R.id.room_name);
        room_textview.setText(room);



        content.draw(page.getCanvas());
        report.finishPage(page);
        Toast.makeText(context,"Document created",Toast.LENGTH_LONG).show();



        String directory_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) +"/Tathastu/";
        File file = new File(directory_path);
        if(!file.exists()){
            file.mkdirs();
        }
        String target = directory_path + "Report.pdf";
        File filepath = new File(target);
        Toast.makeText(context,target,Toast.LENGTH_LONG).show();
        try{
            report.writeTo(new FileOutputStream(filepath));
        }catch (Exception e1){
            Toast.makeText(context,e1.getMessage(),Toast.LENGTH_LONG).show();
            report.close();
        }




    }






}

