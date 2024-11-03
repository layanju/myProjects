package pack305;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class booking {

    private static final String URL = "jdbc:mysql://localhost:3306/project2";
    private static final String USER = "root";
    private static final String PASSWORD = "layanasdf";

    public booking(String fullName) throws SQLException {
        ArrayList<String> days = new ArrayList<>();
        ArrayList<ArrayList<LocalTime>> times = new ArrayList<>();

        // إعداد اتصال قاعدة البيانات
        String url = "jdbc:mysql://localhost:3306/project2";
        String user = "root";
        String password = "layanasdf";

        // فصل الاسم الكامل إلى أجزاء
        String[] nameParts = fullName.trim().split(" ");

        // الحصول على الاسم الأول واسم العائلة
        String firstName = nameParts[0];  // الحصول على الاسم الأول
        String lastName = nameParts[1]; // الحصول على اسم العائلة (ثاني جزء)

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            String query = "SELECT * FROM appointments WHERE Fname = ? AND Lname = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String a = resultSet.getString("AppointmentDate");
                    String b = resultSet.getString("AppointmentTime");
                    System.out.println(a + " " + b);
                    showAvailableTimesGUI(fullName, a, b);
                }

            }
        }

    }

    private static void showAvailableTimesGUI(String consultantName, String date, String time) {
        JFrame frame = new JFrame("الأوقات المتاحة للمستشار");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLayout(new BorderLayout());

        // إعداد العنوان 
        JLabel titleLabel = new JLabel("الأوقات المتاحة لـ " + consultantName, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // اللوحة الرئيسية 
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JButton bookButton = new JButton("حجز: " + date + " " + time);

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // تنفيذ عملية الحجز هنا
                bookAppointment(consultantName, date, time);
                new ScheduleManager(false);
            }
        });

        mainPanel.add(bookButton); // إضافة الزر إلى اللوحة
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        
        
    }

//    frame.add (titleLabel, BorderLayout.NORTH);
//    JScrollPane scrollPane = new JScrollPane(mainPanel);
//
//    frame.add (scrollPane, BorderLayout.CENTER);
//
//    frame.setVisible (
//true);
    private static void bookAppointment(String consultantName, String date, String time) {
        // تنفيذ عملية الحجز هنا، مثل إدخال بيانات الحجز في قاعدة البيانات
     JOptionPane.showMessageDialog(null,"تم حجز الموعد للمستشار: " + consultantName + " في التاريخ: " + date + " والوقت: " + time);

        // يمكنك أيضًا إضافة كود لحفظ الحجز في قاعدة البيانات إذا لزم الأمر
        // ...
    }

    public static String getName(String username) {
        String query = "SELECT FirstName,LastName FROM users WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username); // Set the username as the primary key

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String Fname = resultSet.getString("FirstName"); // Retrieve the "role" column
                String Lname = resultSet.getString("LastName"); // Retrieve the "role" column
                String fullName = Fname + " " + Lname;
                return fullName;
            } else {
                System.out.println("Record not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDate(String username) {
        String query = "SELECT AppointmentDate FROM appointment WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username); // Set the username as the primary key

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String Fname = resultSet.getString("FirstName"); // Retrieve the "role" column
                String Lname = resultSet.getString("LastName"); // Retrieve the "role" column
                String fullName = Fname + " " + Lname;
                return fullName;
            } else {
                System.out.println("Record not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
