package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    EditText username, password;
    Button btnLogin;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.CUSTOMER_TABLE + " WHERE " + DatabaseHelper.USERNAME + "=? AND " + DatabaseHelper.PASSWORD + "=?", new String[]{user,pass});
                if(username.getText().toString().trim().length() == 0 ||
                        password.getText().toString().trim().length() == 0){
                    toast(Login.this,"All fields are required");
                }
                else if(cursor != null){
                    if(cursor.getCount()>0){
                        dashboard();
                        toast(Login.this,"Success");
                    }
                    else {
                        toast(Login.this,"Username or Password incorrect");
                    }
                }
            }
        });

    }

    public void toast(Context context, String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
    //intents
    public void dashboard(){
        Intent intent = new Intent(this,AdminDashboard.class);
        startActivity(intent);
    }

}


