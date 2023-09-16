package com.example.retrieve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class OrderPlaced extends AppCompatActivity {
    LinearLayout linearLayout;
    LayoutInflater layoutInflater;
    TextView place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);
        place = findViewById(R.id.place);
        linearLayout = findViewById(R.id.linear);
        layoutInflater = LayoutInflater.from(this);

        Intent intent = getIntent();
        String[] name = intent.getStringArrayExtra("name");
        String[] price = intent.getStringArrayExtra("price");
        String[] value = intent.getStringArrayExtra("value");

        //Toast.makeText(OrderPlaced.this, String.valueOf(name[2]+"-"+String.valueOf(price[2])), Toast.LENGTH_LONG).show();


        //Toast.makeText(OrderPlaced.this, String.valueOf(name[2]+"-"+String.valueOf(price[2])), Toast.LENGTH_LONG).show();

        linearLayout.removeAllViews();

        double total=0;
        for(int i=0; i<name.length; i++){

                String n = name[i];
                String p = price[i];
                String v = value[i];
                String r = String.valueOf(Double.parseDouble(p)*Double.parseDouble(v));
                total += Double.parseDouble(p)*Double.parseDouble(v);

                View view = layoutInflater.inflate(R.layout.bill_row, linearLayout,false);
                TextView tv1 = view.findViewById(R.id.name);
                tv1.setText(n);
                TextView tv2 = view.findViewById(R.id.price);
                tv2.setText(p);
                TextView tv3 = view.findViewById(R.id.value);
                tv3.setText(v);
                TextView tv4 = view.findViewById(R.id.rate);
                tv4.setText(r);
                linearLayout.addView(view);

        }
        place.setText("Order Total : "+String.valueOf(total));




        //Toast.makeText(OrderPlaced.this, String.valueOf(name[2]+"-"+String.valueOf(price[2])), Toast.LENGTH_LONG).show();



    }
}