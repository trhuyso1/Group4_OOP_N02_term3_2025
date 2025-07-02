package CRUD;

import Model.Student;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class ListStudent implements Iterable<Student> {
    private ArrayList<Student> danhSach = new ArrayList<>();
    private boolean daThemMau = false;
    private boolean hienThongBao = true;

    public void setHienThongBao(boolean hien) {
        this.hienThongBao = hien;
    }

    public void them(Student obj) {
        if (tonTai(obj.getMsv())) {
            if (hienThongBao)
                System.out.println("Không thể thêm ID: '" + obj.getMsv() + "' đã tồn tại.");
            return;
        }
        danhSach.add(obj);
        if (hienThongBao)
            System.out.println("Đã thêm thành công có ID: " + obj.getMsv());
    }

    public void sua(String msv, Student objMoi) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMsv().equalsIgnoreCase(msv)) {
                danhSach.set(i, objMoi);
                if (hienThongBao)
                    System.out.println("Đã sửa thành công!");
                return;
            }
        }
        if (hienThongBao)
            System.out.println("Không tìm thấy đối tượng có ID: " + msv);
    }

    public boolean xoa(String msv) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMsv().equalsIgnoreCase(msv)) {
                danhSach.remove(i);
                if (hienThongBao)
                    System.out.println("Đã xóa thành công!");
                return true;
            }
        }
        if (hienThongBao)
            System.out.println("Không tìm thấy đối tượng có ID: " + msv);
        return false;
    }


    // Tìm kiếm theo bất kỳ thông tin nào chứa từ khóa (không phân biệt hoa thường)
    public ArrayList<Student> timKiem(String tuKhoa) {
        String key = tuKhoa.toLowerCase();
        return (ArrayList<Student>) danhSach.stream()
            .filter(s -> s.getMsv().toLowerCase().contains(key)
                      || s.getFullName().toLowerCase().contains(key)
                      || s.getGender().toLowerCase().contains(key)
                      || s.getEmail().toLowerCase().contains(key)
                      || s.getDob().toLowerCase().contains(key)
                      || s.getKhoa().toLowerCase().contains(key)
                      || s.getClassName().toLowerCase().contains(key))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public void inDanhSach() {
        if (danhSach.isEmpty()) {
            System.out.println("Danh sách rỗng.");
            return;
        }
        for (Student obj : danhSach) {
            System.out.println(obj);
        }
    }

    public ArrayList<Student> getList() {
        return danhSach;
    }

    public boolean tonTai(String msv) {
        return danhSach.stream().anyMatch(s -> s.getMsv().equalsIgnoreCase(msv));
    }

    public boolean isDaThemMau() {
        return daThemMau;
    }

    public void setDaThemMau(boolean daThemMau) {
        this.daThemMau = daThemMau;
    }

    public void addList(ArrayList<Student> ds) {
        if (daThemMau) {
            if (hienThongBao)
                System.out.println("Danh sách mẫu đã được thêm.");
            return;
        }
        for (Student obj : ds) {
            if (timKiem(obj.getMsv()) == null) {
                danhSach.add(obj);
            }
        }
        daThemMau = true;
        if (hienThongBao)
            System.out.println("Đã thêm danh sách mẫu thành công!");
    }

    @Override
    public Iterator<Student> iterator() {
        return danhSach.iterator();
    }
}
