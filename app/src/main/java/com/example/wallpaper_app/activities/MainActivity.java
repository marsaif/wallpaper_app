package com.example.wallpaper_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.wallpaper_app.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar) ;
        toolbar.setLogo(getDrawable(R.drawable.ic_letter_w_topbar));
        setSupportActionBar(toolbar);
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
}