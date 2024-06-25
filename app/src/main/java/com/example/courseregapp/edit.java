package com.example.courseregapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class edit extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        ed1 = findViewById(R.id.title);
        ed2 = findViewById(R.id.author);
        ed3 = findViewById(R.id.pages);
        ed4 = findViewById(R.id.id);

        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);
        b3 = findViewById(R.id.bt3);

        Intent i = getIntent();

        String t1 = i.getStringExtra("id").toString();
        String t2 = i.getStringExtra("title").toString();
        String t3 = i.getStringExtra("author").toString();
        String t4 = i.getStringExtra("pages").toString();

        ed4.setText(t1);
        ed1.setText(t2);
        ed2.setText(t3);
        ed3.setText(t4);


        b2.setOnClickListener(v -> Delete());


        b3.setOnClickListener(v -> {

            Intent i1 = new Intent(getApplicationContext(),view.class);
            startActivity(i1);

        });

        b1.setOnClickListener(v -> Edit());

    }

    public void Delete()
    {
        try
        {
            String id = ed4.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("LibraryDb.db", Context.MODE_PRIVATE,null);


            String sql = "delete from records where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Record Deleted",Toast.LENGTH_LONG).show();


            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed1.requestFocus();

        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Record Fail",Toast.LENGTH_LONG).show();
        }
    }
    public void Edit()
    {
        try
        {
            String title = ed1.getText().toString();
            String author = ed2.getText().toString();
            String pages = ed3.getText().toString();
            String id = ed4.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("LibraryDb.db",Context.MODE_PRIVATE,null);

            String sql = "update records set title = ?,author=?,pages=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,title);
            statement.bindString(2,author);
            statement.bindString(3,pages);
            statement.bindString(4,id);
            statement.execute();
            Toast.makeText(this,"Record Updated",Toast.LENGTH_LONG).show();


            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed1.requestFocus();


        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Record Fail",Toast.LENGTH_LONG).show();
        }

    }

}