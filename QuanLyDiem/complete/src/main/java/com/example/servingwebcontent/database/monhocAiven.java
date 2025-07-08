package com.example.servingwebcontent.database;

import com.example.servingwebcontent.Model.Monhoc;
import java.sql.*;
import java.util.*;

public class monhocAiven {
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://mysql-1af5a7c5-quanlydiem.c.aivencloud.com:22784/quanlydiem?ssl-mode=REQUIRED",
            "avnadmin", "AVNS_tvvJpWj2LldY7V1XllZ"
        );
    }

    public List<Monhoc> getAllMonhoc() {
        List<Monhoc> list = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM monhoc")) {
            while (rs.next()) {
                list.add(new Monhoc(
                    rs.getString("ma_mon"),
                    rs.getString("ten_mon"),
                    rs.getInt("so_tin_chi"),
                    rs.getString("so_tiet_lt"),
                    rs.getString("so_tiet_th"),
                    rs.getString("hoc_ky")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void addMonhoc(Monhoc m) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO monhoc (ma_mon, ten_mon, so_tin_chi, so_tiet_lt, so_tiet_th, hoc_ky) VALUES (?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, m.getMaMon());
            ps.setString(2, m.getTenMon());
            ps.setInt(3, m.getSoTinChi());
            ps.setString(4, m.getSotietLT());
            ps.setString(5, m.getSotietTH());
            ps.setString(6, m.getHocky());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void updateMonhoc(Monhoc m) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "UPDATE monhoc SET ten_mon=?, so_tin_chi=?, so_tiet_lt=?, so_tiet_th=?, hoc_ky=? WHERE ma_mon=?")) {
            ps.setString(1, m.getTenMon());
            ps.setInt(2, m.getSoTinChi());
            ps.setString(3, m.getSotietLT());
            ps.setString(4, m.getSotietTH());
            ps.setString(5, m.getHocky());
            ps.setString(6, m.getMaMon());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void deleteMonhoc(String maMon) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM monhoc WHERE ma_mon=?")) {
            ps.setString(1, maMon);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public Monhoc getMonhocById(String maMon) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM monhoc WHERE ma_mon=?")) {
            ps.setString(1, maMon);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Monhoc(
                    rs.getString("ma_mon"),
                    rs.getString("ten_mon"),
                    rs.getInt("so_tin_chi"),
                    rs.getString("so_tiet_lt"),
                    rs.getString("so_tiet_th"),
                    rs.getString("hoc_ky")
                );
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public List<Monhoc> searchMonhoc(String keyword) {
        List<Monhoc> list = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM monhoc WHERE " +
                "ma_mon LIKE ? OR ten_mon LIKE ? OR " +
                "CAST(so_tin_chi AS CHAR) LIKE ? OR " +
                "so_tiet_lt LIKE ? OR so_tiet_th LIKE ? OR hoc_ky LIKE ?")) {
            String kw = "%" + keyword + "%";
            ps.setString(1, kw);
            ps.setString(2, kw);
            ps.setString(3, kw);
            ps.setString(4, kw);
            ps.setString(5, kw);
            ps.setString(6, kw);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Monhoc(
                    rs.getString("ma_mon"),
                    rs.getString("ten_mon"),
                    rs.getInt("so_tin_chi"),
                    rs.getString("so_tiet_lt"),
                    rs.getString("so_tiet_th"),
                    rs.getString("hoc_ky")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}
