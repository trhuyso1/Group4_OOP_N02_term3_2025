// src/LocHocLucYeu.java

import java.util.*;

public class LocHocLucYeu {
    TinhDiem td = new TinhDiem();

    public void locHocLucYeu(List<SinhVien> danhSach) {
        System.out.println("== Danh sách sinh viên học lực yếu (ĐTB < 5.0) ==");
        for (SinhVien sv : danhSach) {
            double dtb = td.tinhDiemTrungBinh(sv);
            if (dtb < 5.0) {
                System.out.println(sv.getMssv() + " - " + sv.getHoTen() + " | ĐTB: " + dtb);
            }
        }
    }
}

