package com.example.librarymanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {

    DatabaseHelper db;
    EditText username, name, email, password, cpassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = new DatabaseHelper(this);

        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);

        btnRegister = findViewById(R.id.btnRegister);

        register();

    }
    //initializing toast messages
    public void toast(Context context, String string){
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
    }
    //intents
    public void login_activity(){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

    public void register(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().trim().length() == 0 ||
                        name.getText().toString().trim().length() == 0 ||
                        email.getText().toString().trim().length() == 0 ||
                        password.getText().toString().trim().length() == 0 || validate()){
                    toast(Signup.this,"All fields are required");
                } else if (db.insertData_Customer(username.getText().toString(), name.getText().toString(), email.getText().toString(), password.getText().toString())){
                    login_activity();
                    toast(Signup.this,"Registered Successfully Please Login");
                    username.setText("");
                    name.setText("");
                    email.setText("");
                    password.setText("");
                    cpassword.setText("");
                }else{
                    toast(Signup.this,"Error PLease try again");
                }
            }
        });
    }
    public boolean validate() {
        boolean valid = false;

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            valid = false;
            email.setError("Please enter valid email!");
        } else {
            valid = true;
            email.setError(null);
        }

        return valid;
    }
    public void Login(){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
}
