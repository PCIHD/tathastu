package com.miniproj.paragchaudhari.tathastu;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.ar.core.Anchor;

import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;

import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.rendering.Texture;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import static com.google.ar.sceneform.rendering.PlaneRenderer.MATERIAL_TEXTURE;
import static com.google.ar.sceneform.rendering.PlaneRenderer.MATERIAL_UV_SCALE;




public class ArActivity extends AppCompatActivity {
    private ImageView button2;
    private ArFragment fragment;
    private Uri selectedObject;
    private Button clear_object_button;
    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_activity);
        InitializeDrawer();

        button2 = (ImageView) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainactivity(view);
            }
        });



        fragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment);
        fragment.getPlaneDiscoveryController().hide();
        //setPlaneTexture("lines.png");


        InitializeDrawer();
        fragment.setOnTapArPlaneListener(
                ((hitResult, plane, motionEvent) -> {
                    if (plane.getType() != Plane.Type.HORIZONTAL_UPWARD_FACING) {
                        return;

                    }
                    if (selectedObject == null) {

                    }
                    Anchor anchor = hitResult.createAnchor();
                    placeObject(fragment, anchor, selectedObject);

                })
        );

        clear_object_button = (Button) findViewById(R.id.ar_clear_button);

        fragment.getArSceneView().getScene().addOnPeekTouchListener(new Scene.OnPeekTouchListener() {
            @Override
            public void onPeekTouch(HitTestResult hitTestResult, MotionEvent motionEvent) {
                if (hitTestResult.getNode()!= null){
                    clear_object_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(hitTestResult.getNode()!=null)
                            {
                                hitTestResult.getNode().setParent(null);
                            }
                        }
                    });

                }
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                switch(menuItem.getItemId()){
                    case R.id.Bed_01_sfb:
                        selectedObject = Uri.parse("Bed_01.sfb");
                        return true;
                    case R.id.computer_table_sfb:
                        selectedObject = Uri.parse("computer_table.sfb");
                        return true;
                    case R.id.dining_table_sfb:
                        selectedObject = Uri.parse("dining_table.sfb");
                        return true;
                    case R.id.eb_nightstand_01_sfb:
                        selectedObject = Uri.parse("eb_nightstand_01.sfb");
                        return true;
                    case R.id.expensive_sofa_sfb:
                        selectedObject = Uri.parse("expensive_sofa.sfb");
                        return true;
                    case R.id.model_sfb:
                        selectedObject  = Uri.parse("model.sfb");
                        return true;






                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return  true;

            }
        });


    }

    private void CLEARSCENE(Anchor anchor){
        if(anchor !=null)
        anchor.detach();
    }
    private void InitializeGallery() {
        LinearLayout gallery = findViewById(R.id.gallery_layout);

        ImageView couch = new ImageView(this);
        couch.setImageResource(R.drawable.couch_thumb);
        couch.setContentDescription("Couch");
        couch.setOnClickListener(view -> {
            selectedObject = Uri.parse("model.sfb");
        });
        gallery.addView(couch);

        ImageView bed = new ImageView(this);
        bed.setImageResource(R.drawable.bed);
        bed.setContentDescription("Bed");
        bed.setOnClickListener(view -> {
            selectedObject = Uri.parse("Bed_01.sfb");
        });
        gallery.addView(bed);

        ImageView computerTable = new ImageView(this);
        computerTable.setImageResource(R.drawable.computer_table);
        computerTable.setContentDescription("Computer Table");
        computerTable.setOnClickListener(view -> {
            selectedObject = Uri.parse("computer_table.sfb");
        });
        gallery.addView(computerTable);

      ImageView diningTable = new ImageView(this);
      diningTable.setImageResource(R.drawable.dining_table);
      diningTable.setContentDescription("Dining table");
      diningTable.setOnClickListener(view -> {
          selectedObject = Uri.parse("dining_table.sfb");

      });
        gallery.addView(diningTable);

        ImageView expensive_sofa = new ImageView(this);
        expensive_sofa.setImageResource(R.drawable.expensive_sofa);
        expensive_sofa.setContentDescription("Expensive Sofa");
        expensive_sofa.setOnClickListener(view -> {
            selectedObject = Uri.parse("expensive_sofa.sfb");

        });
        gallery.addView(expensive_sofa);


        ImageView night_table = new ImageView(this);
        night_table.setImageResource(R.drawable.night_stand);
        night_table.setContentDescription("Night Stand");
        night_table.setOnClickListener(view -> {
            selectedObject = Uri.parse("eb_nightstand_01.sfb");

        });
        gallery.addView(night_table);


    }



    private void placeObject(ArFragment fragment, Anchor anchor, Uri model) {
        ModelRenderable.builder()
                .setSource(fragment.getContext(), model)
                .build()
                .thenAccept(renderable -> addNodeToScene(fragment, anchor, renderable))
                .exceptionally((throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage((throwable.getMessage()))
                            .setTitle("Error!");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return null;

                }));
    }

    private void addNodeToScene(ArFragment fragment, Anchor anchor, Renderable renderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode node = new TransformableNode(fragment.getTransformationSystem());
        node.setRenderable(renderable);
        node.setParent(anchorNode);
        fragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();

    }

    private void mainactivity(View view){
        Intent mainactivity = new Intent(ArActivity.this,MainActivity.class);
        startActivity(mainactivity);
    }


    private void setPlaneTexture(String texturePath) {

        Texture.Sampler sampler = Texture.Sampler.builder()
                .setMinFilter(Texture.Sampler.MinFilter.LINEAR_MIPMAP_LINEAR)
                .setMagFilter(Texture.Sampler.MagFilter.LINEAR)
                .setWrapModeR(Texture.Sampler.WrapMode.REPEAT)
                .setWrapModeS(Texture.Sampler.WrapMode.REPEAT)
                .setWrapModeT(Texture.Sampler.WrapMode.REPEAT)
                .build();

        Texture.builder().setSource(() -> getAssets().open(texturePath))
                .setSampler(sampler)
                .build().thenAccept((texture) -> {
            fragment.getArSceneView().getPlaneRenderer().getMaterial()
                    .thenAccept((material) -> {
                        material.setTexture(MATERIAL_TEXTURE, texture);
                        material.setFloat(MATERIAL_UV_SCALE,10f);
                    });
        }).exceptionally((throwable -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage((throwable.getMessage()))
                    .setTitle("Error!");
            AlertDialog dialog = builder.create();
            dialog.show();
            return null;
        }));

    }
    private void InitializeDrawer(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);







    }
}