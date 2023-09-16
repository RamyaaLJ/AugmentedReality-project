package com.example.retrieve;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.core.Context;


import java.util.ArrayList;
import java.util.List;

public class HelperAdapter extends RecyclerView.Adapter{
DrawerLayout drawerLayout;
    List<FetchData> fetchDataList;
    public HelperAdapter(List<FetchData> fetchDataList) {
        this.fetchDataList = fetchDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        ViewHolderClass viewHolderClass=new ViewHolderClass(view);

        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass=(ViewHolderClass)holder;
        final FetchData fetchData=fetchDataList.get(position);
        viewHolderClass.name.setText(fetchData.getName());
        viewHolderClass.price.setText(fetchData.getPrice().toString());
        //viewHolderClass.warranty.setText(fetchData.getWarranty());
        Glide.with(viewHolderClass.img.getContext()).load(fetchData.getImg())
                .into(viewHolderClass.img);
      viewHolderClass.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(view.getContext(),ProductDetails.class);
                intent.putExtra("name",fetchData.getName());
                intent.putExtra("price",fetchData.getPrice().toString());
                intent.putExtra("img",fetchData.getImg());
                intent.putExtra("offer",fetchData.getOffer());
                intent.putExtra("save",fetchData.getSave());
                intent.putExtra("warranty",fetchData.getWarranty());
                view.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return fetchDataList.size();
    }
    public void filterList(ArrayList<FetchData> filteredList){
        fetchDataList=filteredList;
        notifyDataSetChanged();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView name,price;
        ImageView img;
        CardView layout;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textView2);
            price=itemView.findViewById(R.id.textView3);
            img=itemView.findViewById(R.id.imageView2);
            //warranty=itemView.findViewById(R.id.warranty);
            drawerLayout=(DrawerLayout) itemView.findViewById(R.id.drawer_layout);
            layout=itemView.findViewById(R.id.layout_card);

        }
    }
}