package com.example.scheduleapp.Model;

public class DataModel {
    private int id;
    private String wktMulai, wktSelesai, jadwal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWktMulai() {
        return wktMulai;
    }

    public void setWktMulai(String wktMulai) {
        this.wktMulai = wktMulai;
    }

    public String getWktSelesai() {
        return wktSelesai;
    }

    public void setWktSelesai(String wktSelesai) {
        this.wktSelesai = wktSelesai;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }
}
