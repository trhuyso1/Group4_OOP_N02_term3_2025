// package com.example.servingwebcontent.database;

// import com.example.servingwebcontent.Model.Diem;
// import java.sql.*;
// import java.util.*;

// public class diemAiven {
//     private Connection getConnection() throws Exception {
//         Class.forName("com.mysql.cj.jdbc.Driver");
//         return DriverManager.getConnection(
//             "jdbc:mysql://mysql-1af5a7c5-quanlydiem.c.aivencloud.com:22784/defaultdb?ssl-mode=REQUIRED",
//             "avnadmin", "AVNS_tvvJpWj2LldY7V1XllZ"
//         );
//     }

//     public List<Diem> getAllDiem() {
//         List<Diem> list = new ArrayList<>();
//         try (Connection conn = getConnection();
//              Statement stmt = conn.createStatement();
//              ResultSet rs = stmt.executeQuery("SELECT * FROM diem")) {
//             while (rs.next()) {
//                 list.add(new Diem(
//                     rs.getString("msv"),
//                     rs.getString("maMon"),
//                     rs.getDouble("diemQT"),
//                     rs.getDouble("diemCK"),
//                     rs.getDouble("diemTK")
//                 ));
//             }
//         } catch (Exception e) { e.printStackTrace(); }
//         return list;
//     }

//     // Thêm, sửa, xóa, tìm kiếm tương tự StudentAiven
// }
