package com.example.wallpaper_app.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wallpaper_app.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.io.IOException;


public class AboutWallpaperActivity extends AppCompatActivity {

    private ImageView imageViewWallpaper ;
    private BottomSheetDialog bottomSheetDialog ;
    private ImageView imageViewMore ;
    private CoordinatorLayout coordinatorLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_wallpaper);

        coordinatorLayout = findViewById(R.id.coordinatorLayout) ;
        imageViewMore = findViewById(R.id.more) ;

        imageViewWallpaper = findViewById(R.id.wallpaper) ;
        imageViewWallpaper.setDrawingCacheEnabled(true);

        Intent intent = getIntent() ;
        String imgUrl = intent.getStringExtra("imgUrl") ;

        Picasso.get().load(imgUrl).into(imageViewWallpaper) ;

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

        LinearLayout linearLayoutOnlyScreen = bottomSheetDialog.findViewById(R.id.linear_layout_only_screen) ;
        LinearLayout linearLayoutLockScreen = bottomSheetDialog.findViewById(R.id.linear_layout_lock_screen) ;
        LinearLayout linearLayoutBoth = bottomSheetDialog.findViewById(R.id.linear_layout_both_screen) ;

        linearLayoutOnlyScreen.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                setSystemWallpaper();
                bottomSheetDialog.dismiss();
                Snackbar.make(coordinatorLayout,"wallpaper updated",Snackbar.LENGTH_LONG).show();          }
        });

        linearLayoutLockScreen.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                setLockWallpaper() ;
                bottomSheetDialog.dismiss();
                Snackbar.make(coordinatorLayout,"wallpaper updated",Snackbar.LENGTH_LONG).show();
            }
        });

        linearLayoutBoth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBothWallpaper() ;
                bottomSheetDialog.dismiss();
                Snackbar.make(coordinatorLayout,"wallpaper updated",Snackbar.LENGTH_LONG).show();
            }
        });

        bottomSheetDialog.show();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setSystemWallpaper()
    {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(AboutWallpaperActivity.this);
        try {
            wallpaperManager.setBitmap(imageViewWallpaper.getDrawingCache(),null,true,WallpaperManager.FLAG_SYSTEM);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setLockWallpaper()
    {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(AboutWallpaperActivity.this);
        try {
            wallpaperManager.setBitmap(imageViewWallpaper.getDrawingCache(),null,true,WallpaperManager.FLAG_LOCK);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBothWallpaper()
    {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(AboutWallpaperActivity.this);
        try {
            wallpaperManager.setBitmap(imageViewWallpaper.getDrawingCache());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}