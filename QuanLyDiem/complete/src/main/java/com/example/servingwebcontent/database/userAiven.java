package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
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
        Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM login");

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                User user = new User(username, password);
                users.add(user);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error in database connection");
            e.printStackTrace();
        }
        return users;
    }

    // Phương thức kiểm tra user có tồn tại không
    public boolean userExists(String username) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("SELECT username FROM login WHERE username = ?");
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Error checking user existence");
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Phương thức đăng ký user mới
    public boolean registerUser(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "INSERT INTO login (username, password) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    // Phương thức xác thực đăng nhập
    public boolean authenticateUser(String username, String password) {
        System.out.println("🔐 Authenticating user: " + username);
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("SELECT username FROM login WHERE username = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            
            boolean authenticated = rs.next();
            if (authenticated) {
                System.out.println("✅ Authentication successful: " + username);
            } else {
                System.out.println("❌ Authentication failed: " + username);
            }
            
            return authenticated;
        } catch (Exception e) {
            System.out.println("❌ Error authenticating user: " + username + " - " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Phương thức test kết nối database
    public boolean testConnection() {
        Connection conn = null;
        try {
            conn = getConnection();
            System.out.println("✅ Kết nối database thành công!");
            
            // Test query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM login");
            if (rs.next()) {
                int count = rs.getInt("count");
                System.out.println("📊 Số lượng tài khoản trong bảng login: " + count);
            }
            
            rs.close();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println("❌ Lỗi kết nối database: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Phương thức hiển thị tất cả users (để debug)
    public void printAllUsers() {
        Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM login ORDER BY created_at");
            
            System.out.println("\n📋 DANH SÁCH TÀI KHOẢN:");
            System.out.println("==========================================");
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String createdAt = rs.getString("created_at");
                System.out.println("👤 Username: " + username + " | Password: " + password + " | Created: " + createdAt);
            }
            System.out.println("==========================================\n");
            
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("❌ Lỗi khi hiển thị users: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}

