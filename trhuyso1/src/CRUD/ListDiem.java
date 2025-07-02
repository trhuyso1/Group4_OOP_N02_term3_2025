package CRUD;

import Model.Diem;
import java.util.ArrayList;
import java.util.Iterator;

public class ListDiem implements Iterable<Diem> {
    private ArrayList<Diem> danhSach = new ArrayList<>();
    private boolean daThemMau = false;
    private boolean hienThongBao = true;

    public void setHienThongBao(boolean hien) {
        this.hienThongBao = hien;
    }

    public void them(Diem obj) {
        if (timKiem(obj.getStudent().getMsv(), obj.getMonhoc().getMaMon()) != null) {
            if (hienThongBao)
                System.out.println("Không thể thêm điểm, đã tồn tại cho sinh viên và môn học này.");
            return;
        }
        danhSach.add(obj);
        if (hienThongBao)
            System.out.println("Đã thêm điểm thành công!");
    }

    public void sua(String msv, String maMon, Diem objMoi) {
        for (int i = 0; i < danhSach.size(); i++) {
            Diem d = danhSach.get(i);
            if (d.getStudent().getMsv().equalsIgnoreCase(msv) && d.getMonhoc().getMaMon().equalsIgnoreCase(maMon)) {
                danhSach.set(i, objMoi);
                if (hienThongBao)
                    System.out.println("Đã sửa điểm thành công!");
                return;
            }
        }
        if (hienThongBao)
            System.out.println("Không tìm thấy điểm cần sửa.");
    }

    public boolean xoa(String msv, String maMon) {
        for (int i = 0; i < danhSach.size(); i++) {
            Diem d = danhSach.get(i);
            if (d.getStudent().getMsv().equalsIgnoreCase(msv) && d.getMonhoc().getMaMon().equalsIgnoreCase(maMon)) {
                danhSach.remove(i);
                if (hienThongBao)
                    System.out.println("Đã xóa điểm thành công!");
                return true;
            }
        }
        if (hienThongBao)
            System.out.println("Không tìm thấy điểm cần xóa.");
        return false;
    }

    public Diem timKiem(String msv, String maMon) {
        for (Diem d : danhSach) {
            if (d.getStudent().getMsv().equalsIgnoreCase(msv) && d.getMonhoc().getMaMon().equalsIgnoreCase(maMon)) {
                return d;
            }
        }
        return null;
    }

    public void inDanhSach() {
        if (danhSach.isEmpty()) {
            System.out.println("Danh sách rỗng.");
            return;
        }
        for (Diem obj : danhSach) {
            System.out.println(obj);
        }
    }

    public ArrayList<Diem> getList() {
        return danhSach;
    }

    public boolean tonTai(String msv, String maMon) {
        return timKiem(msv, maMon) != null;
    }

    public boolean isDaThemMau() {
        return daThemMau;
    }

    public void setDaThemMau(boolean daThemMau) {
        this.daThemMau = daThemMau;
    }

    public void addList(ArrayList<Diem> ds) {
        if (daThemMau) {
            if (hienThongBao)
                System.out.println("Danh sách mẫu đã được thêm.");
            return;
        }
        for (Diem obj : ds) {
            if (timKiem(obj.getStudent().getMsv(), obj.getMonhoc().getMaMon()) == null) {
                danhSach.add(obj);
            }
        }
        daThemMau = true;
        if (hienThongBao)
            System.out.println("Đã thêm danh sách mẫu.");
    }

    @Override
    public Iterator<Diem> iterator() {
        return danhSach.iterator();
    }
}
