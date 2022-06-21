package com.example.scheduleapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.scheduleapp.R;
import com.example.scheduleapp.TuesdayActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView cardMonday, cardTuesday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.days_card);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cardMonday = (CardView) findViewById(R.id.card_monday);
        cardTuesday = (CardView) findViewById(R.id.card_tuesday);

        cardMonday.setOnClickListener(this);
        cardTuesday.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.language){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.card_monday:
                i = new Intent(this,MondayActivity.class);
                startActivity(i);
                break;

            case R.id.card_tuesday:
                i = new Intent(this, TuesdayActivity.class);
                startActivity(i);
                break;
        }
    }
}