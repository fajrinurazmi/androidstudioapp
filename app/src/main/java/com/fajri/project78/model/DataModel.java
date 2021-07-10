package com.fajri.project78.model;

public class DataModel {
    private int id_inspeksi;
    private String date, nama_bay, kondisi_pmt, kondisi_pegas, tekanan_gas, angka_tekanangas,
            indikator_sf6, counter_pmt, kondisi_kabel;

    public int getId_inspeksi() {
        return id_inspeksi;
    }

    public void setId_inspeksi(int id_inspeksi) {
        this.id_inspeksi = id_inspeksi;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNama_bay() {
        return nama_bay;
    }

    public void setNama_bay(String nama_bay) {
        this.nama_bay = nama_bay;
    }

    public String getKondisi_pmt() {
        return kondisi_pmt;
    }

    public void setKondisi_pmt(String kondisi_pmt) {
        this.kondisi_pmt = kondisi_pmt;
    }

    public String getKondisi_pegas() {
        return kondisi_pegas;
    }

    public void setKondisi_pegas(String kondisi_pegas) {
        this.kondisi_pegas = kondisi_pegas;
    }

    public String getTekanan_gas() {
        return tekanan_gas;
    }

    public void setTekanan_gas(String tekanan_gas) {
        this.tekanan_gas = tekanan_gas;
    }

    public String getAngka_tekanangas() {
        return angka_tekanangas;
    }

    public void setAngka_tekanangas(String angka_tekanangas) {
        this.angka_tekanangas = angka_tekanangas;
    }

    public String getIndikator_sf6() {
        return indikator_sf6;
    }

    public void setIndikator_sf6(String indikator_sf6) {
        this.indikator_sf6 = indikator_sf6;
    }

    public String getCounter_pmt() {
        return counter_pmt;
    }

    public void setCounter_pmt(String counter_pmt) {
        this.counter_pmt = counter_pmt;
    }

    public String getKondisi_kabel() {
        return kondisi_kabel;
    }

    public void setKondisi_kabel(String kondisi_kabel) {
        this.kondisi_kabel = kondisi_kabel;
    }
}
