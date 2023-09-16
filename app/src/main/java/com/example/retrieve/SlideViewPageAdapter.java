package com.example.retrieve;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SlideViewPageAdapter extends PagerAdapter {
    Context ctx;

    public SlideViewPageAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater= (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_screen,container,false);
         ImageView logo=view.findViewById(R.id.logo);
         ImageView ind1=view.findViewById(R.id.ind1);
         ImageView ind2=view.findViewById(R.id.ind2);
         ImageView ind3=view.findViewById(R.id.ind3);
         ImageView back=view.findViewById(R.id.back);
         ImageView next=view.findViewById(R.id.next);
         TextView title=view.findViewById(R.id.title);
         Button button=view.findViewById(R.id.split1);
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(ctx,MainActivity.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                 ctx.startActivity(intent);
             }
         });
         next.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 SlideActivity.viewPager.setCurrentItem(position+1);
             }
         });
         back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 SlideActivity.viewPager.setCurrentItem(position-1);
             }
         });
         switch(position)
         {
             case 0:
                 logo.setImageResource(R.drawable.slide2);
                 ind1.setImageResource(R.drawable.selected);
                 ind2.setImageResource(R.drawable.unselected);
                 ind3.setImageResource(R.drawable.unselected);
                 title.setText("Giving is not just about making a donation ,It is about making a difference");
                 back.setVisibility(View.GONE);
                 next.setVisibility(View.VISIBLE);
                 break;
             case 1:
                 logo.setImageResource(R.drawable.slide3);
                 ind1.setImageResource(R.drawable.unselected);
                 ind2.setImageResource(R.drawable.selected);
                 ind3.setImageResource(R.drawable.unselected);

                 title.setText("No one has become poor from giving");
                 back.setVisibility(View.VISIBLE);
                 next.setVisibility(View.VISIBLE);
                 break;
             case 2:
                 logo.setImageResource(R.drawable.slide4);
                 ind1.setImageResource(R.drawable.unselected);
                 ind2.setImageResource(R.drawable.unselected);
                 ind3.setImageResource(R.drawable.selected);
                 title.setText("It's not how much we give but how much love we put into giving");
                 back.setVisibility(View.VISIBLE);
                 next.setVisibility(View.GONE);
                 break;


         }
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
