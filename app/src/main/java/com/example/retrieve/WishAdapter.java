package com.example.retrieve;

import android.content.Intent;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.List;

public class WishAdapter extends RecyclerView.Adapter<WishAdapter.WishHolder> {
    DrawerLayout drawerLayout;
    List<Wish> wishList;
    public WishAdapter(List<Wish> wishList) {
        this.wishList = wishList;
    }
    @NonNull
    @Override
    public WishHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.wish_item_layout,parent,false);

        WishHolder wishHolder=new WishHolder(view);
        return wishHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WishHolder holder, int position) {
        WishAdapter.WishHolder wishHolder=(WishAdapter.WishHolder)holder;
        final Wish wish=wishList.get(position);
        wishHolder.name.setText(wish.getName());
        wishHolder.price.setText(wish.getPrice());

    }

    @Override
    public int getItemCount() {
        return wishList.size();
    }

    public class WishHolder extends RecyclerView.ViewHolder{
        TextView name,price;
        //ElegantNumberButton price;
        ImageView img;
        CardView layout;
        public WishHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.wish_item_name);
            price=itemView.findViewById(R.id.wish_item_price);

            drawerLayout=(DrawerLayout) itemView.findViewById(R.id.drawer_layout);
            layout=itemView.findViewById(R.id.wish_layout);
        }
    }
}
