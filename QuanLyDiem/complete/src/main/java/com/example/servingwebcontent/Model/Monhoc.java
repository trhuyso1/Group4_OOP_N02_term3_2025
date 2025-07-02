package com.example.servingwebcontent.Model;

public class Monhoc {
    private String maMon;
    private String tenMon;
    private int soTinChi;
    private String sotietLT;
    private String sotietTH;
    private String hocky;

    public Monhoc() {

    }
    public Monhoc(String maMon, String tenMon, int soTinChi, String sotietLT, String sotietTH, String hocky) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.soTinChi = soTinChi;
        this.sotietLT = sotietLT;
        this.sotietTH = sotietTH;
        this.hocky = hocky;
    }



    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    

    public String getSotietLT() {
        return sotietLT;
    }



    public void setSotietLT(String sotietLT) {
        this.sotietLT = sotietLT;
    }



    public String getSotietTH() {
        return sotietTH;
    }



    public void setSotietTH(String sotietTH) {
        this.sotietTH = sotietTH;
    }



    public String getHocky() {
        return hocky;
    }



    public void setHocky(String hocky) {
        this.hocky = hocky;
    }



    @Override
    public String toString() {
        return String.format("%-8s | %-25s | %-8d | %-10s | %-10s | %-6s",
            maMon, tenMon, soTinChi, sotietLT, sotietTH, hocky);
    }
    
}
