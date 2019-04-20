package com.miniproj.paragchaudhari.tathastu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

final public  class Class_storedData {





    private String suggestion_id;
    private int room_id,object_id;
    private Bitmap image_id;
    private String Acceptiblity_id;
    private float degree_label;
    private String room ,object,File_name;

    private Context context;
    private TextView  room_textview,acceptiblity_textview,suggestion_textview;
    private ImageView object_image;
    PdfDocument report = new PdfDocument();



/*

FUnction getSuggestion created , add logic for the suggestion there , add necessary paramaters

 */
   public Class_storedData(int obj, int room,float azimuth, Bitmap Image, String File,Context activity){
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
   private void getDescription_id(){

       Acceptiblity_id = acceptiblity(check_validation(room_id,object_id,degree_label));
       Toast.makeText(context,"Test",Toast.LENGTH_LONG).show();
       switch (room_id){
           case 1:switch (object_id){
               case 1:  suggestion_id = getSuggestion();
                        room = "Kitchen";
                        object = "Door";
               return;
               case 2:suggestion_id = getSuggestion();
                   room = "Kitchen";
                   object = "Window";   break;
               default:Toast.makeText(context,"error",Toast.LENGTH_LONG).show();
           }return;
           case 2:switch (object_id){
               case 1:suggestion_id = getSuggestion();
                   room = "Hall";
                   object = "Door";
                   return;
               case 2: suggestion_id = getSuggestion();
                   room = "Hall";
                   object = "Window";;
                  return;
               default:Toast.makeText(context,"error",Toast.LENGTH_LONG).show();
           }return;
           case 3:switch (object_id){
               case 1: suggestion_id = getSuggestion();
                   room = "Bedroom";
                   object = "Door";
               return;
               case 2: suggestion_id = getSuggestion();

                   return;
               default:Toast.makeText(context,"error",Toast.LENGTH_LONG).show();
           }return;
       }
   }


    public void generate_pdf() {
       getDescription_id();
        String directory_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) +"/Tathastu/";
        File file = new File(directory_path);
        String target = directory_path + "Report.pdf";
        File filepath = new File(target);





        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1400,2500,1).create();
        PdfDocument.Page page = report.startPage(pageInfo);


        LayoutInflater inflator = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View content = inflator.inflate(R.layout.report_layout,null);
        int measureWidth = View.MeasureSpec.makeMeasureSpec(page.getCanvas().getWidth(),View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(page.getCanvas().getHeight(), View.MeasureSpec.EXACTLY);

        content.measure(measureWidth, measuredHeight);
        content.layout(0, 0, page.getCanvas().getWidth(), page.getCanvas().getHeight());

        room_textview = (TextView) content.findViewById(R.id.room_name);
        room_textview.setText(room);
        object_image = (ImageView) content.findViewById(R.id.Room_image);
        object_image.setImageBitmap(image_id);
        acceptiblity_textview = (TextView) content.findViewById(R.id.acceptability);
        acceptiblity_textview.setText(Acceptiblity_id);
        suggestion_textview = (TextView) content.findViewById(R.id.suggestions);
        suggestion_textview.setText(suggestion_id);



        content.draw(page.getCanvas());
        report.finishPage(page);
        Toast.makeText(context,"Document created",Toast.LENGTH_LONG).show();





        if(!file.exists()){
            file.mkdirs();
        }

        Toast.makeText(context,target,Toast.LENGTH_LONG).show();
        try{
            report.writeTo(new FileOutputStream(filepath));
        }catch (Exception e1){
            Toast.makeText(context,e1.getMessage(),Toast.LENGTH_LONG).show();
            report.close();
        }




    }
    private String getSuggestion(){
       String value = "";
       return value;
    }

    private int check_validation(int room, int object , float degree){

        switch(room) {
            case 1: // kitchen
                switch (object) {
                    case 1://door
                        if (degree >= 135 && degree <= 225)
                        {return 1;}
                        else if (degree >= 45 && degree <= 135) {return 2;}
                        else return 3;



                    case 2://window
                        if (degree >= 0 && degree <= 45) return 1;
                        else if (degree >= 235 && degree <= 305) return 2;
                        else return 3;

                }
                return 0;

            case 2: // hall
                switch (object) {
                    case 1://door
                        if (degree >= 135 && degree <= 225) return 1;
                        else if (degree >= 45 && degree <= 135 || degree >= 225 && degree <= 305)
                            return 2;
                        else return 3;


                    case 2://window
                        if (degree >= 135 && degree <= 225) return 1;
                        else if (degree >= 45 && degree <= 135 || degree >= 225 && degree <= 305)
                            return 2;
                        else return 3;

                }
                return 0;


            case 3: // bedroom
                switch (object) {
                    case 1://door
                        if (degree >= 45 && degree <= 135) return 1;
                        else if (degree >= 135 && degree <= 225) return 2;
                        else return 3;


                    case 2://window
                        if (degree >= 235 && degree <= 305) return 1;
                        else if (degree >= 135 && degree <= 225) return 2;
                        else return 3;

                }
                return 0;


            default:
                return 0;

        }

    }




}

