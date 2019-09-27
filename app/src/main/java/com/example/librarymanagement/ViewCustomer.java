package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewCustomer extends AppCompatActivity {


    DatabaseHelper db;
    EditText cus_id;
    Button btnDelete;

    ListView CustomerList;

    ArrayList<String> list;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);

        db = new DatabaseHelper(this);

        list = new ArrayList<>();
        CustomerList = findViewById(R.id.view);

        btnDelete = findViewById(R.id.btnDelete);

        cus_id = findViewById(R.id.cus_id);

        viewData();
        deleteData();

        CustomerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = CustomerList.getItemAtPosition(i).toString();
                Toast.makeText(ViewCustomer.this,""+text,Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void view_Cus(){
        Intent intent = new Intent(this,ViewCustomer.class);
        startActivity(intent);
    }
    private void viewData() {
        Cursor cursor = db.viewData();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                list.add("Customer Id: " + cursor.getString(0));
                list.add("Customer Username: " + cursor.getString(1));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            CustomerList.setAdapter(adapter);
        }
    }
    public void deleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        Integer deleteRows = db.deleteData(cus_id.getText().toString());
                        if(deleteRows > 0){
                            view_Cus();
                            Toast.makeText(ViewCustomer.this, "Customer Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(ViewCustomer.this, "Customer Deleted Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}
