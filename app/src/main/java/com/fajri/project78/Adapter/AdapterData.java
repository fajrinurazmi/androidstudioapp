package com.fajri.project78.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.fajri.project78.ApiInspeksi.APIRequestData;
import com.fajri.project78.ApiInspeksi.RetroServer;
import com.fajri.project78.InspeksiActivity;
import com.fajri.project78.R;
import com.fajri.project78.UbahActivity;
import com.fajri.project78.model.DataModel;
import com.fajri.project78.model.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listData;
    private List<DataModel> listInspeksi;
    private int idInspeksix;

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
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listData.get(position);

        holder.tvidtv.setText(String.valueOf(dm.getId_inspeksi()));
        holder.tvdate.setText(dm.getDate());
        holder.tvnamaBay.setText(dm.getNama_bay());
        holder.tvkondisiPmt.setText(dm.getKondisi_pmt());
        holder.tvkondisiPegas.setText(dm.getKondisi_pegas());
        holder.tvtekananGas.setText(dm.getTekanan_gas());
        holder.tvangkaTekananGas.setText(dm.getAngka_tekanangas());
        holder.tvindikatorSf6.setText(dm.getIndikator_sf6());
        holder.tvcounterPmt.setText(dm.getCounter_pmt());
        holder.tvkondisiKabel.setText(dm.getKondisi_kabel());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {

        TextView tvidtv, tvdate, tvnamaBay, tvkondisiPmt, tvkondisiPegas,
                tvtekananGas, tvangkaTekananGas, tvindikatorSf6, tvcounterPmt, tvkondisiKabel;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvidtv = itemView.findViewById(R.id.tvidtv);
            tvdate = itemView.findViewById(R.id.tv_date);
            tvnamaBay = itemView.findViewById(R.id.tv_namaBay);
            tvkondisiPmt = itemView.findViewById(R.id.tv_kondisiPmt);
            tvkondisiPegas = itemView.findViewById(R.id.tv_kondisiPegas);
            tvtekananGas = itemView.findViewById(R.id.tv_tekananGas);
            tvangkaTekananGas = itemView.findViewById(R.id.tv_angkaTekananGas);
            tvindikatorSf6 = itemView.findViewById(R.id.tv_indikatorSf6);
            tvcounterPmt = itemView.findViewById(R.id.tv_counterPmt);
            tvkondisiKabel = itemView.findViewById(R.id.tv_kondisiKabel);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Pilih Operasi yang Akan Dilakukan");
                    dialogPesan.setTitle("Perhatian");
                    dialogPesan.setIcon(R.drawable.ic_baseline_delete_24);
                    dialogPesan.setCancelable(true);

                    idInspeksix = Integer.parseInt(tvidtv.getText().toString());

                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteData();
                            dialogInterface.dismiss();
                            ((InspeksiActivity) ctx).retrieveData();
                        }
                    });

                    dialogPesan.setNegativeButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getData();
                            dialogInterface.dismiss();
                        }
                    });

                    dialogPesan.show();

                    return false;
                }
            });

        }

        private void deleteData() {
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> hapusData = ardData.ardDeleteData(idInspeksix);

            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "Kode : " + kode + " | Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server" + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }

        private void getData() {
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> ambilData = ardData.ardGetData(idInspeksix);

            ambilData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    listInspeksi = response.body().getData();

                    int varIdInspeksi = listInspeksi.get(0).getId_inspeksi();
                    String varDate = listInspeksi.get(0).getDate();
                    String varNamabay = listInspeksi.get(0).getNama_bay();
                    String varKondisipmt = listInspeksi.get(0).getKondisi_pmt();
                    String varKondisipegas = listInspeksi.get(0).getKondisi_pegas();
                    String varTekanangas = listInspeksi.get(0).getTekanan_gas();
                    String varAngkatekanangas = listInspeksi.get(0).getAngka_tekanangas();
                    String varIndikatorsf6 = listInspeksi.get(0).getIndikator_sf6();
                    String varCounterpmt = listInspeksi.get(0).getCounter_pmt();
                    String varKondisikabel = listInspeksi.get(0).getKondisi_kabel();

                    //Toast.makeText(ctx, "Kode : " + kode + " | Pesan : " + pesan + " | Data : " + varIdInspeksi + " | " + varDate + " | " + varNamabay + " | " + varKondisipmt, Toast.LENGTH_SHORT).show();
                    Intent kirim = new Intent(ctx, UbahActivity.class);
                    kirim.putExtra("xId", varIdInspeksi);
                    kirim.putExtra("xDate", varDate);
                    kirim.putExtra("xNamabay", varNamabay);
                    kirim.putExtra("xKondisipmt", varKondisipmt);
                    kirim.putExtra("xKondisipegas", varKondisipegas);
                    kirim.putExtra("xTekanangas", varTekanangas);
                    kirim.putExtra("xAngkatekanangas", varAngkatekanangas);
                    kirim.putExtra("xIndikatorsf6", varIndikatorsf6);
                    kirim.putExtra("xCounterpmt", varCounterpmt);
                    kirim.putExtra("xKondisikabel", varKondisikabel);
                    ctx.startActivity(kirim);
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
