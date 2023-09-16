
package com.example.retrieve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Lamp extends AppCompatActivity {
    DrawerLayout drawerLayout;
    List<LampData> lampData;
    RecyclerView recyclerView1;
    LampAdapter lampAdapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp);
        recyclerView1=findViewById(R.id.recyclerView1);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView1.setLayoutManager(gridLayoutManager);
        lampData=new ArrayList<>();
        EditText editText=findViewById(R.id.search_box4);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        databaseReference= FirebaseDatabase.getInstance().getReference("decor");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    LampData data1=ds.getValue(LampData.class);
                    lampData.add(data1);
                }
                lampAdapter=new LampAdapter(lampData);
                recyclerView1.setAdapter(lampAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void filter(String text){
        ArrayList<LampData> filteredList=new ArrayList<>();
        for(LampData item:lampData){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

            }
        }
        lampAdapter.filterList(filteredList);
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
    public void ClickMirror(View view) {redirectActivity(this,Mirror.class);}
    public void ClickHome(View view){
        redirectActivity(this,Home.class);


    }
    public void ClickGridview(View view){
        redirectActivity(this,Gridview.class);
    }
    public void Clicksofa(View view){
        redirectActivity(this,Sofa.class);
    }
    public void ClickLamp(View view){
        recreate();
    }

    public void ClickGridview1(View view){
        redirectActivity(this,Gridview1.class);
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