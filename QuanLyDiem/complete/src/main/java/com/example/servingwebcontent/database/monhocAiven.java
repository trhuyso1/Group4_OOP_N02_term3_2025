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
        String sql = "SELECT ma_mon, ten_mon, so_tin_chi, so_tiet_lt, so_tiet_th, hoc_ky FROM monhoc";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
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

    // Thêm, sửa, xóa, tìm kiếm tương tự StudentAiven
}
