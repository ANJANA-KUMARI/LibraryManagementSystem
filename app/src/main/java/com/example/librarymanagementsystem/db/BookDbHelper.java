package com.example.librarymanagementsystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.librarymanagementsystem.Book;
import com.example.librarymanagementsystem.Category;
import com.example.librarymanagementsystem.R;

import java.util.ArrayList;
import java.util.List;

public class BookDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Library.db";

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BookContract.SQL_CREATE_BOOKS);
        sqLiteDatabase.execSQL(BookContract.SQL_CREATE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.putNull(BookContract.BookEntry.COLUMN_NAME_ID);
        values.put(BookContract.BookEntry.COLUMN_NAME_TITLE, book.title);
        values.put(BookContract.BookEntry.COLUMN_NAME_AUTHOR, book.author);
        values.put(BookContract.BookEntry.COLUMN_NAME_PAGES, book.pages);
        values.put(BookContract.BookEntry.COLUMN_NAME_SUMMARY, book.summary);
        values.put(BookContract.BookEntry.COLUMN_NAME_CATID, book.catid);
        values.put(BookContract.BookEntry.COLUMN_NAME_COVER, book.cover);

        long newRowId = db.insert(BookContract.BookEntry.TABLE_NAME, null, values);
        return newRowId;
    }

    public long updateBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BookContract.BookEntry.COLUMN_NAME_TITLE, book.title);
        values.put(BookContract.BookEntry.COLUMN_NAME_AUTHOR, book.author);
        values.put(BookContract.BookEntry.COLUMN_NAME_PAGES, book.pages);
        values.put(BookContract.BookEntry.COLUMN_NAME_SUMMARY, book.summary);
        values.put(BookContract.BookEntry.COLUMN_NAME_CATID, book.catid);
        values.put(BookContract.BookEntry.COLUMN_NAME_COVER, book.cover);

        String selection = " WHERE " + BookContract.BookEntry.COLUMN_NAME_ID + " = ?";
        String[] selectionArgs = {Integer.toString(book.id)};

        int count = db.update(
                BookContract.BookEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        return count;
    }

    public int deleteBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = " WHERE " + BookContract.BookEntry.COLUMN_NAME_ID + " = ?";
        String[] selectiionArgs = {Integer.toString(book.id)};

        int deleteRows = db.delete(BookContract.BookEntry.TABLE_NAME, selection, selectiionArgs);
        return deleteRows;
    }

    public List<Book> getAllBooks() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                BookContract.BookEntry.COLUMN_NAME_ID,
                BookContract.BookEntry.COLUMN_NAME_TITLE,
                BookContract.BookEntry.COLUMN_NAME_AUTHOR,
                BookContract.BookEntry.COLUMN_NAME_PAGES,
                BookContract.BookEntry.COLUMN_NAME_SUMMARY,
                BookContract.BookEntry.COLUMN_NAME_CATID,
                BookContract.BookEntry.COLUMN_NAME_COVER
        };

        Cursor cursor = db.query(BookContract.BookEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<Book> bookList = new ArrayList<Book>();

        while (cursor.moveToNext()) {
            Book book = new Book();

            book.id = cursor.getInt(cursor.getColumnIndexOrThrow(BookContract.BookEntry.COLUMN_NAME_ID));
            book.title = cursor.getString(cursor.getColumnIndexOrThrow(BookContract.BookEntry.COLUMN_NAME_TITLE));
            book.author = cursor.getString(cursor.getColumnIndexOrThrow(BookContract.BookEntry.COLUMN_NAME_AUTHOR));
            book.pages = cursor.getInt(cursor.getColumnIndexOrThrow(BookContract.BookEntry.COLUMN_NAME_PAGES));
            book.summary = cursor.getString(cursor.getColumnIndexOrThrow(BookContract.BookEntry.COLUMN_NAME_SUMMARY));
            book.catid = cursor.getInt(cursor.getColumnIndexOrThrow(BookContract.BookEntry.COLUMN_NAME_CATID));
            book.cover = cursor.getString(cursor.getColumnIndexOrThrow(BookContract.BookEntry.COLUMN_NAME_COVER));

            bookList.add(book);
        }

        return bookList;
    }

    public void insertDefaultCategoryList(){
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<Category> categoryList = new ArrayList<Category>();

        categoryList.add( new Category("Education", String.valueOf(R.drawable.edu)));
        categoryList.add( new Category("Religion", String.valueOf(R.drawable.religion)));
        categoryList.add( new Category("History", String.valueOf(R.drawable.history)));
        categoryList.add( new Category("Biographies", String.valueOf(R.drawable.biography)));
        categoryList.add( new Category("Cooking", String.valueOf(R.drawable.cooking)));
        categoryList.add( new Category("Medical", String.valueOf(R.drawable.medical)));
        categoryList.add( new Category("Sports", String.valueOf(R.drawable.sports)));


        for (Category category: categoryList
        ) {
            ContentValues values = new ContentValues();
            values.put(BookContract.BookCategoryEntry.COLUMN_NAME_NAME,category.text);
            values.put(BookContract.BookCategoryEntry.COLUMN_NAME_IMAGE,category.image);

        }

    }

}
