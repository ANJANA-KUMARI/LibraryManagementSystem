package com.example.librarymanagementsystem.db;

import android.provider.BaseColumns;

public class BookContract {

    private BookContract() {

    }

    public static class BookEntry implements BaseColumns{
        public static final String TABLE_NAME = "book";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_PAGES = "pages";
        public static final String COLUMN_NAME_SUMMARY = "summary";
        public static final String COLUMN_NAME_COVER ="cover";
        public static final String COLUMN_NAME_CATID ="catid";
    }

    public static class BookCategoryEntry implements BaseColumns{
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME ="name";
        public static final String COLUMN_NAME_IMAGE ="image";
    }


    public static final String SQL_CREATE_BOOKS =
            "CREATE TABLE " + BookEntry.TABLE_NAME + " (" +
                    BookEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    BookEntry.COLUMN_NAME_TITLE + " TEXT," +
                    BookEntry.COLUMN_NAME_AUTHOR + " TEXT," +
                    BookEntry.COLUMN_NAME_PAGES + " INTEGER," +
                    BookEntry.COLUMN_NAME_SUMMARY + " TEXT," +
                    BookEntry.COLUMN_NAME_CATID + " INTEGER," +
                    BookEntry.COLUMN_NAME_COVER + " TEXT)";

    public static  final String SQL_DROP_BOOKS =
            "DROP TABLE IF EXISTS " + BookEntry.TABLE_NAME;


    public static final String SQL_CREATE_CATEGORY =
            "CREATE TABLE " + BookCategoryEntry.TABLE_NAME + " (" +
                    BookCategoryEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    BookCategoryEntry.COLUMN_NAME_NAME + " TEXT," +
                    BookCategoryEntry.COLUMN_NAME_IMAGE + " TEXT)";
}
