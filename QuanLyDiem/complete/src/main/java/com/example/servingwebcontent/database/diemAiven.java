package com.example.servingwebcontent.database;

import com.example.servingwebcontent.Model.Diem;
import com.example.servingwebcontent.Model.Student;
import com.example.servingwebcontent.Model.Monhoc;
import java.sql.*;
import java.util.*;

public class diemAiven {
    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://mysql-1af5a7c5-quanlydiem.c.aivencloud.com:22784/defaultdb?ssl-mode=REQUIRED",
            "avnadmin", "AVNS_tvvJpWj2LldY7V1XllZ"
        );
    }

    public List<Diem> getAllDiem() {
        List<Diem> list = new ArrayList<>();
        String sql = "SELECT d.msv, m.tenMon, d.diemMon " +
                     "FROM diem d JOIN monhoc m ON d.maMon = m.maMon";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student sv = new Student();
                sv.setMsv(rs.getString("msv")); // Chỉ set mã SV
                Monhoc mh = new Monhoc();
                mh.setTenMon(rs.getString("tenMon"));
                Diem diem = new Diem(sv, mh, rs.getDouble("diemMon"));
                list.add(diem);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public boolean addDiem(Diem grade) {
        String sql = "INSERT INTO diem (msv, maMon, diemMon) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, grade.getStudent().getMsv());
            ps.setString(2, grade.getMonhoc().getMaMon());
            ps.setDouble(3, grade.getDiemMon());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean updateDiem(Diem grade) {
        String sql = "UPDATE diem SET diemMon=? WHERE msv=? AND maMon=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, grade.getDiemMon());
            ps.setString(2, grade.getStudent().getMsv());
            ps.setString(3, grade.getMonhoc().getMaMon());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean deleteDiem(String msv, String maMon) {
        String sql = "DELETE FROM diem WHERE msv=? AND maMon=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, msv);
            ps.setString(2, maMon);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public Diem findDiem(String msv, String maMon) {
        String sql = "SELECT d.msv, d.maMon, m.tenMon, d.diemMon FROM diem d JOIN monhoc m ON d.maMon = m.maMon WHERE d.msv=? AND d.maMon=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, msv);
            ps.setString(2, maMon);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student sv = new Student();
                sv.setMsv(rs.getString("msv"));
                Monhoc mh = new Monhoc();
                mh.setTenMon(rs.getString("tenMon"));
                return new Diem(sv, mh, rs.getDouble("diemMon"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public List<Diem> searchByAnyField(String keyword) {
        List<Diem> list = new ArrayList<>();
        String sql = "SELECT d.msv, d.maMon, m.tenMon, d.diemMon " +
                     "FROM diem d JOIN monhoc m ON d.maMon = m.maMon " +
                     "WHERE d.msv LIKE ? OR d.maMon LIKE ? OR m.tenMon LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String kw = "%" + keyword + "%";
            ps.setString(1, kw);
            ps.setString(2, kw);
            ps.setString(3, kw);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student sv = new Student();
                sv.setMsv(rs.getString("msv"));
                Monhoc mh = new Monhoc();
                mh.setTenMon(rs.getString("tenMon"));
                Diem diem = new Diem(sv, mh, rs.getDouble("diemMon"));
                list.add(diem);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}
