// src/HienThiDiem.java

import java.util.*;

public class HienThiDiem {
    public void hienThiDiemTheoLop(String maLop, List<SinhVien> danhSach) {
        System.out.println("== Danh sách điểm lớp " + maLop + " ==");
        for (SinhVien sv : danhSach) {
            if (sv.getMaLop().equalsIgnoreCase(maLop)) {
                System.out.println(sv);
            }
        }
    }
}
