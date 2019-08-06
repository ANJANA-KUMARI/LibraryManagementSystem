package com.example.librarymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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


    }
}
