import java.sql.*;

public class Postgres {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to PostgreSQL!");

            insertMenuItem(conn, "Cheese Pizza", 11.99, true);
            readMenuItems(conn);
            updateMenuItemPrice(conn, "Cheese Pizza", 12.99);
            deleteMenuItem(conn, "Cheese Pizza");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void insertMenuItem(Connection conn, String name, double price, boolean isVegetarian) throws SQLException {
        String sql = "INSERT INTO menuitem (name, price, is_vegetarian) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setBoolean(3, isVegetarian);
            stmt.executeUpdate();
            System.out.println("Inserted: " + name);
        }
    }


    private static void readMenuItems(Connection conn) throws SQLException {
        String sql = "SELECT * FROM menuitem";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Menu Items:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("name") + " | $" + rs.getDouble("price") + " | Vegetarian: " + rs.getBoolean("is_vegetarian"));
            }
        }
    }


    private static void updateMenuItemPrice(Connection conn, String name, double newPrice) throws SQLException {
        String sql = "UPDATE menuitem SET price = ? WHERE name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, newPrice);
            stmt.setString(2, name);
            int rowsUpdated = stmt.executeUpdate();
            System.out.println("Updated " + rowsUpdated + " row(s)");
        }
    }


    private static void deleteMenuItem(Connection conn, String name) throws SQLException {
        String sql = "DELETE FROM menuitem WHERE name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            int rowsDeleted = stmt.executeUpdate();
            System.out.println("Deleted " + rowsDeleted + " row(s)");
        }
    }
}

