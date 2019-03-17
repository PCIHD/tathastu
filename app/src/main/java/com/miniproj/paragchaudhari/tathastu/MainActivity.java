package com.miniproj.paragchaudhari.tathastu;

import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity {

    private ArFragment fragment;
    private Uri selectedObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment);
        InitializeGallery();
        fragment.setOnTapArPlaneListener(
                ((hitResult, plane, motionEvent) -> {
                    if (plane.getType() != Plane.Type.HORIZONTAL_UPWARD_FACING) {
                        return;

                    }
                    Anchor anchor = hitResult.createAnchor();
                    placeObject(fragment, anchor, selectedObject);

                })
        );
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


}