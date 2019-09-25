package com.example.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btn_add;

    private ListView listView;
    private ArrayList<PhonebookModel> phonebookModelArrayList;
    private CustomAdapterPhonebook customAdapterPhonebook;
    private DatabaseHelperPhonebook databaseHelperPhonebook;

    public  void addPhonebookActivity(){
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPhonebookActivity();
        listView = (ListView) findViewById(R.id.teachers_lv);

        databaseHelperPhonebook = new DatabaseHelperPhonebook(this);

        phonebookModelArrayList = databaseHelperPhonebook.getAllPhonebook();

        customAdapterPhonebook = new CustomAdapterPhonebook(this,phonebookModelArrayList);
        listView.setAdapter(customAdapterPhonebook);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("students",phonebookModelArrayList.get(position));
                startActivity(intent);

            }
        });
    }
}
