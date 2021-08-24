package com.example.wallpaper_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wallpaper_app.R;
import com.example.wallpaper_app.fragments.CategoryFragment;
import com.example.wallpaper_app.fragments.HomeFragment;
import com.example.wallpaper_app.models.Wallpaper;
import com.example.wallpaper_app.network.VolleySingleton;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment ;
    CategoryFragment categoryFragment ;
    TabLayout tabLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar) ;
        toolbar.setLogo(getDrawable(R.drawable.ic_letter_w_topbar));
        setSupportActionBar(toolbar);

        homeFragment = new HomeFragment() ;
        categoryFragment = new CategoryFragment() ;

        createFragment(homeFragment);
        createFragment(categoryFragment);
        showFragment(homeFragment);

        tabLayout = findViewById(R.id.tab_layout) ;
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getText().equals("Home"))
                {
                    hideFragment(categoryFragment);
                    showFragment(homeFragment);
                } else {
                    hideFragment(homeFragment);
                    showFragment(categoryFragment);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search) ;
        SearchView searchView =(SearchView) menuItem.getActionView() ;

        EditText editText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        editText.setTextColor(Color.WHITE);
        editText.setHint("Search");
        editText.setHintTextColor(Color.WHITE);
        ImageView imageViewClose = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        imageViewClose.setImageResource(R.drawable.ic_cancel);

        return super.onCreateOptionsMenu(menu);

    }

    private void createFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout, fragment)
                .hide(fragment)
                .commit();
    }
    private void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .show(fragment)
                .commit();
    }
    private void hideFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .hide(fragment)
                .commit();
    }



}