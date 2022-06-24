package com.example.scheduleapp.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scheduleapp.API.APIRequestData;
import com.example.scheduleapp.API.RetroServer;
import com.example.scheduleapp.Activity.MondayActivity;
import com.example.scheduleapp.Activity.UpdateActivity;
import com.example.scheduleapp.Model.DataModel;
import com.example.scheduleapp.Model.ResponseModel;
import com.example.scheduleapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// adapter = menghubungkan data dengan cardview
public class AdapterData extends  RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listData;
    private List<DataModel> listJadwal;
    private int idJadwal;

    public AdapterData(Context ctx, List<DataModel> listData) {
        this.ctx = ctx;
        this.listData = listData;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterData.HolderData holder, int position) {
        // ngeset penempatan data pada card
        DataModel dm = listData.get(position);

        holder.tvId.setText(String.valueOf(dm.getId()));
        holder.tvWktMulai.setText(dm.getWktMulai());
        holder.tvWktSelesai.setText(dm.getWktSelesai());
        holder.tvJadwal.setText(dm.getJadwal());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    // card view kita ada apa aja
    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvId, tvWktMulai, tvWktSelesai, tvJadwal;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvWktMulai = itemView.findViewById(R.id.tv_wktMulai);
            tvWktSelesai = itemView.findViewById(R.id.tv_wktSelesai);
            tvJadwal = itemView.findViewById(R.id.tv_Jadwal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setTitle("Perhatian");
                    dialogPesan.setMessage("Pilih operasi yang akan dilakukan!");
                    dialogPesan.setCancelable(true);

                    idJadwal = Integer.parseInt(tvId.getText().toString());

                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteData();
                            dialogInterface.dismiss();
                            // kasih jeda biar wkt dihapus datanya lgsg bisa kehapus tanpa refresh
                            Handler hand = new Handler();
                            hand.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ((MondayActivity) ctx).getData();
                                }
                            }, 500);
                        }
                    });

                    dialogPesan.setNegativeButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getData2();
                            dialogInterface.dismiss();
                        }
                    });

                    dialogPesan.show();
                }
            });
        }

        private void deleteData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> hapusData = ardData.ardDeleteData(idJadwal);

            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void getData2(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> ambilData = ardData.ardGetData2(idJadwal);

            ambilData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    listJadwal = response.body().getData();

                    int varIdJadwal = listJadwal.get(0).getId();
                    String varWktMulai = listJadwal.get(0).getWktMulai();
                    String varWktSelesai = listJadwal.get(0).getWktSelesai();
                    String varJadwal = listJadwal.get(0).getJadwal();

                    // mengirim data sesuai id ke layout form_edit
                    Intent kirim = new Intent(ctx, UpdateActivity.class);

                    kirim.putExtra("xId", varIdJadwal);
                    kirim.putExtra("xWktMulai", varWktMulai);
                    kirim.putExtra("xWktSelesai", varWktSelesai);
                    kirim.putExtra("xJadwal", varJadwal);

                    ctx.startActivity(kirim);
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
