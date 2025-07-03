package com.example.servingwebcontent.database;

import com.example.servingwebcontent.Model.Monhoc;
import java.sql.*;
import java.util.*;

public class monhocAiven {
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://mysql-1af5a7c5-quanlydiem.c.aivencloud.com:22784/defaultdb?ssl-mode=REQUIRED",
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
                    rs.getString("maMon"),
                    rs.getString("tenMon"),
                    rs.getInt("soTinChi"),
                    rs.getString("sotietLT"),
                    rs.getString("sotietTH"),
                    rs.getString("hocky")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // Thêm, sửa, xóa, tìm kiếm tương tự StudentAiven
}
