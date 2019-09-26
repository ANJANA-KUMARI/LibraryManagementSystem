package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCustomer extends AppCompatActivity {

    DatabaseHelper db;
    EditText id,username, name, email, password, cpassword;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);

        db = new DatabaseHelper(this);

        id = findViewById(R.id.cus_id);
        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);

        btnUpdate = findViewById(R.id.btnUpdate);

        updateData();
    }
    //initializing toast messages
    public void toast(Context context, String string){
        Toast.makeText(this,string, Toast.LENGTH_SHORT).show();
    }
    public void updateData(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = db.updateData(id.getText().toString(),username.getText().toString(), name.getText().toString(), email.getText().toString(), password.getText().toString());
                if(
                        id.getText().toString().trim().length() == 0||
                                username.getText().toString().trim().length() == 0 ||
                                name.getText().toString().trim().length() == 0 ||
                                email.getText().toString().trim().length() == 0 ||
                                password.getText().toString().trim().length() == 0){
                    toast(UpdateCustomer.this, "Complete all fields");
                }
                else if(isUpdate == true){
                    view_Cus();
                    toast(UpdateCustomer.this,"Customer Updated Successfully");
                    id.setText("");
                    username.setText("");
                    name.setText("");
                    email.setText("");
                    password.setText("");
                }
                else {
                    toast(UpdateCustomer.this,"Error");
                }
            }
        });
    }
    public void view_Cus(){
        Intent intent = new Intent(this,ViewCustomer.class);
        startActivity(intent);
    }
}
