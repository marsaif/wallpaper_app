package com.example.wallpaper_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wallpaper_app.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class AboutWallpaperActivity extends AppCompatActivity {

    private ImageView imageViewWallpaper ;
    private ImageView imageViewMore ;
    private BottomSheetDialog bottomSheetDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_wallpaper);

        imageViewWallpaper = findViewById(R.id.wallpaper) ;
        imageViewMore = findViewById(R.id.more) ;

        Intent intent = getIntent() ;
        String imgUrl = intent.getStringExtra("imgUrl") ;
        Glide.with(this).load(imgUrl).into(imageViewWallpaper) ;

        imageViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog() ;
            }
        });



    }

    public void showBottomSheetDialog()
    {
        bottomSheetDialog = new BottomSheetDialog(this) ;
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
        bottomSheetDialog.show();
    }
}