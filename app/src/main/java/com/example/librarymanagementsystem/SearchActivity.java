package com.example.librarymanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.librarymanagementsystem.db.BookDbHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private ArrayList<Book> bookArray = new ArrayList<>() ;
    private ArrayList<Book> originalBookArray;
    private BookDbHelper dbHelper;

    private BookAdapter.BookClickListener bookClickListener = new BookAdapter.BookClickListener() {
        @Override
        public void onBookClick(int position) {
            Intent intent = new Intent(SearchActivity.this, BookViewActivity.class);
            intent.putExtra(BookViewActivity.BOOKID, bookArray.get(position).id);
            startActivity(intent);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Search Books");
        setContentView(R.layout.activity_search);
        dbHelper = new BookDbHelper(this);

        SearchView search = findViewById(R.id.search);
        recyclerView = findViewById(R.id.bookList);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterBookList(newText);
                return true;
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.collectionbtn:
                        Intent i = new Intent(SearchActivity.this, CategoryActivity.class);
                        startActivity(i);
                        break;

                    case R.id.bookbtn:
                        Intent i2 = new Intent(SearchActivity.this, MainActivity.class);
                        startActivity(i2);
                        break;
                }

                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupBookRecyclerView();
    }

    private void setupBookRecyclerView(){
        // me tiken ne malu ape recycler view ek setup venne

        // api meka onCreate eke nathuw onResume ekedamu malu, ai e kiyala balala api ek damu malu
        // mn e dan book array ek thibba hama thanam ek ArrayList ekak kala malu mokad api danne nane book kiyak thiyed kiyala db eke ne malu


        // dan api db eken ganna yanne malu okkom tika

        this.originalBookArray = dbHelper.getAllBooks();
        bookArray.removeAll(bookArray);
        bookArray.addAll(originalBookArray);

        manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);
        adapter = new BookAdapter(bookArray,this, bookClickListener);
        recyclerView.setAdapter(adapter);



    }

    private void filterBookList(String query){
        if(query.length() == 0){
            this.bookArray.removeAll(this.bookArray);
            this.bookArray.addAll(this.originalBookArray);
            adapter.notifyDataSetChanged();
            return;
        }

        ArrayList<Book> filteredList = new ArrayList<>();

        for (Book b: this.bookArray
        ) {
            if(b.title.contains(query)){
                filteredList.add(b);
            }
        }

        this.bookArray.removeAll(this.bookArray);

        this.bookArray.addAll(filteredList);

        adapter.notifyDataSetChanged();

    }
}
