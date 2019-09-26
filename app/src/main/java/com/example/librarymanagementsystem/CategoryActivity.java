package com.example.librarymanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CategoryActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private Category[] catArray = new Category[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        catArray[0] = new Category();
        catArray[0].image = "maths";
        catArray[0].text = "Mathematics";

        catArray[1] = new Category();
        catArray[1].image = "his";
        catArray[1].text = "History";

        catArray[2] = new Category();
        catArray[2].image = "cooking";
        catArray[2].text = "Cooking";

        catArray[3] = new Category();
        catArray[3].image = "religion";
        catArray[3].text = "Religion";

        catArray[4] = new Category();
        catArray[4].image = "sports";
        catArray[4].text = "Sport";

        catArray[5] = new Category();
        catArray[5].image = "medical";
        catArray[5].text = "Medical";

        catArray[6] = new Category();
        catArray[6].image = "biography";
        catArray[6].text = "Biography";

        catArray[7] = new Category();
        catArray[7].image = "grammar";
        catArray[7].text = "Grammar";


        recyclerView = findViewById(R.id.catList);
        manager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(manager);
        adapter = new CategoryAdapter(catArray,this);
        recyclerView.setAdapter(adapter);



        BottomNavigationView v = findViewById(R.id.bottom_navigation);
        Log.i("CATEGORY_ACTIVITY", v.toString());
        v.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.bookbtn :
                        Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.searchbtn :
                        Intent intent1 = new Intent(CategoryActivity.this, SearchActivity.class);
                        startActivity(intent1);
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
