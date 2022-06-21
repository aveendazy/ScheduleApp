package com.example.scheduleapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scheduleapp.API.APIRequestData;
import com.example.scheduleapp.API.RetroServer;
import com.example.scheduleapp.Model.ResponseModel;
import com.example.scheduleapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {

    private int yId;
    private String yWktMulai, yWktSelesai, yJadwal;
    private EditText add_wktMulai, add_wktSelesai, add_jadwal;
    private Button btn_update;
    private String zWktMulai, zWktSelesai, zJadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_edit);

        // menerima kiriman data dari AdapterData
        Intent terima = getIntent();

        yId = terima.getIntExtra("xId", -1);
        yWktMulai = terima.getStringExtra("xWktMulai");
        yWktSelesai = terima.getStringExtra("xWktSelesai");
        yJadwal = terima.getStringExtra("xJadwal");

        // meletakkan data di layout form_edit
        add_wktMulai = findViewById(R.id.add_wktMulai);
        add_wktSelesai = findViewById(R.id.add_wktSelesai);
        add_jadwal = findViewById(R.id.add_jadwal);
        btn_update = findViewById(R.id.btn_update);

        add_wktMulai.setText(yWktMulai);
        add_wktSelesai.setText(yWktSelesai);
        add_jadwal.setText(yJadwal);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                zWktMulai = add_wktMulai.getText().toString();
                zWktSelesai = add_wktSelesai.getText().toString();
                zJadwal = add_jadwal.getText().toString();

                updateData();
            }
        });
    }

    private void updateData() {
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> updateData = ardData.ardUpdateData(yId, zWktMulai, zWktSelesai, zJadwal);

        updateData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Gagal Menghubungi Server "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}



