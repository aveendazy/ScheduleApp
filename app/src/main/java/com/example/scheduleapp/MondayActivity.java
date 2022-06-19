package com.example.scheduleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MondayActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monday_schedule);

        button = (Button) findViewById(R.id.add_form);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFormAdd();
            }
        });
    }
    public void openFormAdd() {
        Intent intent = new Intent(this,AddActivity.class);
        startActivity(intent);
    }
}
