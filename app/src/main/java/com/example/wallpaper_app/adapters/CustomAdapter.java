package com.example.wallpaper_app.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.example.wallpaper_app.R;
import com.example.wallpaper_app.activities.AboutWallpaperActivity;
import com.example.wallpaper_app.models.Wallpaper;
import com.example.wallpaper_app.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<Wallpaper> list ;
    private Context context;
    private int page ;

    public CustomAdapter(List<Wallpaper> list, Context context) {
        this.list = list;
        this.context = context;
        page = 1 ;
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
        holder.getImageViewWallpaper().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AboutWallpaperActivity.class) ;
                intent.putExtra("imgUrl",list.get(position).getUrlImage()) ;
                context.startActivity(intent);
            }
        });
        if (position == getItemCount() - 1)
        {
            page = page + 1 ;
            requestNextWallpapers();
        }
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



    public void requestNextWallpapers()
    {

        String url = "https://api.pexels.com/v1/curated?page="+page+"&per_page=15" ;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("photos") ;
                    for (int i = 0 ; i < jsonArray.length() ; i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i) ;

                        int id = jsonObject.getInt("id") ;
                        String photographer = jsonObject.getString("photographer");

                        jsonObject = jsonObject.getJSONObject("src") ;

                        String urlImage = jsonObject.getString("portrait") ;

                        Wallpaper wallpaper = new Wallpaper(id,photographer,urlImage) ;
                        list.add(wallpaper) ;
                    }

                    notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ffff ");

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> map = new HashMap<>() ;
                map.put("Authorization",context.getString(R.string.api_key)) ;
                return map ;
            }
        };


        VolleySingleton volleySingleton = VolleySingleton.getInstance(context) ;
        volleySingleton.getRequestQueue().add(jsonObjectRequest);

    }
}
