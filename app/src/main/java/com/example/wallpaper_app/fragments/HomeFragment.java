package com.example.wallpaper_app.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wallpaper_app.R;
import com.example.wallpaper_app.adapters.CustomAdapter;
import com.example.wallpaper_app.models.Wallpaper;
import com.example.wallpaper_app.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {
    List<Wallpaper> list ;
    RecyclerView recyclerView ;
    CustomAdapter customAdapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recycler_view) ;

        customAdapter = new CustomAdapter(list,this.getContext()) ;

        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),2));
        recyclerView.setAdapter(customAdapter);

        requestWallpapers();

    }

    public void requestWallpapers()
    {

        String url = "https://api.pexels.com/v1/search?query=popular?page=1&per_page=15" ;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                list.clear();
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

                    customAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> map = new HashMap<>() ;
                map.put("Authorization",getString(R.string.api_key)) ;
                return map ;
            }
        };


        VolleySingleton volleySingleton = VolleySingleton.getInstance(this.getContext()) ;
        volleySingleton.getRequestQueue().add(jsonObjectRequest);

    }
}