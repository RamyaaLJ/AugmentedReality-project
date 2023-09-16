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
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    List<Cart> cart;
    RecyclerView recyclerView1;
    //RecyclerView.LayoutManager layoutManager;
    CartAdapter cartAdapter;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    Button place;
    String price[];
    String name[];

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        recyclerView1=findViewById(R.id.recyclerView1);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        recyclerView1.setLayoutManager(gridLayoutManager);
        place=findViewById(R.id.place);

        // firebaseAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("cart");
        //user=firebaseAuth.getCurrentUser();
        // databaseReference=FirebaseDatabase.getInstance().getReference("user").child("cart");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cart=new ArrayList<>();
                for(DataSnapshot ds:snapshot.getChildren()) {
                    Cart data=ds.getValue(Cart.class);
                    cart.add(data);
                }
                cartAdapter=new CartAdapter(cart);



                price = new String[cart.size()];
                name = new String[cart.size()];
                for(int i=0; i<cart.size();i++){
                    price[i] = String.valueOf(cart.get(i).getPrice());
                    name[i] = String.valueOf(cart.get(i).getName());
                }


                /*
                double total = 0;
                for(int i=0; i<sum.length; i++){
                    total += sum[i];
                }

                 */


                recyclerView1.setAdapter(cartAdapter);
place.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int itemCount = recyclerView1.getAdapter().getItemCount();

        String value[] = new String[itemCount];
        for(int i=0; i<itemCount; i++) {
            try {
                RecyclerView.ViewHolder holder = recyclerView1.findViewHolderForAdapterPosition(i);
                Spinner spin = (Spinner) holder.itemView.findViewById(R.id.cart_item_amount);
                String val = spin.getSelectedItem().toString();
                value[i] = val;
            }
            catch(Exception e){
                value[i] = "1";
            }
        }
        Intent intent=new Intent(CartActivity.this,OrderPlaced.class);
        intent.putExtra("value",value);
        intent.putExtra("price",price);
        intent.putExtra("name",name);
        startActivity(intent);
        // value array and price array
       // Toast.makeText(CartActivity.this, String.valueOf(name[2]+"-"+String.valueOf(price[2])), Toast.LENGTH_LONG).show();

    }

});


                /*
                for (int i=0; i<itemCount; i++) {
                    RecyclerView.ViewHolder holder1 = recyclerView1.findViewHolderForAdapterPosition(i);
                    if (holder != null) {
                        val accountNameView = holder.itemView.findViewById<View>(R.id.accountTileAccountName) as TextView
                        if (accountNameView.text == accountName) {
                            val targetView = holder.itemView.findViewById<View>(targetViewId)
                            return itemMatcher.matches(targetView)
                        }
                    }
                }

                /*for (int x = recyclerView1.getChildCount(), i = 0; i < x; ++i) {
                    RecyclerView.ViewHolder holder = recyclerView1.getChildViewHolder(recyclerView1.getChildAt(0));
                    Spinner spin = holder.itemView.findViewById(R.id.cart_item_amount);

                    String text = spin.getSelectedItem().toString();

                }*/

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
    public void ClickLamp(View view){
        redirectActivity(this,Lamp.class);
    }
    public void ClickHome(View view){
        redirectActivity(this,Home.class);
    }
    public void ClickMirror(View view) {redirectActivity(this,Mirror.class);}
    public void Clicksofa(View view){
        redirectActivity(this,Sofa.class);
    }
    public void ClickGridview1(View view){
        redirectActivity(this,Gridview1.class);
    }
    public void ClickGridview(View view){
        redirectActivity(this,Gridview.class);
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
