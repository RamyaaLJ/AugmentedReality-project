package com.example.retrieve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetails extends AppCompatActivity {
TextView title,price,warranty,save,offer;
ImageView imageView;
DrawerLayout drawerLayout;
Button addtocart,preview;
String productID="";
FirebaseDatabase db=FirebaseDatabase.getInstance();
FirebaseAuth fAuth;
DatabaseReference root=db.getReference().child("cart");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        fAuth=FirebaseAuth.getInstance();
        title=findViewById(R.id.sec_title);
        price=findViewById(R.id.sec_price);
        warranty=findViewById(R.id.warranty);
        offer=findViewById(R.id.sec_offer);
        save=findViewById(R.id.sec_save);
        imageView=findViewById(R.id.sec_image);
        preview=findViewById(R.id.preview);
        addtocart=findViewById(R.id.addtocart);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        productID=getIntent().getStringExtra("id");
        title.setText(getIntent().getStringExtra("name"));
        warranty.setText(getIntent().getStringExtra("warranty"));
        save.setText(getIntent().getStringExtra("save"));
        offer.setText(getIntent().getStringExtra("offer"));
       price.setText(getIntent().getExtras().getString("price"));
        String furnimg=getIntent().getStringExtra("img");
        Glide.with(this)
                .load(furnimg)
                .into(imageView);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductDetails.this,ARViewAstronaut.class);
                intent.putExtra("title",title.getText().toString());
                startActivity(intent);
            }
        });
      // imageView.setImageResource(getIntent().getStringExtra("img"));
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addingToCartList();
            }
        });
    }

    private void addingToCartList() {
        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate =  Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());
        //final DatabaseReference cartListRef= FirebaseDatabase.getInstance().getReference().child("CartList");
        final HashMap<String ,Object> cartMap=new HashMap<>();
        cartMap.put("id",productID);
        cartMap.put("name",title.getText().toString());
        cartMap.put("warranty",warranty.getText().toString());
        cartMap.put("save",save.getText().toString());
        cartMap.put("offer",offer.getText().toString());
        cartMap.put("price",price.getText().toString());
        //cartMap.put("img",imageView.);
        cartMap.put("date",saveCurrentDate);
        cartMap.put("time",saveCurrentTime);

        root.push().setValue(cartMap);
        FirebaseDatabase.getInstance().getReference("cart").child(productID)
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ProductDetails.this, "Added successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

        /*cartListRef.child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Products").child(productID)
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ProductDetails.this, "Added To cart", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/

    }

    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }
    public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }
    public static void closeDrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }

    }
    public void ClickHome(View view){
        redirectActivity(this,Home.class);


    }
    public void ClickGridview(View view){
        redirectActivity(this,Gridview.class);
    }
    public void ClickCartActivity(View view){
        redirectActivity(this,CartActivity.class);
    }
    public void ClickLogout(View view){
        logout(this);
    }
    public static void logout(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("logout");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    public static void redirectActivity(Activity activity, Class aClass){
        Intent intent=new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);
    }
}