package com.example.retrieve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;


public class AutoSlider extends AppCompatActivity {
    SliderLayout sliderLayout;
    Button logobutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_slider);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sliderLayout=findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setScrollTimeInSec(1);
        setSliderViews();
        logobutton= (Button) findViewById(R.id.logoid);
        logobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AutoSlider.this, Login.class);
                startActivity(intent);
            }
        });
    }
    private void setSliderViews(){
        for(int i=0;i<=3;i++){
            DefaultSliderView sliderView=new DefaultSliderView(this);
            switch(i){
                case 0:
                    sliderView.setImageDrawable(R.drawable.back5);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.back4);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.back6);
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.back7);
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            //sliderView.setDescription("setDescription"+(i+1));
            final int finalI =i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(AutoSlider.this,"This is slider"+(finalI+1),Toast.LENGTH_SHORT).show();

                }
            });
            sliderLayout.addSliderView(sliderView );

        }
    }
}