package com.example.servingwebcontent.CRUD;

import Model.Monhoc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class ListMonHoc implements Iterable<Monhoc> {
    private ArrayList<Monhoc> danhSach = new ArrayList<>();
    private boolean hienThongBao = true;

    public ArrayList<Monhoc> getList() {
        return danhSach;
    }

    public void setHienThongBao(boolean hien) {
        this.hienThongBao = hien;
    }

    public void them(Monhoc obj) {
        if (tonTai(obj.getMaMon())) {
            if (hienThongBao)
                System.out.println("Không thể thêm, mã môn đã tồn tại: " + obj.getMaMon());
            return;
        }
        danhSach.add(obj);
        if (hienThongBao)
            System.out.println("Đã thêm môn: " + obj.getMaMon());
    }

    public boolean tonTai(String maMon) {
        return danhSach.stream().anyMatch(m -> m.getMaMon().equalsIgnoreCase(maMon));
    }

    public void sua(String maMon, Monhoc objMoi) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaMon().equalsIgnoreCase(maMon)) {
                danhSach.set(i, objMoi);
                if (hienThongBao)
                    System.out.println("Đã sửa môn: " + maMon);
                return;
            }
        }
        if (hienThongBao)
            System.out.println("Không tìm thấy môn: " + maMon);
    }

    public boolean xoa(String maMon) {
        Iterator<Monhoc> it = danhSach.iterator();
        while (it.hasNext()) {
            if (it.next().getMaMon().equalsIgnoreCase(maMon)) {
                it.remove();
                if (hienThongBao)
                    System.out.println("Đã xoá môn: " + maMon);
                return true;
            }
        }
        if (hienThongBao)
            System.out.println("Không tìm thấy môn: " + maMon);
        return false;
    }

    public ArrayList<Monhoc> timKiem(String tuKhoa) {
        String key = tuKhoa.toLowerCase();
        return danhSach.stream()
            .filter(m -> m.getMaMon().toLowerCase().contains(key)
                      || m.getTenMon().toLowerCase().contains(key)
                      || String.valueOf(m.getSoTinChi()).contains(key)
                      || m.getSotietLT().toLowerCase().contains(key)
                      || m.getSotietTH().toLowerCase().contains(key)
                      || m.getHocky().toLowerCase().contains(key))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Iterator<Monhoc> iterator() {
        return danhSach.iterator();
    }
}
