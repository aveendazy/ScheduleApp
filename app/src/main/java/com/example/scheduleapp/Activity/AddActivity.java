package com.example.scheduleapp.Activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.scheduleapp.API.APIRequestData;
import com.example.scheduleapp.API.RetroServer;
import com.example.scheduleapp.Model.ResponseModel;
import com.example.scheduleapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {

    private EditText add_wktMulai, add_wktSelesai, add_jadwal;
    private Button btn_simpan;
    private String wktMulai, wktSelesai, jadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_add);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        add_wktMulai = findViewById(R.id.add_wktMulai);
        add_wktSelesai = findViewById(R.id.add_wktSelesai);
        add_jadwal = findViewById(R.id.add_jadwal);
        btn_simpan = findViewById(R.id.btn_simpan);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wktMulai = add_wktMulai.getText().toString();
                wktSelesai = add_wktSelesai.getText().toString();
                jadwal = add_jadwal.getText().toString();

                if(wktMulai.trim().equals("")){
                    add_wktMulai.setError("Waktu Mulai Harus Diisi");
                }
                else if(wktSelesai.trim().equals("")){
                    add_wktSelesai.setError("Waktu Selesai Harus Diisi");
                }
                else if(jadwal.trim().equals("")){
                    add_jadwal.setError("Jadwal Harus Diisi");
                }else {
                    createData();

                }
            }
        });
    }

    //toolbar
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

    private void createData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardCreateData(wktMulai, wktSelesai, jadwal);
//
//        Intent resultIntent = new Intent(this, MainActivity.class);
//        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

//                NotificationCompat.Builder builder = (NotificationCompat.Builder)
//                        new NotificationCompat.Builder(getApplicationContext())
//                        .setSmallIcon(R.drawable.ic_time)
//                        .setContentTitle("Your schedule has been added!")
//                        .setContentText("Click to see your schedule");
//                NotificationManager notificationManager = (NotificationManager)
//                        getSystemService(NOTIFICATION_SERVICE);
//                notificationManager.notify(0,builder.build());



                NotificationCompat.Builder builder = new NotificationCompat.Builder(AddActivity.this, "My Notification");
                builder.setContentTitle("Your schedule has been added!");
                builder.setContentText("Click to see your schedule");
                builder.setSmallIcon(R.drawable.logo_cs);
                builder.setAutoCancel(true);
//                builder.setContentIntent(resultPendingIntent);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AddActivity.this);
                managerCompat.notify(1, builder.build());

                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(AddActivity.this, "Gagal Menghubungi Server "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}