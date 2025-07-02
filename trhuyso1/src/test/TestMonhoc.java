package test;

import CRUD.ListMonHoc;
import Model.Monhoc;
import java.util.*;

public class TestMonhoc {
    private static ListMonHoc monhocList = new ListMonHoc();
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean daThemMau = false;

    private static final Object[][] data = {
        {"MH01", "Lập trình Java", 3, "30", "15", "1"},
        {"MH02", "Cơ sở dữ liệu", 3, "30", "15", "2"}
    };

    public void dsMauMonhoc() {
        if (daThemMau) return;
        monhocList.setHienThongBao(false);
        for (Object[] row : data) {
            Monhoc m = new Monhoc(
                (String) row[0],
                (String) row[1],
                (int) row[2],
                (String) row[3],
                (String) row[4],
                (String) row[5]
            );
            monhocList.them(m);
        }
        daThemMau = true;
    }

    public ListMonHoc getMonhocList() {
        return monhocList;
    }

    public void inMonhoc() {
        System.out.println("__Danh sách môn học__\n");
        if (monhocList.getList().isEmpty()) {
            System.out.println("Danh sách môn học rỗng.");
            return;
        }
        inTieuDe();
        for (Monhoc m : monhocList.getList()) {
            System.out.println(m);
        }
    }

    private void inTieuDe() {
        System.out.printf("%-8s | %-25s | %-8s | %-10s | %-10s | %-6s\n",
            "Mã MH", "Tên môn", "Số TC", "Số tiết LT", "Số tiết TH", "Học kỳ");
        System.out.println("-------------------------------------------------------------------------------");
    }

    // Có thể bổ sung các hàm add, update, delete, search, thongKe tương tự TestStudent
}