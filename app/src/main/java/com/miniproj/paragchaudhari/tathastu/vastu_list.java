package com.miniproj.paragchaudhari.tathastu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class vastu_list extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vastu_list);
        ArrayList userList = getListData();
        final ListView lv = (ListView) findViewById(R.id.user_list);
        lv.setAdapter(new CustomListAdaptor(this, userList));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                ListItem user = (ListItem) lv.getItemAtPosition(position);
                Toast.makeText(vastu_list.this, "Selected :" + " " + user.getName()+", "+ user.getLocation(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private ArrayList getListData() {
        ArrayList<ListItem> results = new ArrayList<>();
        ListItem user1 = new ListItem();
        user1.setName("KITCHEN VASTU");
        user1.setDesignation("To ckeck your kitchen vastu");
        user1.setLocation("GOT");
        results.add(user1);
        ListItem user2 = new ListItem();
        user2.setName("HALL VASTU");
        user2.setDesignation("To ckeck your Hall vastu");
        user2.setLocation("GOT");
        results.add(user2);
        ListItem user3 = new ListItem();
        user3.setName("BEDROOM VASTU");
        user3.setDesignation("To ckeck your Bedroom vastu");
        user3.setLocation("GOT");
        results.add(user3);
        return results;
    }
}
