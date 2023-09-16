package com.example.retrieve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Gridview extends AppCompatActivity {
    DrawerLayout drawerLayout;
    List<FetchData> fetchData;
    RecyclerView recyclerView;
    HelperAdapter helperAdapter;
    DatabaseReference databaseReference;
    LinearLayoutManager mLayoutManager;
    SharedPreferences mSharedPref;
    List<FetchData> fetchDataNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        recyclerView = findViewById(R.id.recyclerView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        fetchData = new ArrayList<>();
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        mSharedPref=getSharedPreferences("SortSettings",MODE_PRIVATE);
        String mSorting= mSharedPref.getString("Sort","newest");

        if(mSorting.equals("newest")){
            databaseReference = FirebaseDatabase.getInstance().getReference("recyclerview");

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    fetchData = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        FetchData data = ds.getValue(FetchData.class);
                        fetchData.add(data);
                    }

                    quicksort2(fetchData, 0, fetchData.size()-1);


                    helperAdapter = new HelperAdapter(fetchData);
                    recyclerView.setAdapter(helperAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(mSorting.equals("oldest")){
            databaseReference = FirebaseDatabase.getInstance().getReference("recyclerview");

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    fetchData = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        FetchData data = ds.getValue(FetchData.class);
                        fetchData.add(data);
                    }

                    quicksort(fetchData, 0, fetchData.size()-1);


                    helperAdapter = new HelperAdapter(fetchData);
                    recyclerView.setAdapter(helperAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

    }

    public void quicksort(List<FetchData> fData, int low, int high){
        if(low<high){
            int pi = partition(fData, low, high);

            quicksort(fData, low, pi-1);
            quicksort(fData, pi+1, high);
        }
    }
    public int partition(List<FetchData> fData, int low, int high){
        Long pivot = fData.get(high).getPrice();
        int i = (low - 1);

        for(int j = low; j <= high; j++){
            if(fData.get(j).getPrice() < pivot){
                i++;
                swap(fData, i, j);
            }
        }
        swap(fData, i+1, high);
        return (i+1);
    }

    public void quicksort2(List<FetchData> fData, int low, int high){
        if(low<high){
            int pi = partition2(fData, low, high);

            quicksort2(fData, low, pi-1);
            quicksort2(fData, pi+1, high);
        }
    }
    public int partition2(List<FetchData> fData, int low, int high){
        Long pivot = fData.get(high).getPrice();
        int i = (low - 1);

        for(int j = low; j <= high; j++){
            if(fData.get(j).getPrice() > pivot){
                i++;
                swap(fData, i, j);
            }
        }
        swap(fData, i+1, high);
        return (i+1);
    }

    public void swap(List<FetchData> fData, int i, int j){
        FetchData temp = fData.get(i);
        fData.set(i,fData.get(j));
        fData.set(j,temp);
    }

    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view) {
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

    }

    public void ClickHome(View view) {
        redirectActivity(this, Home.class);


    }

    public void ClickGridview(View view) {
        recreate();
    }

    public void ClickCartActivity(View view) {
        redirectActivity(this, CartActivity.class);
    }

    public void ClickLogout(View view) {
        logout(this);
    }

    public static void logout(Activity activity) {
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

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_sort);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.action_sort){
            showSortDialog();
        }
        return true;
    }

    private void showSortDialog() {
        String[] sortOptions={"Highest priced first","Lowest priced first"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Sort by")
                .setIcon(R.drawable.ic_action_sort)
                .setItems(sortOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0){
                            SharedPreferences.Editor editor=mSharedPref.edit();
                            editor.putString("Sort","newest");
                            editor.apply();
                            recreate();
                        }
                        else if(which==1){
                            SharedPreferences.Editor editor=mSharedPref.edit();
                            editor.putString("Sort","oldest");
                            editor.apply();
                            recreate();
                        }
                    }
                });
        builder.show();
    }
}