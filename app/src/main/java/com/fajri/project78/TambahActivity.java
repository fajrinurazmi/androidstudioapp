package com.fajri.project78;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fajri.project78.ApiInspeksi.APIRequestData;
import com.fajri.project78.ApiInspeksi.RetroServer;
import com.fajri.project78.model.ResponseModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tvDateResult;
    private Button btDatePicker,btnSimpan;
    private Spinner spBay, spKondisipmt, spKondisipegas, spTekanangas, spIndikatorsf6, spKondisikabel;
    private EditText etangkatekanangas, etCounterpmt;
    private String  date, namaBay, kondisiPmt, kondisiPegas, tekananGas, angkaTekananGas, indikatorSf6,  counterPmt, kondisiKabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        btDatePicker = (Button) findViewById(R.id.bt_datepicker);
        tvDateResult = findViewById(R.id.tv_dateresult);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                showDateDialog();
                                            }
                                        });

        spBay = findViewById(R.id.sp_bay);
        spKondisipmt = findViewById(R.id.sp_kondisipmt);
        spKondisipegas = findViewById(R.id.sp_kondisipegas);
        spTekanangas = findViewById(R.id.sp_tekanangas);
        etangkatekanangas = findViewById(R.id.et_angkatekanangas);
        spIndikatorsf6 = findViewById(R.id.sp_indikatorsf6);
        etCounterpmt = findViewById(R.id.et_counterpmt);
        spKondisikabel = findViewById(R.id.sp_kondisikabel);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date = tvDateResult.getText().toString();
                namaBay = spBay.getSelectedItem().toString();
                kondisiPmt = spKondisipmt.getSelectedItem().toString();
                kondisiPegas = spKondisipegas.getSelectedItem().toString();
                tekananGas = spTekanangas.getSelectedItem().toString();
                angkaTekananGas = etangkatekanangas.getText().toString();
                indikatorSf6 = spIndikatorsf6.getSelectedItem().toString();
                counterPmt = etCounterpmt.getText().toString();
                kondisiKabel = spKondisikabel.getSelectedItem().toString();

                if(angkaTekananGas.trim().equals("")){
                    etangkatekanangas.setError("Tekanan Gas Harus Diisi");
                }
                else if(counterPmt.trim().equals("")){
                    etCounterpmt.setError("Counter PMT Harus Diisi");
                }
                else {
                    createData();
                }
            }
        });
    }

    private void showDateDialog(){

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                tvDateResult.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void createData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardCreateData(date, namaBay, kondisiPmt, kondisiPegas, tekananGas, angkaTekananGas, indikatorSf6,  counterPmt, kondisiKabel);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode :"+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}