package com.example.wallpaper_app.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wallpaper_app.R;
import com.example.wallpaper_app.models.Wallpaper;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<Wallpaper> list ;
    private Context context;

    public CustomAdapter(List<Wallpaper> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(list.get(position).getUrlImage()).into(holder.getImageViewWallpaper()) ;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewWallpaper ;

        public ViewHolder(View view) {
            super(view);
            imageViewWallpaper = view.findViewById(R.id.wallpaper) ;
        }

        public ImageView getImageViewWallpaper() {
            return imageViewWallpaper;
        }
    }
}
