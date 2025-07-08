package com.example.servingwebcontent.database;

import com.example.servingwebcontent.Model.Diem;
import com.example.servingwebcontent.Model.Monhoc;
import com.example.servingwebcontent.Model.Student;

import java.sql.*;
import java.util.*;

public class diemAiven {
    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://mysql-1af5a7c5-quanlydiem.c.aivencloud.com:22784/quanlydiem?ssl-mode=REQUIRED",
            "avnadmin", "AVNS_tvvJpWj2LldY7V1XllZ"
        );
    }

    // Lấy tất cả điểm, JOIN để lấy tên SV và tên môn
    public List<Diem> getAllDiem() {
        List<Diem> list = new ArrayList<>();
        String sql = "SELECT d.msv, s.fullname, d.maMon AS maMon, m.ten_mon AS tenMon, d.diemMon " +
                     "FROM diem d " +
                     "JOIN student s ON d.msv = s.msv " +
                     "JOIN monhoc m ON d.maMon = m.ma_mon";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student sv = new Student();
                sv.setMsv(rs.getString("msv"));
                sv.setFullname(rs.getString("fullname"));
                Monhoc mh = new Monhoc();
                mh.setMaMon(rs.getString("maMon"));
                mh.setTenMon(rs.getString("tenMon"));
                Diem diem = new Diem();
                diem.setStudent(sv);
                diem.setMonhoc(mh);
                diem.setDiemMon(rs.getDouble("diemMon"));
                list.add(diem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm điểm
    public boolean addDiem(Diem grade) {
        String sql = "INSERT INTO diem (msv, maMon, diemMon, diemHe4, diemChu, danhGia) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, grade.getStudent().getMsv());
            ps.setString(2, grade.getMonhoc().getMaMon());
            ps.setDouble(3, grade.getDiemMon());
            ps.setDouble(4, grade.getDiemHe4());
            ps.setString(5, grade.getDiemChu());
            ps.setString(6, grade.getDanhGia());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Sửa điểm
    public boolean updateDiem(Diem grade) {
        String sql = "UPDATE diem SET diemMon = ?, diemHe4 = ?, diemChu = ?, danhGia = ? WHERE msv = ? AND maMon = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, grade.getDiemMon());
            ps.setDouble(2, grade.getDiemHe4());
            ps.setString(3, grade.getDiemChu());
            ps.setString(4, grade.getDanhGia());
            ps.setString(5, grade.getStudent().getMsv());
            ps.setString(6, grade.getMonhoc().getMaMon());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa điểm
    public boolean deleteDiem(String msv, String maMon) {
        String sql = "DELETE FROM diem WHERE msv = ? AND maMon = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, msv);
            ps.setString(2, maMon);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm điểm theo msv, maMon (JOIN để lấy tên)
    public Diem findDiem(String msv, String maMon) {
        String sql = "SELECT d.msv, s.fullname, d.maMon AS maMon, m.ten_mon AS tenMon, d.diemMon " +
                     "FROM diem d " +
                     "JOIN student s ON d.msv = s.msv " +
                     "JOIN monhoc m ON d.maMon = m.ma_mon " +
                     "WHERE d.msv = ? AND d.maMon = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, msv);
            ps.setString(2, maMon);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Student sv = new Student();
                    sv.setMsv(rs.getString("msv"));
                    sv.setFullname(rs.getString("fullname"));
                    Monhoc mh = new Monhoc();
                    mh.setMaMon(rs.getString("maMon"));
                    mh.setTenMon(rs.getString("tenMon"));
                    Diem diem = new Diem();
                    diem.setStudent(sv);
                    diem.setMonhoc(mh);
                    diem.setDiemMon(rs.getDouble("diemMon"));
                    return diem;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Diem> searchByAnyField(String keyword) {
        List<Diem> list = new ArrayList<>();
        String sql = "SELECT d.msv, s.fullname, d.maMon AS maMon, m.ten_mon AS tenMon, d.diemMon " +
                     "FROM diem d " +
                     "JOIN student s ON d.msv = s.msv " +
                     "JOIN monhoc m ON d.maMon = m.ma_mon " +
                     "WHERE d.msv LIKE ? OR s.fullname LIKE ? OR d.maMon LIKE ? OR m.ten_mon LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String kw = "%" + keyword + "%";
            ps.setString(1, kw);
            ps.setString(2, kw);
            ps.setString(3, kw);
            ps.setString(4, kw);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student sv = new Student();
                sv.setMsv(rs.getString("msv"));
                sv.setFullname(rs.getString("fullname"));
                Monhoc mh = new Monhoc();
                mh.setMaMon(rs.getString("maMon"));
                mh.setTenMon(rs.getString("tenMon"));
                Diem diem = new Diem();
                diem.setStudent(sv);
                diem.setMonhoc(mh);
                diem.setDiemMon(rs.getDouble("diemMon"));
                list.add(diem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
