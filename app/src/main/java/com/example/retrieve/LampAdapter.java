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

import java.util.ArrayList;
import java.util.List;

public class LampAdapter extends RecyclerView.Adapter{
    DrawerLayout drawerLayout;
    List<LampData> lampDataList;
    public LampAdapter(List<LampData> lampDataList) {
        this.lampDataList = lampDataList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lamp_layout,parent,false);
       LampHolderClass lampHolderClass =new LampAdapter.LampHolderClass(view);

        return lampHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LampHolderClass lampHolderClass =(LampHolderClass)holder;
        final LampData lampData=lampDataList.get(position);
        lampHolderClass.name.setText(lampData.getName());
        lampHolderClass.price.setText(lampData.getPrice().toString());
        Glide.with(lampHolderClass.img.getContext()).load(lampData.getImg())
                .into(lampHolderClass.img);
        lampHolderClass.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(view.getContext(),ProductDetails.class);
                intent.putExtra("name",lampData.getName());
                intent.putExtra("price",lampData.getPrice().toString());
                intent.putExtra("img",lampData.getImg());
                intent.putExtra("offer",lampData.getOffer());
                intent.putExtra("save",lampData.getSave());
                intent.putExtra("warranty",lampData.getWarranty());
                view.getContext().startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return lampDataList.size();
    }
    public void filterList(ArrayList<LampData> filteredList){
        lampDataList=filteredList;
        notifyDataSetChanged();
    }
    public class LampHolderClass extends RecyclerView.ViewHolder{
        TextView name,price;
        ImageView img;
        CardView layout;
        public LampHolderClass(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.ltextView2);
            price=itemView.findViewById(R.id.ltextView3);
            img=itemView.findViewById(R.id.limageView2);
            drawerLayout=(DrawerLayout) itemView.findViewById(R.id.drawer_layout);
            layout=itemView.findViewById(R.id.layout_card4);

        }
    }
}
