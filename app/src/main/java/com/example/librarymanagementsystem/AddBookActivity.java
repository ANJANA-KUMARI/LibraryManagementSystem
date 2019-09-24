package com.example.librarymanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.librarymanagementsystem.db.BookDbHelper;
import com.google.android.material.textfield.TextInputEditText;

import static java.security.AccessController.getContext;

public class AddBookActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    // api dan e textbox valat reference ganna oni malu
    private TextInputEditText titleTxt;
    private TextInputEditText authorTxt;
    private TextInputEditText pagesTxt;
    private TextInputEditText summaryTxt;
    private  int selectedCategoryId =  -1;

    private BookDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AddBookActivity)this).getSupportActionBar().setTitle("Add Book");
        setContentView(R.layout.activity_add_book);

        Spinner spinner = findViewById(R.id.categoryID);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // call initializeViews here malu
        this.initializeViews();

        // here we create a new bookdb helper malu
        this.dbHelper = new BookDbHelper(this);

    }

    private void initializeViews(){
        titleTxt = findViewById(R.id.book_title_txt);
        authorTxt = findViewById(R.id.book_author_txt);
        pagesTxt = findViewById(R.id.book_pages_txt);
        summaryTxt = findViewById(R.id.book_summary_txt);
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
        bookToInsert.pages = Integer.valueOf(this.pagesTxt.getText().toString());
        bookToInsert.summary = this.summaryTxt.getText().toString();
        bookToInsert.catid = selectedCategoryId;
        bookToInsert.cover = "";

        int result = (int)dbHelper.insertBook(bookToInsert);

        if(result  != -1){
            // success
            // navigate to main activity malu
            // this will go back malu, back to the main activity
            Toast.makeText(AddBookActivity.this, "Inserted " + result , Toast.LENGTH_SHORT).show();
            finish();

        }else{
            // display a tost saying insert failed malu
            Toast.makeText(AddBookActivity.this, "Insert failed " + result , Toast.LENGTH_SHORT).show();
        }

    }
}
