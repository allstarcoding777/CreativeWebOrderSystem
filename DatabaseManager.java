// DatabaseManager.java
// Author: Alex Ellis
// Module 5: SQLite database connection and order management

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:creativeweborders.db";

    public static Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }

    public static boolean initializeDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS orders ("
                   + "orderId INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + "customerName TEXT NOT NULL, "
                   + "projectType TEXT NOT NULL, "
                   + "status TEXT NOT NULL)";

        try (Connection conn = connect()) {
            if (conn == null) return false;
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to create table: " + e.getMessage());
            return false;
        }
    }

    public static boolean insertOrder(String customerName, String projectType, String status) {
        String sql = "INSERT INTO orders (customerName, projectType, status) VALUES (?, ?, ?)";
        try (Connection conn = connect()) {
            if (conn == null) return false;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, customerName);
                pstmt.setString(2, projectType);
                pstmt.setString(3, status);
                pstmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error inserting order: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateOrderStatus(int orderId, String newStatus) {
        String sql = "UPDATE orders SET status = ? WHERE orderId = ?";
        try (Connection conn = connect()) {
            if (conn == null) return false;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, newStatus);
                pstmt.setInt(2, orderId);
                int rows = pstmt.executeUpdate();
                return rows > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error updating order: " + e.getMessage());
            return false;
        }
    }

    public static void printOrdersReport() {
        String sql = "SELECT * FROM orders";
        try (Connection conn = connect()) {
            if (conn == null) return;
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    System.out.println(
                        "Order ID: " + rs.getInt("orderId") +
                        ", Customer: " + rs.getString("customerName") +
                        ", Project: " + rs.getString("projectType") +
                        ", Status: " + rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving orders: " + e.getMessage());
        }
    }
}



