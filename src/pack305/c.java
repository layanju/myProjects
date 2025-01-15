package pack305;

import java.sql.*;

public class c {
    public static void main(String[] args) throws SQLException {
        String UrlDb = "jdbc:mysql://localhost:3306/layan";

        try (Connection connect = DriverManager.getConnection(UrlDb, "root", "layanasdf")) {
            Statement newSt = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            // Use the database
            String database = "StudentDB";
            newSt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + database);
            newSt.execute("USE " + database);

            // Create table with primary key
            String CreateT = "CREATE TABLE IF NOT EXISTS Student("
                    + "ID INT PRIMARY KEY, "
                    + "StudentName VARCHAR(255), "
                    + "CourseNumber INT, "
                    + "ClassNumber INT);";
            newSt.executeUpdate(CreateT);

            // Insert data
            String addInfo = "INSERT INTO Student (ID, StudentName, CourseNumber, ClassNumber) VALUES "
                    + "(1, 'Sara', 89, 45), "
                    + "(2, 'Joud', 50, 33), "
                    + "(3, 'Yara', 100, 28), "
                    + "(4, 'Hala', 18, 26) "
                    + "ON DUPLICATE KEY UPDATE StudentName=VALUES(StudentName);";
            newSt.executeUpdate(addInfo);

            // Fetch and update data
            try (ResultSet rs = newSt.executeQuery("SELECT * FROM Student")) {
                rs.absolute(-2); // Move to second-last row
                rs.updateDouble(3, 100); // Update CourseNumber to 100
                rs.updateRow(); // Apply the update

                do {
                    System.out.println(rs.getInt("ID")); // Print ID
                    System.out.println(rs.getString("StudentName")); // Print name
                    System.out.println(rs.getInt("CourseNumber")); // Print course number
                    System.out.println(rs.getInt("ClassNumber")); // Print class number
                    System.out.println();
                } while (rs.next());

                rs.relative(-2); // Move back relatively
                System.out.println(rs.getString("StudentName"));
                rs.previous(); // Move back one row
                System.out.println(rs.getString("StudentName"));
            }
        }
    }
}
