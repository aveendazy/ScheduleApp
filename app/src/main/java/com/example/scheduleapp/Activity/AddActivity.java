package com.example.scheduleapp.Activity;

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

public class AddActivity extends AppCompatActivity {

    private EditText add_wktMulai, add_wktSelesai, add_jadwal;
    private Button btn_simpan;
    private String wktMulai, wktSelesai, jadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_add);

        add_wktMulai = findViewById(R.id.add_wktMulai);
        add_wktSelesai = findViewById(R.id.add_wktSelesai);
        add_jadwal = findViewById(R.id.add_jadwal);
        btn_simpan = findViewById(R.id.btn_simpan);

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

    private void createData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardCreateData(wktMulai, wktSelesai, jadwal);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(AddActivity.this, "Gagal Menghubungi Server "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}