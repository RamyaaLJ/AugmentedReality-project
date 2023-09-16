//package com.example.arview1;
package com.example.retrieve;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ARViewAstronaut extends AppCompatActivity {
    private ArFragment arFragment;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    private ModelRenderable modelRenderable;
    DatabaseReference databaseReference;
    private String MODEL_URL;

    List<Review> review;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        databaseReference= FirebaseDatabase.getInstance().getReference("preview");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                review = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Review data = ds.getValue(Review.class);
                    review.add(data);
                }
                boolean check = false;
                String link = "";
                for(int i=0; i<review.size();i++){
                    if(review.get(i).getTitle().equals(title)){
                        check = true;
                        link = review.get(i).getLink();
                    }
                }
                if(check) {
                    setContentView(R.layout.activity_arview_astronaut);
                    //MODEL_URL = "https://modelviewer.dev/shared-assets/models/Astronaut.glb?raw=true";
                    MODEL_URL = link;
                    arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
                    setUpModel();
                    setUpPlane();

                    Button backButton = findViewById(R.id.backButton);
                    //backButton.setOnClickListener(v -> this.finish());
                }
                else {
                    setContentView(R.layout.android_random);
                    Toast.makeText(ARViewAstronaut.this, String.valueOf(check),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setUpPlane() {
        arFragment.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            Anchor anchor= hitResult.createAnchor();
            AnchorNode anchorNode=new AnchorNode(anchor);
            anchorNode.setParent(arFragment.getArSceneView().getScene());
            createModel(anchorNode);

        }));
    }

    private void createModel(AnchorNode anchorNode) {
        TransformableNode node=new TransformableNode(arFragment.getTransformationSystem());
        node.setParent(anchorNode);
        node.setRenderable(modelRenderable);
        node.select();
    }

    private void setUpModel() {
        ModelRenderable.builder()
                .setSource(this, RenderableSource.builder().setSource(this,
                        Uri.parse(MODEL_URL),RenderableSource.SourceType.GLB)
                        .setScale(0.75f).setRecenterMode(RenderableSource.RecenterMode.ROOT)
                        .build())
                .setRegistryId(MODEL_URL)
                .build()

                .thenAccept(renderable -> modelRenderable=renderable)

                .exceptionally(throwable -> {
                    Toast.makeText(ARViewAstronaut.this,"cant load",Toast.LENGTH_LONG).show();
                    return null;
                });
    }

}
