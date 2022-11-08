package com.mxn.soul.flowingdrawer.db;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mxn.soul.flowingdrawer.R;

public class DatabaseAdder extends AppCompatActivity {

        DatabaseHelper myDB;
        EditText name,surname,marks,id;
        Button add,view,update,delete;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_adder);
        myDB = new DatabaseHelper(this);
        name = (EditText) findViewById(R.id.nameedit);
        id = (EditText) findViewById(R.id.idedit);
//        surname = (EditText) findViewById(R.id.snameedit);
//        marks = (EditText) findViewById(R.id.marksedit);
        add = (Button) findViewById(R.id.add);
        view = (Button) findViewById(R.id.viewall);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);


        addDetails();
        viewDetails();

        update.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Boolean isUpdated = myDB.updateData(id.getText().toString(),name.getText().toString());
        if (isUpdated != true)
        Toast.makeText(DatabaseAdder.this,"Data Updated Successfully",Toast.LENGTH_LONG).show();
        else
        Toast.makeText(DatabaseAdder.this,"Data Updation failed",Toast.LENGTH_LONG).show();
        }
        });

        delete.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Integer isDeleted = myDB.deleteData(id.getText().toString());
        if (isDeleted > 0)
        Toast.makeText(DatabaseAdder.this,"Data Deleted Successfully",Toast.LENGTH_LONG).show();
        else
        Toast.makeText(DatabaseAdder.this,"Data Deletion failed",Toast.LENGTH_LONG).show();
        }
        });
        }

public void addDetails()
        {
        add.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Boolean isInserted = myDB.InsertData(id.getText().toString(),name.getText().toString());
        if (isInserted != true)
        Toast.makeText(DatabaseAdder.this,"Data inserted Successfully",Toast.LENGTH_LONG).show();
        else
        Toast.makeText(DatabaseAdder.this,"Data insertion failed",Toast.LENGTH_LONG).show();
        }
        });


        }

public void viewDetails()
        {
        view.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Cursor cursor = myDB.getAllData();
        if (cursor.getColumnCount() == 0) {
        Toast.makeText(DatabaseAdder.this, "Getting Data failed", Toast.LENGTH_LONG).show();
        return;
        }
        else {
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()){
        stringBuffer.append("ID : "+cursor.getString(0)+"\n");
        stringBuffer.append("Name : "+cursor.getString(1)+"\n");
        }

        showDetails("Data",stringBuffer.toString());
        }


        }
        });
        }

public void showDetails(String title, String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
        }


        }
