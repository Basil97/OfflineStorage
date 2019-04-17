package com.basil.offlinestorage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DataBaseConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connection = new DataBaseConnection(this);
    }

    public void onSave(View view) {
        EditText name = findViewById(R.id.name);
        EditText age = findViewById(R.id.age);
        if (name.getText() != null && age.getText() != null)
            connection.addStudent(name.getText().toString(), Integer.parseInt(age.getText().toString()));
    }

    public void onLoad(View view) {
        ListView list = findViewById(R.id.list_view);
        List<String> data = connection.getAll();
        list.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, data));
    }
}
