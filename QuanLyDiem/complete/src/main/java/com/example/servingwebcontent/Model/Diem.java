package com.example.servingwebcontent.Model;

public class Diem {
    private Student student;
    private Monhoc monhoc;
    private double diemMon;   // Điểm hệ 10
    private double diemHe4;   // Điểm hệ 4 (tự động tính)
    private String diemChu;   // Điểm chữ (A, B, C, D, F)
    private String danhGia;   // Đánh giá (Giỏi, Khá, ...)

    public Diem() {}

    public Diem(Student student, Monhoc monhoc, double diemMon) {
        this.student = student;
        this.monhoc = monhoc;
        setDiemMon(diemMon);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Monhoc getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(Monhoc monhoc) {
        this.monhoc = monhoc;
    }

    public double getDiemMon() {
        return diemMon;
    }

    public void setDiemMon(double diemMon) {
        this.diemMon = diemMon;
        this.diemHe4 = tinhDiemHe4(diemMon);
        this.diemChu = tinhDiemChu(diemMon);
        this.danhGia = tinhDanhGia(diemMon);
    }

    public double getDiemHe4() {
        return diemHe4;
    }

    public String getDiemChu() {
        return diemChu;
    }

    public String getDanhGia() {
        return danhGia;
    }

    // Chuyển điểm hệ 10 sang hệ 4
    private double tinhDiemHe4(double diem) {
        if (diem >= 8.5) return 4.0;
        else if (diem >= 7.0) return 3.0;
        else if (diem >= 5.5) return 2.0;
        else if (diem >= 4.0) return 1.0;
        else return 0.0;
    }

    // Hàm chuyển điểm số sang điểm chữ
    private String tinhDiemChu(double diem) {
        if (diem >= 9.0) return "A+";
        else if (diem >= 8.5) return "A";
        else if (diem >= 7.0) return "B";
        else if (diem >= 5.5) return "C";
        else if (diem >= 4.0) return "D";
        else return "F";
    }

    // Đánh giá kết quả
    private String tinhDanhGia(double diem) {
        if (diem >= 8.5) return "Giỏi";
        else if (diem >= 7.0) return "Khá";
        else if (diem >= 5.5) return "Trung bình";
        else if (diem >= 4.0) return "Yếu";
        else return "Không đạt";
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-20s | %-25s | %6.2f | %4.1f | %-3s | %-12s",
            student.getMsv(),
            student.getFullName(),
            monhoc.getTenMon(),
            diemMon,
            diemHe4,
            diemChu,
            danhGia
        );
    }
}
