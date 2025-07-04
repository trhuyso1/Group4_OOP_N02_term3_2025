package com.example.servingwebcontent.database;

import java.sql.*;
import java.util.ArrayList;
import com.example.servingwebcontent.Model.User;

public class userAiven {

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://mysql-1af5a7c5-quanlydiem.c.aivencloud.com:22784/quanlydiem?ssl-mode=REQUIRED",
            "avnadmin", "AVNS_tvvJpWj2LldY7V1XllZ"
        );
    }

    public ArrayList<User> getUserList() {
        ArrayList<User> users = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM login")) {
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                users.add(new User(username, password));
            }
        } catch (Exception e) {
            System.out.println("❌ Error in getUserList: " + e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    public boolean userExists(String username) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT username FROM login WHERE username = ?")) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            System.out.println("❌ Error checking user existence: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean registerUser(String username, String password) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO login (username, password) VALUES (?, ?)")) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.out.println("❌ Error registering user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticateUser(String username, String password) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT username FROM login WHERE username = ? AND password = ?")) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                boolean authenticated = rs.next();
                if (authenticated) {
                    System.out.println("✅ Authentication successful: " + username);
                } else {
                    System.out.println("❌ Authentication failed: " + username);
                }
                return authenticated;
            }
        } catch (Exception e) {
            System.out.println("❌ Error authenticating user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Test kết nối database
    public boolean testConnection() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM login")) {
            if (rs.next()) {
                int count = rs.getInt("count");
                System.out.println("✅ Kết nối database thành công! Số tài khoản: " + count);
            }
            return true;
        } catch (Exception e) {
            System.out.println("❌ Lỗi kết nối database: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Hiển thị tất cả users (debug)
    public void printAllUsers() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM login ORDER BY created_at")) {
            System.out.println("\n📋 DANH SÁCH TÀI KHOẢN:");
            System.out.println("==========================================");
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String createdAt = rs.getString("created_at");
                System.out.println("👤 Username: " + username + " | Password: " + password + " | Created: " + createdAt);
            }
            System.out.println("==========================================\n");
        } catch (Exception e) {
            System.out.println("❌ Lỗi khi hiển thị users: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

