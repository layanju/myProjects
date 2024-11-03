/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack305;

import com.toedter.calendar.JCalendar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleManager extends JFrame implements ActionListener {

    private JCalendar dateChooser;
    private JComboBox<String> timeComboBox;
    private JButton addButton;
    private DefaultListModel<String> availableTimesModel;
    private JList<String> scheduleList;
    private boolean booking;

    // Constructor
    
       public ScheduleManager(boolean booking){
          this.booking=booking;
       }
    
    public ScheduleManager() {
        availableTimesModel = new DefaultListModel<>();

        // Set up the frame
        setTitle("Schedule Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a custom JPanel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Initialize components
        JLabel dateLabel = new JLabel("Select Date:");
        dateChooser = new JCalendar();
        dateChooser.getDayChooser().setMinSelectableDate(new Date());

        JLabel timeLabel = new JLabel("Select Time:");
        timeComboBox = new JComboBox<>(new String[]{"8:00 am", "9:00 am", "10:00 am", "11:00 am", "12:00 pm"});

        addButton = new JButton("Add Time");
        addButton.addActionListener(this);

        // Add components to layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(dateLabel, gbc);
        gbc.gridx = 1;
        panel.add(dateChooser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(timeLabel, gbc);
        gbc.gridx = 1;
        panel.add(timeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(addButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(new JScrollPane(scheduleList), gbc);

        // Add panel to frame
        setContentPane(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                addTime();
            } catch (SQLException ex) {
                Logger.getLogger(ScheduleManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ScheduleManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void addTime() throws SQLException, ParseException {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate());
        String time = (String) timeComboBox.getSelectedItem();

        SimpleDateFormat originalFormat = new SimpleDateFormat("h:mm a");
        SimpleDateFormat targetFormat = new SimpleDateFormat("HH:mm:ss");
        time = targetFormat.format(originalFormat.parse(time));

        String connectionURL = "jdbc:mysql://localhost:3306/project2";
        Connection con = DriverManager.getConnection(connectionURL, "root", "layanasdf");
        PreparedStatement ps;
        String username = Session.getUsername();

        String query = "SELECT FirstName, LastName FROM consultative WHERE Username = ?";
        ps = con.prepareStatement(query);

        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        String firstName = null;
        String lastName = null;
        if (rs.next()) {
            firstName = rs.getString("FirstName");
            lastName = rs.getString("LastName");
        }

        String sql = "INSERT INTO appointments (Username,Fname,Lname, AppointmentDate, AppointmentTime, Booked) VALUES (?,?,?,?,?,?)";
        ps = con.prepareStatement(sql);

        try {
            ps.setString(1, username);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, date);
            ps.setString(5, time);
            ps.setBoolean(6, true);

            ps.executeUpdate();
            System.out.println("Appointment added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

}
