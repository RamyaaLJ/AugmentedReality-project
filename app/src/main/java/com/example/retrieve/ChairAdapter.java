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

public class ChairAdapter extends RecyclerView.Adapter {
    DrawerLayout drawerLayout;
    List<Gridview1Data> chairDataList;
    public ChairAdapter(List<Gridview1Data> chairDataList) {
        this.chairDataList = chairDataList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chair_layout,parent,false);
     //   SofaAdapter.SofaHolderClass sofaHolderClass =new SofaAdapter.SofaHolderClass(view);

     //   return sofaHolderClass;
        ChairHolderClass chairHolderClass=new ChairAdapter.ChairHolderClass(view);
        return chairHolderClass;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChairAdapter.ChairHolderClass chairHolderClass =(ChairAdapter.ChairHolderClass)holder;
        final Gridview1Data gridview1Data=chairDataList.get(position);
        chairHolderClass.name.setText(gridview1Data.getName());
        chairHolderClass.price.setText(gridview1Data.getPrice().toString());
        Glide.with(chairHolderClass.img.getContext()).load(gridview1Data.getImg())
                .into(chairHolderClass.img);
        chairHolderClass.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(view.getContext(),ProductDetails.class);
                intent.putExtra("name",gridview1Data.getName());
                intent.putExtra("price",gridview1Data.getPrice().toString());
                intent.putExtra("img",gridview1Data.getImg());
                intent.putExtra("offer",gridview1Data.getOffer());
                intent.putExtra("save",gridview1Data.getSave());
                intent.putExtra("warranty",gridview1Data.getWarranty());
                view.getContext().startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return chairDataList.size();
    }
    public void filterList(ArrayList<Gridview1Data> filteredList){
        chairDataList=filteredList;
        notifyDataSetChanged();
    }
    public class ChairHolderClass extends RecyclerView.ViewHolder{
        TextView name,price;
        ImageView img;
        CardView layout;
        public ChairHolderClass(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.ctextView2);
            price=itemView.findViewById(R.id.ctextView3);
            img=itemView.findViewById(R.id.cimageView2);
            drawerLayout=(DrawerLayout) itemView.findViewById(R.id.drawer_layout);
            layout=itemView.findViewById(R.id.layout_card2);
        }
    }
}
