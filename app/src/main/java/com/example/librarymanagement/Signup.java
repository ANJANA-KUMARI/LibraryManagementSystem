package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    DatabaseHelper db;
    EditText username, name, email, password, cpassword;
    Button btnRegister,btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);

        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this,Login.class);
                startActivity(intent);
            }
        });

        register();

    }
    //initializing toast messages
    public void toast(Context context, String string){
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
    }
    //intents
    public void login(){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

    public void register(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()==false){
                    //toast(Signup.this,"Please Fill in the correct format");
                }else if (db.insertData_Customer(username.getText().toString(), name.getText().toString(), email.getText().toString(), password.getText().toString())){
                    login();
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

        if (username.getText().toString().trim().length() == 0 ||
                name.getText().toString().trim().length() == 0 ||
                email.getText().toString().trim().length() == 0 ||
                password.getText().toString().trim().length() == 0){
            toast(Signup.this,"All fields are required");
        }
        else {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                valid = false;
                email.setError("Please enter valid email!");
                toast(Signup.this, "Please enter valid email!");
            } else {
                valid = true;
                email.setError(null);
            }
        }
        if (password.length() > 8 || cpassword.length() > 8){
            valid = true;
            password.setError(null);
            cpassword.setError(null);
        }else{
            valid = false;
            toast(Signup.this,"Password must be 8 characters long");
        }

        return valid;
    }
}
