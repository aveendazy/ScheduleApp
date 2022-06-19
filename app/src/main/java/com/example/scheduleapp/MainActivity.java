package com.example.scheduleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView cardMonday, cardTuesday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.days_card);

        cardMonday = (CardView) findViewById(R.id.card_monday);
        cardTuesday = (CardView) findViewById(R.id.card_tuesday);

        cardMonday.setOnClickListener(this);
        cardTuesday.setOnClickListener(this);
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
                i = new Intent(this,TuesdayActivity.class);
                startActivity(i);
                break;
        }
    }
}