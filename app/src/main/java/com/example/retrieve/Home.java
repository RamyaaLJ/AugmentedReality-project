package com.example.retrieve;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    DrawerLayout drawerLayout;
    TextView d_email,d_usertype;
    ImageView card1,card2,card3,card4,card5,decor1,decor2,decor3,decor4,decor5,decor6,look1,look2,look3,look4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        drawerLayout=findViewById(R.id.drawer_layout);


        card1=findViewById(R.id.card1);
        card2=findViewById(R.id.card2);
        card3=findViewById(R.id.card3);
        card4=findViewById(R.id.card4);
        card5=findViewById(R.id.card5);

        decor1=findViewById(R.id.decor1);
        decor2=findViewById(R.id.decor2);
        decor3=findViewById(R.id.decor3);
        decor4=findViewById(R.id.decor4);
        decor5=findViewById(R.id.decor5);
        decor6=findViewById(R.id.decor6);

        look1=findViewById(R.id.look1);
        look2=findViewById(R.id.look2);
        look3=findViewById(R.id.look3);
        look4=findViewById(R.id.look4);



        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Mirror.class);
                startActivity(intent);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Gridview1.class);
                startActivity(intent);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Lamp.class);
                startActivity(intent);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Sofa.class);
                startActivity(intent);
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Mirror.class);
                startActivity(intent);
            }
        });
        decor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Lamp.class);
                startActivity(intent);
            }
        });
        decor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Lamp.class);
                startActivity(intent);
            }
        });
        decor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Lamp.class);
                startActivity(intent);
            }
        });
        decor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Lamp.class);
                startActivity(intent);
            }
        });
        decor5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Lamp.class);
                startActivity(intent);
            }
        });
        decor6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Lamp.class);
                startActivity(intent);
            }
        });
        look1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Sofa.class);
                startActivity(intent);
            }
        });
        look2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Gridview.class);
                startActivity(intent);
            }
        });
        look3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Gridview1.class);
                startActivity(intent);
            }
        });
        look4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Mirror.class);
                startActivity(intent);
            }
        });
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
        recreate();
    }
    public void ClickLamp(View view){
        redirectActivity(this,Lamp.class);
    }
    public void ClickMirror(View view) {redirectActivity(this,Mirror.class);}
    public void Clicksofa(View view){
        redirectActivity(this,Sofa.class);
    }
    public void ClickGridview(View view){
        redirectActivity(this,Gridview.class);
    }
    public void ClickGridview1(View view){
        redirectActivity(this,Gridview1.class);
    }
    public void ClickCartActivity(View view){
        redirectActivity(this,CartActivity.class);
    }
    public void ClickAboutus(View view){ redirectActivity(this,AboutUs.class);
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