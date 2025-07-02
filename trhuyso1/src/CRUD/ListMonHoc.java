package CRUD;

import Model.Monhoc;
import java.util.ArrayList;
import java.util.Iterator;

public class ListMonHoc implements Iterable<Monhoc> {
    private ArrayList<Monhoc> danhSach = new ArrayList<>();
    private boolean daThemMau = false;
    private boolean hienThongBao = true;

    public void setHienThongBao(boolean hien) {
        this.hienThongBao = hien;
    }

    public void them(Monhoc obj) {
        if (timKiem(obj.getMaMon()) != null) {
            if (hienThongBao)
                System.out.println("Không thể thêm ID: '" + obj.getMaMon() + "' đã tồn tại.");
            return;
        }
        danhSach.add(obj);
        if (hienThongBao)
            System.out.println("Đã thêm thành công có ID: " + obj.getMaMon());
    }

    public void sua(String maMon, Monhoc objMoi) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaMon().equalsIgnoreCase(maMon)) {
                danhSach.set(i, objMoi);
                if (hienThongBao)
                    System.out.println("Đã sửa thành công!");
                return;
            }
        }
        if (hienThongBao)
            System.out.println("Không tìm thấy đối tượng có ID: " + maMon);
    }

    public boolean xoa(String maMon) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaMon().equalsIgnoreCase(maMon)) {
                danhSach.remove(i);
                if (hienThongBao)
                    System.out.println("Đã xóa thành công!");
                return true;
            }
        }
        if (hienThongBao)
            System.out.println("Không tìm thấy đối tượng có ID: " + maMon);
        return false;
    }

    public Monhoc timKiem(String maMon) {
        for (Monhoc obj : danhSach) {
            if (obj.getMaMon().equalsIgnoreCase(maMon)) {
                return obj;
            }
        }
        return null;
    }

    public void inDanhSach() {
        if (danhSach.isEmpty()) {
            System.out.println("Danh sách rỗng.");
            return;
        }
        for (Monhoc obj : danhSach) {
            System.out.println(obj);
        }
    }

    public ArrayList<Monhoc> getList() {
        return danhSach;
    }

    public boolean tonTai(String maMon) {
        return timKiem(maMon) != null;
    }

    public boolean isDaThemMau() {
        return daThemMau;
    }

    public void setDaThemMau(boolean daThemMau) {
        this.daThemMau = daThemMau;
    }

    public void addList(ArrayList<Monhoc> ds) {
        if (daThemMau) {
            if (hienThongBao)
                System.out.println("Danh sách mẫu đã được thêm.");
            return;
        }
        for (Monhoc obj : ds) {
            if (timKiem(obj.getMaMon()) == null) {
                danhSach.add(obj);
            }
        }
        daThemMau = true;
        if (hienThongBao)
            System.out.println("Đã thêm danh sách mẫu thành công!");
    }

    @Override
    public Iterator<Monhoc> iterator() {
        return danhSach.iterator();
    }
}
