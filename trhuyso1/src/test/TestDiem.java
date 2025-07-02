package test;

import CRUD.ListDiem;
import CRUD.ListStudent;
import CRUD.ListMonHoc;
import Model.Diem;
import Model.Student;
import Model.Monhoc;
import java.util.*;

public class TestDiem {
    private static ListDiem diemList = new ListDiem();
    private static boolean daThemMau = false;

    public void dsMauDiem(ListStudent studentList, ListMonHoc monhocList) {
        if (daThemMau) return;
        diemList.setHienThongBao(false);

        // Lấy SV và MH từ list mẫu
        Student s1 = studentList.getList().get(0);
        Student s2 = studentList.getList().get(1);
        Monhoc m1 = monhocList.getList().get(0);
        Monhoc m2 = monhocList.getList().get(1);

        Diem d1 = new Diem(s1, m1, 8.7);
        Diem d2 = new Diem(s2, m2, 6.5);
        diemList.them(d1);
        diemList.them(d2);

        daThemMau = true;
    }

    public ListDiem getDiemList() {
        return diemList;
    }

    public void inDiem() {
        System.out.println("__Danh sách điểm__\n");
        if (diemList.getList().isEmpty()) {
            System.out.println("Danh sách điểm rỗng.");
            return;
        }
        inTieuDe();
        for (Diem d : diemList.getList()) {
            System.out.println(d);
        }
    }

    private void inTieuDe() {
        System.out.printf("%-8s | %-20s | %-25s | %-6s | %-4s | %-3s | %-12s\n",
            "Mã SV", "Tên SV", "Tên môn", "Điểm", "Hệ 4", "Chữ", "Đánh giá");
        System.out.println("-----------------------------------------------------------------------------------------------");
    }

    // Có thể bổ sung các hàm add, update, delete, search, thongKe tương tự TestStudent
}