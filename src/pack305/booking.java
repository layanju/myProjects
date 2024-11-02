package pack305;


import com.toedter.calendar.JCalendar;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class booking extends JFrame {
    private JCalendar dateChooser;
    private JComboBox<String> timeComboBox;
    private JButton bookButton;
    private JLabel dateLabel;
    private JLabel timeLabel;

    // Constructor
    public booking() {
        initComponents();
    }

    private void initComponents() {
        // Set up the frame
        setTitle("Book an appointment");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create a custom JPanel with a background image
        ImageIcon backgroundImage = new ImageIcon(booking.class.getResource("/image/bground1.png")); 
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for centering components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding between components

        // Initialize components
        JLabel instructionLabel = new JLabel("Please select a date and time for your reservation:");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // Change font size 

        dateLabel = new JLabel("Select Date:");
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        dateChooser = new JCalendar();
        dateChooser.getDayChooser().setMinSelectableDate(new Date()); // Disable past dates

        timeLabel = new JLabel("Select Time:");
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        timeComboBox = new JComboBox<>(new String[] { "6:00 am", "8:00 am", "12:00 pm", "2:00 pm", "5:00 pm", "7:00 pm" });
        timeComboBox.setFont(new Font("Arial", Font.PLAIN, 18));

        bookButton = new JButton("BOOK");
        bookButton.setFont(new Font("Arial", Font.PLAIN, 18));
        bookButton.setBackground(new Color(0x74b4c4)); // Set background color for BOOK button

        // Add components to layout
        // Add instruction label (row 0)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(instructionLabel, gbc);

        // Add date label (row 1)
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        backgroundPanel.add(dateLabel, gbc);

        // Add date chooser (row 1)
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        backgroundPanel.add(dateChooser, gbc);

        // Add time label (row 2)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        backgroundPanel.add(timeLabel, gbc);

        // Add time combo box (row 2)
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        backgroundPanel.add(timeComboBox, gbc);

        // Add book button (row 3)
        gbc.gridy = 3;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(bookButton, gbc);

        // Add action listener to the book button
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bookActionPerformed(evt);
            }
        });
JButton backButton = new JButton("← Back");
backButton.setBounds(3, 3, 80, 45); // تعيين موقع وحجم الزر
backButton.setBackground(Color.decode("#74b4c4")); // تعيين لون خلفية الزر
backgroundPanel.add(backButton);

backButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        AdvisorsGUI advisorsGUI = new AdvisorsGUI(); // فتح واجهة مستشاريين
        advisorsGUI.createConsultationGUI();
        setVisible(true);
       
    }
});
        // Add backgroundPanel to the frame
        setContentPane(backgroundPanel);
        setVisible(true);
    }

    public void bookActionPerformed(ActionEvent evt) {
        // Get selected date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String selectedDate = dateFormat.format(dateChooser.getDate());
        String selectedTime = (String) timeComboBox.getSelectedItem();

        // Custom OK button
        JButton okButton = new JButton("OK");
        okButton.setBackground(new Color(0x74b4c4));
        okButton.setFont(new Font("Arial", Font.PLAIN, 18));

        // Custom option pane with custom OK button
        JOptionPane optionPane = new JOptionPane("Booking confirmed for " + selectedDate + " at " + selectedTime,
                JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[] { okButton }, okButton);

        JDialog dialog = optionPane.createDialog(this, "Confirmation");
        
       

        dialog.setVisible(true);
    }

 
}
