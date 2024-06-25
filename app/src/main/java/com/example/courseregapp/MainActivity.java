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

public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = findViewById(R.id.title);
        ed2 = findViewById(R.id.author);
        ed3 = findViewById(R.id.pages);

        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);

        b2.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(),view.class);
            startActivity(i);
        });
        b1.setOnClickListener(v -> insert());
    }

    public void insert()
    {
        try
        {
            String title = ed1.getText().toString();
            String author = ed2.getText().toString();
            String pages = ed3.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("LibraryDb.db", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT,title VARCHAR,author VARCHAR,pages VARCHAR)");

            String sql = "insert into records(title,author,pages)values(?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,title);
            statement.bindString(2,author);
            statement.bindString(3,pages);
            statement.execute();
            Toast.makeText(this,"Record addded",Toast.LENGTH_LONG).show();

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