package com.miniproj.paragchaudhari.tathastu;
// context.getString(R.string.stringname);

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
    private int description_id;


    private Bitmap image_id;
    private String Acceptiblity_id;
    private float degree_label;
    private String room ,object,File_name;

    private Context context;
    private TextView  room_textview,acceptiblity_textview,acceptiblity_textview1,acceptibility_textview2,suggestion_textview;
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

    private static String acceptiblity(String deg){
        switch (deg){
            case "1": return "Perfect";
            case "2": return "Acceptable";
            case "3":return "Poor";

        }return "Acceptability Error";
    }
        private void getDescription_id(){

       Acceptiblity_id = (String) check_validation(room_id,object_id,degree_label,description_id);
       Toast.makeText(context,"Test",Toast.LENGTH_LONG).show();
       switch (room_id){
           case 1:
               description_id = 1;
               switch (object_id){
               case 1:
                        room = "Kitchen";
                        object = "Door";

               return;
               case 2:
                   room = "Kitchen";
                   object = "Window";
                   break;
               default:Toast.makeText(context,"error",Toast.LENGTH_LONG).show();

           }

                return;
           case 2:description_id = 2;
           switch (object_id){
               case 1:
                   room = "Hall";
                   object = "Door";
                   return;
               case 2:
                   room = "Hall";
                   object = "Window";;
                  return;
               default:Toast.makeText(context,"error",Toast.LENGTH_LONG).show();
           }return;
           case 3:description_id = 3;
           switch (object_id){
               case 1:
                   room = "Bedroom";
                   object = "Door";
               return;
               case 2: room = "Bedroom";
                   object = "Window";


                   return;
               default:Toast.makeText(context,"error",Toast.LENGTH_LONG).show();
           }return;
           case 4:description_id = 4;
               switch (object_id){
                   case 1:
                       room = "Studyroom";
                       object = "Door";
                       return;
                   case 2: room = "Studyroom";
                       object = "Window";


                       return;
                   default:Toast.makeText(context,"error",Toast.LENGTH_LONG).show();
               }return;
       }
   }


    public void generate_pdf() {
       getDescription_id();
        String directory_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) +"/Tathastu/";
        File file = new File(directory_path);
        String target = directory_path + File_name;
        File filepath = new File(target);





        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1400,4000,1).create();
        PdfDocument.Page page = report.startPage(pageInfo);


        LayoutInflater inflator = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View content = inflator.inflate(R.layout.report_layout,null);
        int measureWidth = View.MeasureSpec.makeMeasureSpec(page.getCanvas().getWidth(),View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(page.getCanvas().getHeight(), View.MeasureSpec.EXACTLY);

        content.measure(measureWidth, measuredHeight);
        content.layout(0, 0, page.getCanvas().getWidth(), page.getCanvas().getHeight());

        String numberAsString = Float.toString(degree_label);
        String deg = getDegreeLabel();
        String descp = getDescription();

        room_textview = (TextView) content.findViewById(R.id.room_name);
        room_textview.setText(room);
        object_image = (ImageView) content.findViewById(R.id.Room_image);
        object_image.setImageBitmap(image_id);
        acceptiblity_textview = (TextView) content.findViewById(R.id.acceptability);
        acceptiblity_textview.setText(Acceptiblity_id);
        suggestion_textview = (TextView) content.findViewById(R.id.description);
        suggestion_textview.setText(descp);
        acceptiblity_textview1 = (TextView) content.findViewById(R.id.degree_val);
        acceptiblity_textview1.setText(numberAsString);
        acceptibility_textview2 = (TextView) content.findViewById(R.id.direction);
        acceptibility_textview2.setText(deg);






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

    private String getDegreeLabel() {
        if(degree_label>=0 && degree_label<=45) return "N";
        if(degree_label>=45 && degree_label<=90) return "NE";
        if(degree_label>=90 && degree_label<=135) return "E";
        if(degree_label>=135 && degree_label<=180) return "SE";
        if(degree_label>=180 && degree_label<=225) return "S";
        if(degree_label>=225 && degree_label<=270) return "SW";
        if(degree_label>=270 && degree_label<=315) return "W";
        if(degree_label>=315 && degree_label<=360) return "NW";
        return null;
    }

    public String getDescription() {
       switch (description_id){
           case 1:return context.getString(R.string.kitchen_description);
           case 2:return context.getString(R.string.Hall_description);
           case 3:return context.getString(R.string.bedroom_description);
           case 4:return context.getString(R.string.Study_room_description);
       }

        return null;
    }

    private String check_validation(int room, int object, float degree, int description_id){

        switch(room) {
          case 1: // kitchen
                switch (object) {
                    case 1://door
                        if (degree >= 0 && degree <= 90)
                        {return context.getString(R.string.kitchen_door_perfect);}
                        else if (degree >= 250 && degree <= 315) {
                            return context.getString(R.string.kitchen_door_good1);
                        }
                        else if (degree >= 45 && degree <= 135) {
                            return context.getString(R.string.kitchen_door_good2);
                        }
                        else if (degree >= 135 && degree <= 225) {
                            return context.getString(R.string.kitchen_door_bad);
                        }
                        else return context.getString(R.string.default_direction);



                    case 2://window
                        if (degree >= 45 && degree <= 90) return context.getString(R.string.kitchen_window_perfect);
                        else if (degree >= 90 && degree <= 135) return context.getString(R.string.kitchen_window_good1);
                        else if (degree >= 0 && degree <= 45) return context.getString(R.string.kitchen_window_good2);
                        else if (degree >= 235 && degree <= 315) return context.getString(R.string.kitchen_window_bad);
                        else return context.getString(R.string.default_direction);

                }
                return "0";

            case 2: // hall
                switch (object) {
                    case 1://door
                        if (degree >= 0 && degree <= 45) return context.getString(R.string.hall_door_perfect1);
                        else if (degree >= 45 && degree <= 90) return context.getString(R.string.hall_door_perfect2);
                        else if (degree >= 225 && degree <= 315) return context.getString(R.string.hall_door_bad1);
                        else if (degree >= 135 && degree <= 225) return context.getString(R.string.hall_door_bad2);
                        else return context.getString(R.string.default_direction);


                    case 2://window
                        if (degree >= 0 && degree <= 45) return context.getString(R.string.hall_window_perfect1);
                        else if (degree >= 45 && degree <= 90) return context.getString(R.string.hall_window_perfect2);
                        else if (degree >= 135 && degree <= 225) return context.getString(R.string.hall_window_bad1);
                        else if (degree >= 225 && degree <= 315) return context.getString(R.string.hall_window_bad2);
                        else return context.getString(R.string.default_direction);

                }
                return "0";


            case 3: // bedroom
                switch (object) {
                    case 1://door
                        if (degree >= 45 && degree <= 135) return context.getString(R.string.bedroom_door_perfect);
                        else if (degree >= 225 && degree <= 315) return context.getString(R.string.bedroom_door_good1);
                        else if (degree >= 0 && degree <= 45 || degree>=315 && degree<=360) return context.getString(R.string.bedroom_door_good2);
                        else if (degree >= 135 && degree <= 225) return context.getString(R.string.bedroom_door_bad);
                        else return context.getString(R.string.default_direction);


                    case 2://window
                        if (degree >= 225 && degree <= 315) return context.getString(R.string.bedroom_window_perfect);
                        else if (degree >= 0 && degree <= 45) return context.getString(R.string.bedroom_window_good1);
                        else if (degree >= 45 && degree <= 135) return context.getString(R.string.bedroom_window_good2);
                        else if (degree >= 135 && degree <= 225) return context.getString(R.string.bedroom_window_bad);
                        else return context.getString(R.string.default_direction);

                }
                return "0";

            case 4: // classroom
                switch (object) {
                    case 1://door
                        if (degree >= 0 && degree <= 45) return context.getString(R.string.studyroom_door_perfect1);
                        else if (degree >= 45 && degree <= 90) return context.getString(R.string.studyroom_door_perfect2);
                        else if (degree >= 225 && degree <= 270) return context.getString(R.string.studyroom_door_good1);
                        else if (degree >= 135 && degree <= 225) return context.getString(R.string.studyroom_door_bad);
                        else return context.getString(R.string.default_direction);



                    case 2://window
                        if (degree >= 45 && degree <= 90) return context.getString(R.string.studyroom_window_perfect1);
                        else if (degree >= 0 && degree <= 45) return context.getString(R.string.studyroom_window_perfect2);
                        else if (degree >= 225 && degree <= 315) return context.getString(R.string.studyroom_window_good1);
                        else if (degree >= 135 && degree <= 225) return context.getString(R.string.studyroom_window_bad);
                        else return context.getString(R.string.default_direction);

                }
                return "0";
            default:
                return "0";

        }

    }




}

