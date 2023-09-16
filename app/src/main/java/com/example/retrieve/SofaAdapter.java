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

public class SofaAdapter extends RecyclerView.Adapter{
    DrawerLayout drawerLayout;
    List<SofaData> sofaDataList;
    public SofaAdapter(List<SofaData> sofaDataList) {
        this.sofaDataList = sofaDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sofa_layout,parent,false);
        SofaHolderClass sofaHolderClass =new SofaHolderClass(view);
        return sofaHolderClass;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SofaHolderClass sofaHolderClass =(SofaHolderClass)holder;
        final SofaData sofaData=sofaDataList.get(position);
        sofaHolderClass.name.setText(sofaData.getName());
        sofaHolderClass.price.setText(sofaData.getPrice().toString());
        Glide.with(sofaHolderClass.img.getContext()).load(sofaData.getImg())
                .into(sofaHolderClass.img);
      sofaHolderClass.layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent=new Intent(view.getContext(),ProductDetails.class);
                intent.putExtra("name",sofaData.getName());
                intent.putExtra("price",sofaData.getPrice().toString());
                intent.putExtra("img",sofaData.getImg());
                intent.putExtra("offer",sofaData.getOffer());
                intent.putExtra("save",sofaData.getSave());
                intent.putExtra("warranty",sofaData.getWarranty());
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return sofaDataList.size();
    }
    public void filterList(ArrayList<SofaData> filteredList){
        sofaDataList=filteredList;
        notifyDataSetChanged();
    }

    public class SofaHolderClass extends RecyclerView.ViewHolder{
        TextView name,price;
        ImageView img;
        CardView layout;
        public SofaHolderClass(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.stextView2);
            price=itemView.findViewById(R.id.stextView3);
            img=itemView.findViewById(R.id.simageView2);
            drawerLayout=(DrawerLayout) itemView.findViewById(R.id.drawer_layout);
            layout=itemView.findViewById(R.id.layout_card1);
        }
    }
}