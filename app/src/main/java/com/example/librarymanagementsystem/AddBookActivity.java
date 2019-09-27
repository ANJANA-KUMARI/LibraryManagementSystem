package com.example.librarymanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import com.example.librarymanagementsystem.db.BookDbHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


import static java.security.AccessController.getContext;

public class AddBookActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static final String BOOKID  = "BOOKID";

    // api dan e textbox valat reference ganna oni malu
    private TextInputEditText titleTxt;
    private TextInputEditText authorTxt;
    private TextInputEditText pagesTxt;
    private TextInputEditText summaryTxt;
    Spinner spinner;
    private TextView selectedCoverImageTxt;
    private  int selectedCategoryId =  -1;
    private String selectedImagePath = null;

    private FloatingActionButton fileUploadBtn;
    private BookDbHelper dbHelper;

    private Book bookToUpdate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AddBookActivity)this).getSupportActionBar();
        setContentView(R.layout.activity_add_book);

         spinner = findViewById(R.id.categoryID);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // call initializeViews here malu
        this.initializeViews();

        // here we create a new bookdb helper malu
        this.dbHelper = new BookDbHelper(this);

        this.setupBottomNavigationBar();

        Intent reserveIntent = getIntent();

        if(reserveIntent.hasExtra(BOOKID)){
            int bookId = reserveIntent.getExtras().getInt(BOOKID);

            bookToUpdate = dbHelper.getBook(bookId);
            this.setTitle("Update the Book");

            this.setBookDetails(bookToUpdate);
        } else {
            setTitle("Add Book");
        }


    }





    private void initializeViews(){
        titleTxt = findViewById(R.id.book_title_txt);
        authorTxt = findViewById(R.id.book_author_txt);
        pagesTxt = findViewById(R.id.book_pages_txt);
        summaryTxt = findViewById(R.id.book_summary_txt);
        selectedCoverImageTxt = findViewById(R.id.selectedFileNameTxt);

        fileUploadBtn = findViewById(R.id.file_upload_btn);
        fileUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectBookCover();
            }
        });

    }

    private void setBookDetails(Book book) {
        titleTxt.setText(book.title);
        authorTxt.setText(book.author);
        pagesTxt.setText(String.valueOf(book.pages));
        spinner.setSelection(book.catid);
        summaryTxt.setText(book.summary);
        selectedImagePath = book.cover;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.addbook, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_book_save :

                            onBookSaveClicked();
                            break;
            case R.id.action_cancel :
                            onBackPressed();
                            break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        selectedCategoryId = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    private void onBookSaveClicked(){
        // wee need to run this method when the user press the save button malu

        Book bookToInsert = new Book();
        bookToInsert.title  = this.titleTxt.getText().toString();
        bookToInsert.author = this.authorTxt.getText().toString();

        try{

            bookToInsert.pages = Integer.valueOf(this.pagesTxt.getText().toString());

        }catch (NumberFormatException e){
            // if user did not enter anything for the pages textbox or entered some letters, this exception will be thrown because we convert it into a number using the Integer.valueOf() method malu
            // so if this happens we assign the pages 0
            bookToInsert.pages = 0;
        }

        bookToInsert.summary = this.summaryTxt.getText().toString();
        bookToInsert.catid = selectedCategoryId;
        bookToInsert.cover = this.selectedImagePath;

        // before insert we have to validate this book malu, if it returns false then we cannot add the book so return
        if(!validateBook(bookToInsert)){
            return;
        }

        int result ;
        String msg;
        if(bookToUpdate == null){
            msg = "Successful Inserted";
            result = (int)dbHelper.insertBook(bookToInsert);
        }else {
            msg = "Successful Updated";
            bookToInsert.id = bookToUpdate.id;
            result = (int)dbHelper.updateBook(bookToInsert);
        }

        if(result  != -1){
            // success
            // navigate to main activity malu
            // this will go back malu, back to the main activity
            showToast(msg);
            if(bookToUpdate != null) {
                Intent intent = new Intent(AddBookActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                finish();
            }

        }else{
            // display a tost saying insert failed malu
            Toast.makeText(AddBookActivity.this, "Insert failed " + result , Toast.LENGTH_SHORT).show();
        }

    }

    // me method eken api user input validation balanaw malu
    private boolean validateBook(Book newBook){

        if(newBook.title.length() == 0){
            showToast("Title is required");
            return false;
        }else if(newBook.author.length() == 0){
            showToast("Author is required");
            return false;
        }else if(newBook.pages == 0){
            showToast("Pages cannot be 0");
            return false;
        }else if(newBook.cover == null){
            showToast("Cover image is required");
            return false;
        }

        // no invalid data so return true
        return true;
    }

    private void showToast(String msg){
        // ara tost ekak display karan ek hama velam gagaha inna bari nisa ek funtion ekakt damma malu
        Toast.makeText(AddBookActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


    private void selectBookCover(){
        ImagePicker.create(this)
                .returnMode(ReturnMode.GALLERY_ONLY)
                .single()
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(ImagePicker.shouldHandle(requestCode, resultCode, data)){
            Image image = ImagePicker.getFirstImageOrNull(data);
            Log.i("BOOKCOVER", image.getPath());
            selectedImagePath = image.getPath();
            selectedCoverImageTxt.setText(selectedImagePath);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void setupBottomNavigationBar(){
        BottomNavigationView v = findViewById(R.id.bottom_navigation);
        v.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.collectionbtn :
                        Intent intent = new Intent(AddBookActivity.this, CategoryActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.searchbtn :
                        Intent intent1 = new Intent(AddBookActivity.this, SearchActivity
                                .class);
                        startActivity(intent1);
                        break;
                    case R.id.bookbtn :
                        Intent intent2 = new Intent(AddBookActivity.this, MainActivity
                                .class);
                        startActivity(intent2);
                        break;

                }
                return true;
            }
        });

    }
}
