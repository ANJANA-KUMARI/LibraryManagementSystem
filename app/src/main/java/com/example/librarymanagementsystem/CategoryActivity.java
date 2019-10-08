package com.example.librarymanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CategoryActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private Category[] catArray = new Category[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        catArray[0] = new Category();
        catArray[0].image = "edu";
        catArray[0].text = "Education";

        catArray[1] = new Category();
        catArray[1].image = "edu";
        catArray[1].text = "Education";

        catArray[2] = new Category();
        catArray[2].image = "edu";
        catArray[2].text = "Education";

        catArray[3] = new Category();
        catArray[3].image = "edu";
        catArray[3].text = "Education";

        catArray[4] = new Category();
        catArray[4].image = "edu";
        catArray[4].text = "Education";

        catArray[5] = new Category();
        catArray[5].image = "edu";
        catArray[5].text = "Education";


        recyclerView = findViewById(R.id.catList);
        manager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(manager);
        adapter = new CategoryAdapter(catArray,this);
        recyclerView.setAdapter(adapter);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);


        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.bookbtn:
                        Intent i = new Intent(CategoryActivity.this, MainActivity.class);
                        startActivity(i);
                        break;

                    case R.id.searchbtn:
                        Intent i2 = new Intent(CategoryActivity.this, SearchActivity.class);
                        startActivity(i2);
                        break;
                }

                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

}
