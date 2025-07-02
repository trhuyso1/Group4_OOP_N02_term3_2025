package CRUD;

import Model.Diem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class ListDiem implements Iterable<Diem> {
    private ArrayList<Diem> danhSach = new ArrayList<>();
    private boolean hienThongBao = true;

    public ArrayList<Diem> getList() {
        return danhSach;
    }

    public void setHienThongBao(boolean hien) {
        this.hienThongBao = hien;
    }

    public void them(Diem obj) {
        if (tonTai(obj.getStudent().getMsv(), obj.getMonhoc().getMaMon())) {
            if (hienThongBao)
                System.out.println("Điểm đã tồn tại cho SV: " + obj.getStudent().getMsv() + " - " + obj.getMonhoc().getMaMon());
            return;
        }
        danhSach.add(obj);
        if (hienThongBao)
            System.out.println("Đã thêm điểm cho SV: " + obj.getStudent().getMsv() + " - " + obj.getMonhoc().getMaMon());
    }

    public boolean tonTai(String msv, String maMon) {
        return danhSach.stream().anyMatch(d ->
            d.getStudent().getMsv().equalsIgnoreCase(msv)
            && d.getMonhoc().getMaMon().equalsIgnoreCase(maMon)
        );
    }

    public void sua(String msv, String maMon, Diem objMoi) {
        for (int i = 0; i < danhSach.size(); i++) {
            Diem d = danhSach.get(i);
            if (d.getStudent().getMsv().equalsIgnoreCase(msv)
                && d.getMonhoc().getMaMon().equalsIgnoreCase(maMon)) {
                danhSach.set(i, objMoi);
                if (hienThongBao)
                    System.out.println("Đã sửa điểm cho SV: " + msv + " - " + maMon);
                return;
            }
        }
        if (hienThongBao)
            System.out.println("Không tìm thấy điểm của SV: " + msv + " - " + maMon);
    }

    public boolean xoa(String msv, String maMon) {
        Iterator<Diem> it = danhSach.iterator();
        while (it.hasNext()) {
            Diem d = it.next();
            if (d.getStudent().getMsv().equalsIgnoreCase(msv)
                && d.getMonhoc().getMaMon().equalsIgnoreCase(maMon)) {
                it.remove();
                if (hienThongBao)
                    System.out.println("Đã xoá điểm của SV: " + msv + " - " + maMon);
                return true;
            }
        }
        if (hienThongBao)
            System.out.println("Không tìm thấy điểm của SV: " + msv + " - " + maMon);
        return false;
    }

    public ArrayList<Diem> timKiem(String tuKhoa) {
        String key = tuKhoa.toLowerCase();
        return danhSach.stream()
            .filter(d -> d.getStudent().getMsv().toLowerCase().contains(key)
                      || d.getStudent().getFullName().toLowerCase().contains(key)
                      || d.getMonhoc().getMaMon().toLowerCase().contains(key)
                      || d.getMonhoc().getTenMon().toLowerCase().contains(key)
                      || String.valueOf(d.getDiemMon()).contains(key)
                      || String.valueOf(d.getDiemHe4()).contains(key)
                      || d.getDiemChu().toLowerCase().contains(key)
                      || d.getDanhGia().toLowerCase().contains(key))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Iterator<Diem> iterator() {
        return danhSach.iterator();
    }
}
