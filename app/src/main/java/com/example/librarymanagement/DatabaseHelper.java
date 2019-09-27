package com.example.librarymanagement;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "library.db";
    public static final String CUSTOMER_TABLE = "Customer";

    //Customer
    //Columns
    private static final String ID = "ID";
    public static final String USERNAME = "USERNAME";
    private static final String NAME = "NAME";
    private static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";

    //Create table Customer
    public static final String CREATE_TABLE = "CREATE TABLE " + CUSTOMER_TABLE + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USERNAME + " TEXT, " +
            NAME + " TEXT, " +
            EMAIL + " TEXT, " +
            PASSWORD + " TEXT " + ")";

    public DatabaseHelper(Context context){
        super(context, DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE);
        onCreate(sqLiteDatabase);
    }

    //Insert Data
    public boolean insertData_Customer(String username, String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, username);
        contentValues.put(NAME, name);
        contentValues.put(EMAIL, email);
        contentValues.put(PASSWORD, password);

        long result = db.insert(CUSTOMER_TABLE,null,contentValues);
        return result != -1;
    }

    //View Data
    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + CUSTOMER_TABLE;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    //Delete Data
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CUSTOMER_TABLE,"id = ?",new String[] { id });
    }
    //Update Data
    public boolean updateData(String id,String username, String name, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, username);
        contentValues.put(NAME, name);
        contentValues.put(EMAIL, email);
        contentValues.put(PASSWORD, password);

        db.update(CUSTOMER_TABLE,contentValues, "id = ?",new String[]{ id });
        return true;
    }

}
