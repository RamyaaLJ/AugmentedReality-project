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

public class MirrorAdapter extends RecyclerView.Adapter{
    DrawerLayout drawerLayout;
    List<MirrorData> mirrorDataList;
    public MirrorAdapter(List<MirrorData> mirrorDataList) {
        this.mirrorDataList = mirrorDataList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mirror_layout,parent,false);
        MirrorHolderClass mirrorHolderClass=new MirrorAdapter.MirrorHolderClass(view);
        return mirrorHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MirrorHolderClass mirrorHolderClass =(MirrorHolderClass) holder;
        final MirrorData mirrorData=mirrorDataList.get(position);
        mirrorHolderClass.name.setText(mirrorData.getName());
        mirrorHolderClass.price.setText(mirrorData.getPrice().toString());
        Glide.with(mirrorHolderClass.img.getContext()).load(mirrorData.getImg())
                .into(mirrorHolderClass.img);
        mirrorHolderClass.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(view.getContext(),ProductDetails.class);
                intent.putExtra("name",mirrorData.getName());
                intent.putExtra("price",mirrorData.getPrice().toString());
                intent.putExtra("img",mirrorData.getImg());
                intent.putExtra("offer",mirrorData.getOffer());
                intent.putExtra("save",mirrorData.getSave());
                intent.putExtra("warranty",mirrorData.getWarranty());
                view.getContext().startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return mirrorDataList.size();
    }
    public void filterList(ArrayList<MirrorData> filteredList){
        mirrorDataList=filteredList;
        notifyDataSetChanged();
    }
    public class MirrorHolderClass extends RecyclerView.ViewHolder{
        TextView name,price;
        ImageView img;
        CardView layout;
        public MirrorHolderClass(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.mtextView2);
            price=itemView.findViewById(R.id.mtextView3);
            img=itemView.findViewById(R.id.mimageView2);
            drawerLayout=(DrawerLayout) itemView.findViewById(R.id.drawer_layout);
            layout=itemView.findViewById(R.id.layout_card3);
        }
    }
}
