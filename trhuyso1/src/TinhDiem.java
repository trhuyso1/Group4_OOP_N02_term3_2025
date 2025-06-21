// src/TinhDiem.java

public class TinhDiem {
    public double tinhDiemTrungBinh(SinhVien sv) {
        List<Double> diem = sv.getDiemMonHoc();
        double tong = 0;
        for (double d : diem) {
            tong += d;
        }
        return diem.size() == 0 ? 0 : Math.round(tong / diem.size() * 100.0) / 100.0;
    }
}
