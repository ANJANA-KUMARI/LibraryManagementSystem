package com.example.librarymanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.librarymanagementsystem.db.BookDbHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BookViewActivity extends AppCompatActivity {

    public static final String BOOKID = "BOOKID";

    private BookDbHelper dbHelper;

    private TextView titleText;
    private TextView categoryText;
    private TextView authorText;
    private TextView pagesText;
    private TextView summaryText;
    private ImageView coverImage;

    private Book bookToDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);

        initViews();

        Intent reserveIntent = getIntent();
        int bookID = reserveIntent.getExtras().getInt(BOOKID);

        dbHelper = new BookDbHelper(this);

        bookToDisplay = dbHelper.getBook(bookID);

        setBookDetails(bookToDisplay);

        setTitle(bookToDisplay.title);

        this.setupBottomNavigationBar();
    }

    private void initViews() {
        titleText = findViewById(R.id.titleText);
        authorText = findViewById(R.id.authorText);
        pagesText = findViewById(R.id.pagesText);
        categoryText = findViewById(R.id.categoryText);
        summaryText = findViewById(R.id.summaryText);
        coverImage = findViewById(R.id.coverImage);

    }

    private void setBookDetails(Book book) {
        titleText.setText(book.title);
        authorText.setText(book.author);
        pagesText.setText(String.valueOf(book.pages));
        categoryText.setText(getResources().getTextArray(R.array.categories)[book.catid]);
        summaryText.setText(book.summary);
        coverImage.setImageURI(Uri.parse(book.cover));
    }


    private void handleDeleteClick(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This book will be deleted permanently").setTitle("Delete book?");

        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dbHelper.deleteBook(bookToDisplay);
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.delete_book:
                handleDeleteClick();
                break;
            case R.id.edit_book:
                handleUpdateClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.book_view_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void handleUpdateClick(){
        Intent intent = new Intent(BookViewActivity.this, AddBookActivity.class);
        intent.putExtra(AddBookActivity.BOOKID, bookToDisplay.id);
        startActivity(intent);
    }

    private void setupBottomNavigationBar(){
        BottomNavigationView v = findViewById(R.id.bottom_navigation);
        v.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.collectionbtn :
                        Intent intent = new Intent(BookViewActivity.this, CategoryActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.searchbtn :
                        Intent intent1 = new Intent(BookViewActivity.this, SearchActivity
                                .class);
                        startActivity(intent1);
                        break;
                    case R.id.bookbtn :
                        Intent intent2 = new Intent(BookViewActivity.this, MainActivity
                                .class);
                        startActivity(intent2);
                        break;

                }
                return true;
            }
        });

    }
}
