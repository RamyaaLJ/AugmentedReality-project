package com.example.retrieve;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    DrawerLayout drawerLayout;
    List<Cart> cartList;
    public CartAdapter(List<Cart> cartList) {
        this.cartList = cartList;
    }
    @NonNull
    @Override

    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
        CartHolder cartHolder=new CartHolder(view);
        return cartHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        CartAdapter.CartHolder cartHolder=(CartAdapter.CartHolder)holder;
        final Cart cart=cartList.get(position);
        cartHolder.name.setText(cart.getName());
        cartHolder.price.setText(cart.getPrice());
        Glide.with(cartHolder.img.getContext()).load(cart.getImg())
                .into(cartHolder.img);


    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder{
        TextView name,price;
        //ElegantNumberButton price;
        ImageView img;
        CardView layout;
        Spinner mySpinner;
        public CartHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.cart_item_name);
            price=itemView.findViewById(R.id.cart_item_price);
            img=itemView.findViewById(R.id.cart_item_image);
            drawerLayout=(DrawerLayout) itemView.findViewById(R.id.drawer_layout);
            layout=itemView.findViewById(R.id.cart_layout);
            mySpinner=itemView.findViewById(R.id.cart_item_amount);
            // String[] items=new String[]{"1","2"};
            // ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items);
            //myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //mySpinner.setAdapter(myAdapter);


        }
    }
}
