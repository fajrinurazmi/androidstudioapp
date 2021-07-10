package com.fajri.project78;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
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

public class UbahActivity extends AppCompatActivity {
    private int xId;
    private String xDate, xNamabay, xKondisipmt, xKondisipegas, xTekanangas, xAngkatekanangas, xIndikatorsf6, xCounterpmt, xKondisikabel;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tvDateResult;
    private Button btDatePicker,btnUbah;
    private Spinner spBay, spKondisipmt, spKondisipegas, spTekanangas, spIndikatorsf6, spKondisikabel;
    private EditText etangkatekanangas, etCounterpmt;
    private String yDate, yNamabay, yKondisipmt, yKondisipegas, yTekanangas, yAngkatekanangas, yIndikatorsf6, yCounterpmt, yKondisikabel;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent terima = getIntent();
        xId = terima.getIntExtra("xId", -1);
        xDate = terima.getStringExtra("xDate");
        xNamabay = terima.getStringExtra("xNamabay");
        xKondisipmt = terima.getStringExtra("xKondisipmt");
        xKondisipegas = terima.getStringExtra("xKondisipegas");
        xTekanangas = terima.getStringExtra("xTekanangas");
        xAngkatekanangas = terima.getStringExtra("xAngkatekanangas");
        xIndikatorsf6 = terima.getStringExtra("xIndikatorsf6");
        xCounterpmt = terima.getStringExtra("xCounterpmt");
        xKondisikabel = terima.getStringExtra("xKondisikabel");

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
        btnUbah = findViewById(R.id.btn_ubah);

        tvDateResult.setText(xDate);
        etangkatekanangas.setText(xAngkatekanangas);
        etCounterpmt.setText(xCounterpmt);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yDate = tvDateResult.getText().toString();
                yNamabay = spBay.getSelectedItem().toString();
                yKondisipmt = spKondisipmt.getSelectedItem().toString();
                yKondisipegas = spKondisipegas.getSelectedItem().toString();
                yTekanangas = spTekanangas.getSelectedItem().toString();
                yAngkatekanangas = etangkatekanangas.getText().toString();
                yIndikatorsf6 = spIndikatorsf6.getSelectedItem().toString();
                yCounterpmt = etCounterpmt.getText().toString();
                yKondisikabel = spKondisikabel.getSelectedItem().toString();

                updateData();
            }
        });
    }

    private void updateData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> ubahData = ardData.ardUpdateData(xId, yDate, yNamabay, yKondisipmt, yKondisipegas, yTekanangas, yAngkatekanangas, yIndikatorsf6, yCounterpmt, yKondisikabel);

        ubahData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this, "Kode :"+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
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
}