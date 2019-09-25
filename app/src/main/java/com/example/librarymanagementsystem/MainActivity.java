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

import com.example.librarymanagementsystem.db.BookDbHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private ArrayList<Book> bookArray;
    private BookAdapter.BookClickListener bookClickListener = new BookAdapter.BookClickListener() {
        @Override
        public void onBookClick(int position) {
            Intent intent = new Intent(MainActivity.this, BookViewActivity.class);
            intent.putExtra(BookViewActivity.BOOKID, bookArray.get(position).id);
            startActivity(intent);
        }
    };

    private BookDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.dbHelper = new BookDbHelper(this);


        this.initializeViews();

        // malu meka uncomment karala OnResume eke me line ek comment karala run karala balanna malu book ekak add karala
        // ita passe meka comment karala onresume eke eka uncomment karala aya run karala balanna malu book ekak add karala mokadda difference ek kiyala malu
//        this.setupBookRecyclerView();


        this.setupBottomNavigationBar();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // api meka onCreate eke nathuw onResume eke mehem call karanaw malu
        // mokad dan api AddBookActivity eken 1k add kalam malu aya back enawane, ehem me MainActivity ekat back enakot aya api db eken update unu data ganna oni ne malu
        // eth ehem back enakot me activity eke oncreate run venne na malu onResume thama run venne, eka nisa thama database eken data ganna tika api onResume eke damme
        this.setupBookRecyclerView();
    }

    private void setupBookRecyclerView(){
        // me tiken ne malu ape recycler view ek setup venne

        // api meka onCreate eke nathuw onResume ekedamu malu, ai e kiyala balala api ek damu malu
        // mn e dan book array ek thibba hama thanam ek ArrayList ekak kala malu mokad api danne nane book kiyak thiyed kiyala db eke ne malu


        // dan api db eken ganna yanne malu okkom tika

        this.bookArray = dbHelper.getAllBooks();

        manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);
        adapter = new BookAdapter(bookArray,this, bookClickListener);
        recyclerView.setAdapter(adapter);

    }

    private void initializeViews(){
        // me findViewById thien eva okkom malu 1k function ekak athule damma malu ethkot ape onCreate ek pahadile ne malu ne

        recyclerView = findViewById(R.id.bookList);
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

    private void setupBottomNavigationBar(){
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
                        Intent intent1 = new Intent(MainActivity.this, SearchActivity
                                .class);
                        startActivity(intent1);
                        break;

                }
                return true;
            }
        });

    }



}
