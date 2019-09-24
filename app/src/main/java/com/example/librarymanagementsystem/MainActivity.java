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
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private Book bookArray[] = new Book[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookArray[0] = new Book();
        bookArray[0].title = "Harry Potter";
        bookArray[0].cover = "book1";


        bookArray[1] = new Book();
        bookArray[1].title = "Harry Potter 2";
        bookArray[1].cover = "book2";

        bookArray[2] = new Book();
        bookArray[2].title = "Harry Potter 3";
        bookArray[2].cover = "book1";

        bookArray[3] = new Book();
        bookArray[3].title = "Harry Potter 3";
        bookArray[3].cover = "book1";

        bookArray[4] = new Book();
        bookArray[4].title = "Harry Potter 3";
        bookArray[4].cover = "book1";

        bookArray[5] = new Book();
        bookArray[5].title = "Harry Potter 3";
        bookArray[5].cover = "book1";

        bookArray[6] = new Book();
        bookArray[6].title = "Harry Potter 3";
        bookArray[6].cover = "book1";

        bookArray[7] = new Book();
        bookArray[7].title = "Harry Potter 3";
        bookArray[7].cover = "book1";

        bookArray[8] = new Book();
        bookArray[8].title = "Harry Potter 3";
        bookArray[8].cover = "book1";

        bookArray[9] = new Book();
        bookArray[9].title = "Harry Potter 3";
        bookArray[9].cover = "book1";

        bookArray[10] = new Book();
        bookArray[10].title = "Harry Potter 3";
        bookArray[10].cover = "book1";

        bookArray[11] = new Book();
        bookArray[11].title = "Harry Potter 3";
        bookArray[11].cover = "book1";


        recyclerView = findViewById(R.id.bookList);
        manager = new GridLayoutManager(this, 3);

        recyclerView.setLayoutManager(manager);
        adapter = new BookAdapter(bookArray,this);
        recyclerView.setAdapter(adapter);

        BottomNavigationView v = findViewById(R.id.bottom_navigation);
        Log.i("MAIN_ACTIVITY", v.toString());
        v.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.collectionbtn :
                        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.searchbtn :
                        Intent intent1 = new Intent(MainActivity.this, SearchActivity.class);
                        startActivity(intent1);
                        break;

                }
                return true;
            }
        });


        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);
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
