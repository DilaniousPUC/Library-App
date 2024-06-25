package com.example.courseregapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class view extends AppCompatActivity {


    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        SQLiteDatabase db = openOrCreateDatabase("LibraryDb.db",Context.MODE_PRIVATE,null);

        lst1 = findViewById(R.id.lst1);
        final Cursor c = db.rawQuery("select * from records",null);
        int id = c.getColumnIndex("id");
        int title = c.getColumnIndex("title");
        int author = c.getColumnIndex("author");
        int pages = c.getColumnIndex("pages");
        titles.clear();


        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles);
        lst1.setAdapter(arrayAdapter);

        final  ArrayList<student> stud = new ArrayList<student>();


        if(c.moveToFirst())
        {
            do{
                student stu = new student();
                stu.id = c.getString(id);
                stu.title = c.getString(title);
                stu.author = c.getString(author);
                stu.pages = c.getString(pages);
                stud.add(stu);

                titles.add(c.getString(id) + " \t " + c.getString(title) + " \t "  + c.getString(author) + " \t "  + c.getString(pages) );

            } while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();



        }

        lst1.setOnItemClickListener((parent, view, position, id1) -> {
            String aa = titles.get(position).toString();
            student stu = stud.get(position);
            Intent i = new Intent(getApplicationContext(),edit.class);
            i.putExtra("id",stu.id);
            i.putExtra("title",stu.title);
            i.putExtra("author",stu.author);
            i.putExtra("pages",stu.pages);
            startActivity(i);

        });

    }
}