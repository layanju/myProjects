package pack305;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Welcome extends JFrame {

    // Main method to start the consultation system
    public void Start() {
        JFrame myFrame = new JFrame("Consultation System");
        myFrame.setSize(900, 700);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Welcome to the Consultation System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(0, 420, myFrame.getWidth(), 50);
        myFrame.getContentPane().add(titleLabel);

        ImageIcon backgroundImage = new ImageIcon(Welcome.class.getResource("/image/background2.png"));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, -40, myFrame.getWidth(), myFrame.getHeight());
        myFrame.setLayout(null);
        myFrame.getContentPane().add(backgroundLabel);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(275, 500, 150, 45);
        loginButton.setBackground(Color.decode("#74b4c4"));

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(475, 500, 150, 45);
        signUpButton.setBackground(Color.decode("#74b4c4"));

        myFrame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginFrame();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSignUpFrame();
                dispose();
            }
        });

        myFrame.getContentPane().add(loginButton);
        myFrame.getContentPane().setComponentZOrder(loginButton, 0);
        myFrame.getContentPane().add(signUpButton);
        myFrame.getContentPane().setComponentZOrder(signUpButton, 0);
    }

    public void openSignUpFrame() {
        JFrame signUpFrame = new JFrame("Sign Up");
        signUpFrame.setSize(900, 700);
        signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ImageIcon signUpBackground = new ImageIcon(Welcome.class.getResource("/image/signup1.png"));
        JLabel backgroundLabel = new JLabel(signUpBackground);
        backgroundLabel.setBounds(0, 0, signUpFrame.getWidth(), signUpFrame.getHeight());

        signUpFrame.setLayout(null);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(500, 200, 100, 25);
        firstNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        signUpFrame.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(600, 200, 250, 35);
        signUpFrame.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(500, 250, 100, 25);
        lastNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        signUpFrame.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(600, 250, 250, 35);
        signUpFrame.add(lastNameField);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(500, 300, 100, 25);
        userLabel.setFont(new Font("Arial", Font.BOLD, 18));
        signUpFrame.add(userLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(600, 300, 250, 35);
        signUpFrame.add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(500, 350, 100, 25);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 18));
        signUpFrame.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(600, 350, 250, 35);
        signUpFrame.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(500, 400, 100, 25);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        signUpFrame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(600, 400, 250, 35);
        signUpFrame.add(passwordField);

        // Role radio buttons
        JRadioButton consultativeButton = new JRadioButton("Consultative");
        consultativeButton.setBounds(600, 450, 150, 30);

        JRadioButton normalUserButton = new JRadioButton("User");
        normalUserButton.setBounds(750, 450, 100, 30);

        ButtonGroup group = new ButtonGroup();
        group.add(normalUserButton);
        group.add(consultativeButton);

        signUpFrame.add(consultativeButton);
        signUpFrame.add(normalUserButton);

        // أزرار الراديو الخاصة بالمهن
        JRadioButton drawingButton = new JRadioButton("Drawing");
        drawingButton.setBounds(600, 500, 150, 30);
        drawingButton.setEnabled(false); // تعطيل الزر في البداية

        JRadioButton musicButton = new JRadioButton("Music");
        musicButton.setBounds(750, 500, 100, 30);
        musicButton.setEnabled(false); // تعطيل الزر في البداية

        JRadioButton writingButton = new JRadioButton("Writing");
        writingButton.setBounds(600, 530, 150, 30);
        writingButton.setEnabled(false); // تعطيل الزر في البداية

        JRadioButton photographyButton = new JRadioButton("Photography");
        photographyButton.setBounds(750, 530, 100, 30);
        photographyButton.setEnabled(false); // تعطيل الزر في البداية

        // مجموعة لأزرار الراديو الخاصة بالمهن
        ButtonGroup professionGroup = new ButtonGroup();
        professionGroup.add(drawingButton);
        professionGroup.add(musicButton);
        professionGroup.add(writingButton);
        professionGroup.add(photographyButton);

        signUpFrame.add(drawingButton);
        signUpFrame.add(musicButton);
        signUpFrame.add(writingButton);
        signUpFrame.add(photographyButton);

        consultativeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                drawingButton.setEnabled(true);
                musicButton.setEnabled(true);
                writingButton.setEnabled(true);
                photographyButton.setEnabled(true);
            }
        });

        normalUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                drawingButton.setEnabled(false);
                musicButton.setEnabled(false);
                writingButton.setEnabled(false);
                photographyButton.setEnabled(false);

                drawingButton.setSelected(false);
                musicButton.setSelected(false);
                writingButton.setSelected(false);
                photographyButton.setSelected(false);
            

            }
        });

        signUpFrame.setVisible(true);

        JButton createAccountButton = new JButton("Sign Up");
        createAccountButton.setBounds(648, 580, 150, 45);
        createAccountButton.setBackground(Color.decode("#74b4c4"));
        signUpFrame.add(createAccountButton);
        signUpFrame.getContentPane().add(backgroundLabel);
        signUpFrame.setVisible(true);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                String role = "";
                String profession = "";
                if (consultativeButton.isSelected()) {
                    role = "Consultative";

                    if (drawingButton.isSelected()) {
                        profession = "Drawing";
                    } else if (musicButton.isSelected()) {
                        profession = "music";
                    } else if (writingButton.isSelected()) {
                        profession = "writing";
                    } else {
                        profession = "Photography";
                    }

                    if (!(drawingButton.isSelected() || musicButton.isSelected() || writingButton.isSelected() || photographyButton.isSelected())) {
                        JOptionPane.showMessageDialog(signUpFrame, "Please choose one of the profession.");
                        return;

                    } else if (normalUserButton.isSelected()) {
                        role = "User";
                    }

                    if (!email.contains("@") || !email.contains(".")) {
                        JOptionPane.showMessageDialog(signUpFrame, "Invalid email format.");
                        return;
                    }
                } else if (normalUserButton.isSelected()) {
                    role = "User";
                }
                try {
                    if (registerUser(firstName, lastName, username, email, password, role, profession)) {

                        JOptionPane.showMessageDialog(signUpFrame, "Sign-up successful!");
                        Session.setUsername(username);
                        if (role.equalsIgnoreCase("Consultative")) {
                            Home home = new Home();
                            home.ConsultativeHome();
                            signUpFrame.dispose();
                            home.setVisible(true);
                        } else {
                            Home home = new Home();
                            home.UserHome();
                            home.setVisible(true);
                            signUpFrame.dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(signUpFrame, "Sign-up failed. Username may already exist.");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private boolean registerUser(String firstName, String lastName, String username, String email, String password, String role, String profession) throws SQLException {
        String connectionURL = "jdbc:mysql://localhost:3306/project2";

        try (Connection con = DriverManager.getConnection(connectionURL, "root", "layanasdf")) {
            PreparedStatement ps;

            // تحقق من الدور وأعد توجيه إلى الاستعلام المناسب
            if (role.equalsIgnoreCase("user")) {
                ps = con.prepareStatement("INSERT INTO USERS (FirstName, LastName, Username, Email, Password, Role) VALUES (?, ?, ?, ?, ?, ?)");

                // تعيين القيم في الاستعلام
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, username);
                ps.setString(4, email);
                ps.setString(5, password);
                ps.setString(6, role);

                ps.executeUpdate();
                return true;
            } else if (role.equalsIgnoreCase("Consultative")) {
                ps = con.prepareStatement("INSERT INTO consultative (FirstName, LastName, Username, Email,Role , Password,  Profession) VALUES (?, ?, ?, ?, ?, ?, ?)");

                // تعيين القيم في الاستعلام
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, username);
                ps.setString(4, email);
                ps.setString(5, role);
                ps.setString(6, password);
                ps.setString(7, profession);
                ps.executeUpdate();
                return true;
            } else {
                // إذا لم يكن الدور "user" أو "Consultative"، قم بإرجاع false
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticateUser(String username, String password) {
        String connectionURL = "jdbc:mysql://localhost:3306/project2";
        try (Connection con = DriverManager.getConnection(connectionURL, "root", "layanasdf")) {
            ResultSet rs;
            Boolean result;

            String query = "SELECT * FROM USERS WHERE Username = ? AND Password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {

                return true;
            }

            query = "SELECT * FROM consultative WHERE Username = ? AND Password = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            return false;
        }

    }

    public void openLoginFrame() {
        JFrame logInUpFrame = new JFrame("Log in");
        logInUpFrame.setSize(900, 700);
        logInUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ImageIcon loginBackground = new ImageIcon(Welcome.class.getResource("/image/login1.png"));
        JLabel backgroundLabel = new JLabel(loginBackground);
        backgroundLabel.setBounds(0, 0, logInUpFrame.getWidth(), logInUpFrame.getHeight());
        logInUpFrame.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(500, 250, 100, 25);
        userLabel.setFont(new Font("Arial", Font.BOLD, 18));
        logInUpFrame.add(userLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(600, 250, 250, 35);
        logInUpFrame.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(500, 300, 100, 25);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        logInUpFrame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(600, 300, 250, 35);
        logInUpFrame.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(650, 420, 150, 45);
        loginButton.setBackground(Color.decode("#74b4c4"));
        logInUpFrame.add(loginButton);
        logInUpFrame.getContentPane().add(backgroundLabel);
        logInUpFrame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticateUser(username, password)) {
                    String getRole = getDataByPrimaryKey(username);
                    String getRole2 = getDataByPrimaryKey2(username);
                    Session.setUsername(username);
                    if (getRole2 != null && getRole == null) {
                        Home home = new Home();
                        home.ConsultativeHome();
                        home.setVisible(true);
                        Welcome.this.dispose();
                    } else if (getRole != null && getRole2 == null) {
                        Home home = new Home();
                        home.UserHome();
                        home.setVisible(true);
                        Welcome.this.dispose();

                    } else {
                        JOptionPane.showMessageDialog(logInUpFrame, "Log in failed. Username or password may be incorrect.");
                    }
                }
            }
        });

    }

    public static String getDataByPrimaryKey(String primaryKey) {
        String URL = "jdbc:mysql://localhost:3306/project2";
        String USER = "root";
        String PASSWORD = "layanasdf";

        String query = "SELECT * FROM users WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, primaryKey); // تعيين قيمة المفتاح الأساسي

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String name = resultSet.getString("role");
                return name;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String getDataByPrimaryKey2(String primaryKey) {
        String URL = "jdbc:mysql://localhost:3306/project2";
        String USER = "root";
        String PASSWORD = "layanasdf";

        String query = "SELECT * FROM consultative WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, primaryKey); // تعيين قيمة المفتاح الأساسي

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String name = resultSet.getString("role");
                return name;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package pack305;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import javax.imageio.ImageIO;
//
//public class Welcome extends JFrame {
//
//    // Main method to start the consultation system
//    public void Start() {
//        // Create a new JFrame for the welcome window
//        JFrame myFrame = new JFrame("Consultation System");
//
//        // Set size of JFrame
//        myFrame.setSize(900, 700);
//
//        // Set default close operation to close the application
//        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // create a JLabel for the title under the logo
//        JLabel titleLabel = new JLabel("Welcome to the Consultation System", SwingConstants.CENTER);
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // customize the font and size
//        titleLabel.setBounds(0, 420, myFrame.getWidth(), 50); // position the title below the logo and center it
//
//        // Add the title to the frame
//        myFrame.getContentPane().add(titleLabel);
//
//        // Create a JLabel with an ImageIcon for the background
//        ImageIcon backgroundImage = new ImageIcon(Welcome.class.getResource("/image/background2.png"));
//        JLabel backgroundLabel = new JLabel(backgroundImage);
//        // Set bounds of backgroundLabel to cover the entire JFrame
//        backgroundLabel.setBounds(0, -40, myFrame.getWidth(), myFrame.getHeight());
//
//        // Set layout of JFrame to null to allow manual positioning of components
//        myFrame.setLayout(null);
//
//        // Add backgroundLabel to the content pane of the JFrame
//        myFrame.getContentPane().add(backgroundLabel);
//
//        // Create login button
//        JButton loginButton = new JButton("Login");
//        loginButton.setBounds(275, 500, 150, 45); // set button size and position
//        loginButton.setBackground(Color.decode("#74b4c4")); // change the button background color
//
//        // Create sign-up button
//        JButton signUpButton = new JButton("Sign Up");
//        signUpButton.setBounds(475, 500, 150, 45); // set button size and position
//        signUpButton.setBackground(Color.decode("#74b4c4")); // change the button background color
//
//        // Make the JFrame visible
//        myFrame.setVisible(true);
//
//        // Add action listeners for button events
//        loginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // open the login frame when the login button is pressed
//                openloginFrame();
//            }
//        });
//
//        signUpButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // open the sign-up frame when the sign-up button is pressed
//                openSignUpFrame();
//                dispose(); // close the welcome page when opening the login window
//            }
//        });
//
//        // Add the login button to the content pane of the JFrame
//        myFrame.getContentPane().add(loginButton);
//
//        // Ensure the button is in front of the background label
//        myFrame.getContentPane().setComponentZOrder(loginButton, 0);
//
//        // Add the sign-up button to the content pane of the JFrame
//        myFrame.getContentPane().add(signUpButton);
//
//        // Ensure the button is in front of the background label
//        myFrame.getContentPane().setComponentZOrder(signUpButton, 0);
//    }
//
//    // Method to display a frame for role selection
//    public static void RoleSelectionFrame() {
//        // create a JFrame for selecting roles
//        JFrame roleFrame = new JFrame("Select Role");
//        roleFrame.setSize(900, 700); // set frame size
//        roleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close the application on exit
//        roleFrame.setLayout(null); // manual layout for components
//
//        // create user role button
//        JButton userButton = new JButton("User");
//        userButton.setBounds(250, 300, 150, 45); // set button position
//        userButton.setFont(new Font("Arial", Font.BOLD, 18)); // enlarge the font size
//        roleFrame.add(userButton); // add the user button to the frame
//
//        // create consultant role button
//        JButton consultantButton = new JButton("Consultant");
//        consultantButton.setBounds(500, 300, 150, 45); // set button position
//        consultantButton.setFont(new Font("Arial", Font.BOLD, 18)); // enlarge the font size
//        roleFrame.add(consultantButton); // add the consultant button to the frame
//
//        // make the frame visible
//        roleFrame.setVisible(true);
//    }
//
//    // Method to open the sign-up frame
//    public void openSignUpFrame() {
//        // create a new frame for sign-up
//        JFrame signUpFrame = new JFrame("Sign Up");
//        signUpFrame.setSize(900, 700); // set frame size
//        signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close only the current frame
//
//        // Create a JLabel with an ImageIcon for the background
//        ImageIcon signUpBackground = new ImageIcon(Welcome.class.getResource("/image/signup1.png"));
//        JLabel backgroundLabel = new JLabel(signUpBackground);
//        backgroundLabel.setBounds(0, 0, signUpFrame.getWidth(), signUpFrame.getHeight()); // set background size
//
//        // set layout to null for manual positioning
//        signUpFrame.setLayout(null);
//
//        // add components to the sign-up window (e.g., labels and text fields)
//        JLabel userLabel = new JLabel("Username:");
//        userLabel.setBounds(500, 250, 100, 25); // set position
//        userLabel.setFont(new Font("Arial", Font.BOLD, 18)); // set font size
//        signUpFrame.add(userLabel);
//
//        JTextField usernameField = new JTextField();
//        usernameField.setBounds(600, 250, 250, 35); // set text field size and position
//        signUpFrame.add(usernameField);
//
//        // create the email label and text field
//        JLabel emailLabel = new JLabel("Email:");
//        emailLabel.setBounds(500, 300, 100, 25); // set label position
//        emailLabel.setFont(new Font("Arial", Font.BOLD, 18)); // set font size
//        signUpFrame.add(emailLabel);
//
//        JTextField emailField = new JTextField();
//        emailField.setBounds(600, 300, 250, 35); // set text field size and position
//        signUpFrame.add(emailField);
//
//        JLabel passwordLabel = new JLabel("Password:");
//        passwordLabel.setBounds(500, 350, 100, 25); // set label position
//        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18)); // set font size
//        signUpFrame.add(passwordLabel);
//
//        JPasswordField passwordField = new JPasswordField();
//        passwordField.setBounds(600, 350, 250, 35); // set text field size and position
//        signUpFrame.add(passwordField);
//
//        JLabel roleLabel = new JLabel("Role:");
//        roleLabel.setBounds(500, 400, 100, 25);
//        roleLabel.setFont(new Font("Arial", Font.BOLD, 18));
//        signUpFrame.add(roleLabel);
//
//        JTextField roleField = new JTextField();
//        roleField.setBounds(600, 400, 250, 35);
//        signUpFrame.add(roleField);
//
//        // create a sign-up button
//        JButton createAccountButton = new JButton("Sign Up");
//        createAccountButton.setBounds(650, 450, 150, 45); // set button size and position
//        signUpFrame.add(createAccountButton);
//        createAccountButton.setBackground(Color.decode("#74b4c4")); // set button background color
//
//        // add the background image to the sign-up window
//        signUpFrame.getContentPane().add(backgroundLabel);
//
//        // make the sign-up window visible
//        signUpFrame.setVisible(true);
//
////open home page when entering button
//        createAccountButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String username = usernameField.getText();
//                String email = emailField.getText();
//                String password = new String(passwordField.getPassword());
//                String role = roleField.getText();
//                if (!email.contains("@") || !email.endsWith(".")) {
//                    JOptionPane.showMessageDialog(signUpFrame, "Invalid email format. ");
//                    return; // إذا كان التنسيق غير صحيح، نعرض الرسالة ونوقف التنفيذ
//                }
//                if (registerUser(username, email, password, role)) {
//                    JOptionPane.showMessageDialog(signUpFrame, "Sign-up successful!");
//                    Home home = new Home();
//                    home.setVisible(true);
//                    setVisible(false);
//                    signUpFrame.dispose();
//
//                } else {
//                    JOptionPane.showMessageDialog(signUpFrame, "Sign-up failed. Username may already exist.");
//                }
//
//            }
//
//        });
//    }
//
//    // Database method for registering a new user
//    private boolean registerUser(String username, String email, String password, String role) {
//        String connectionURL = "jdbc:mysql://localhost:3306/project2"; // ضع اسم قاعدة البيانات هنا بعد localhost:3306
//        try (Connection con = DriverManager.getConnection(connectionURL, "root", "1234");
//                PreparedStatement ps = con.prepareStatement("INSERT INTO USERS (Username, Email, Password, Role) VALUES (?, ?, ?, ?)")) {
//
//            // إعداد القيم في الاستعلام
//            ps.setString(1, username);
//            ps.setString(2, email);
//            ps.setString(3, password);
//            ps.setString(4, role);
//
//            // تنفيذ الاستعلام
//            ps.executeUpdate();
//
//            // إذا نجح الإدخال، أعد true
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // في حال الفشل، أعد false
//            return false;
//        }
//    }
//
//    public boolean authenticateUser(String username, String password) {
//        String connectionURL = "jdbc:mysql://localhost:3306/project2";
//        try (Connection con = DriverManager.getConnection(connectionURL, "root", "1234")) {
//            String query = "SELECT * FROM USERS WHERE Username = ? AND Password = ?";
//            PreparedStatement ps = con.prepareStatement(query);
//            ps.setString(1, username);
//            ps.setString(2, password);
//            ResultSet rs = ps.executeQuery();
//            return rs.next(); // Returns true if user exists
//        } catch (SQLException e) {
//
//            return false;
//        }
//    }
//
//    // Method to open the login frame
//    public void openloginFrame() {
//        // create a new frame for login
//        JFrame logInUpFrame = new JFrame("Log in");
//        logInUpFrame.setSize(900, 700); // set frame size
//        logInUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close only the current frame
//
//        // Create a JLabel with an ImageIcon for the background
//        ImageIcon loginBackground = new ImageIcon(Welcome.class.getResource("/image/login1.png"));
//        JLabel backgroundLabel = new JLabel(loginBackground);
//        backgroundLabel.setBounds(0, 0, logInUpFrame.getWidth(), logInUpFrame.getHeight()); // set background size
//
//        // set layout to null for manual positioning
//        logInUpFrame.setLayout(null);
//
//        // add components to the login window (e.g., labels and text fields)
//        JLabel userLabel = new JLabel("Username:");
//        userLabel.setBounds(500, 250, 100, 25); // set label position
//        userLabel.setFont(new Font("Arial", Font.BOLD, 18)); // set font size
//        logInUpFrame.add(userLabel);
//
//        JTextField usernameField = new JTextField();
//        usernameField.setBounds(600, 250, 250, 35); // set text field size and position
//        logInUpFrame.add(usernameField);
//
//        // create the password label and text field
//        JLabel passwordLabel = new JLabel("Password:");
//        passwordLabel.setBounds(500, 300, 100, 25); // set label position
//        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18)); // set font size
//        logInUpFrame.add(passwordLabel);
//
//        JPasswordField passwordField = new JPasswordField();
//        passwordField.setBounds(600, 300, 250, 35); // set text field size and position
//        logInUpFrame.add(passwordField);
//
//        // create a login button
//        JButton createAccountButton = new JButton("Login");
//        createAccountButton.setBounds(650, 420, 150, 45); // set button size and position
//        logInUpFrame.add(createAccountButton);
//        createAccountButton.setBackground(Color.decode("#74b4c4")); // set button background color
//
//        // add the background image to the login window
//        logInUpFrame.getContentPane().add(backgroundLabel);
//
//        // make the login window visible
//        logInUpFrame.setVisible(true);
//
//        createAccountButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String username = usernameField.getText();
//                String password = new String(passwordField.getPassword());
//
//                if (authenticateUser(username, password)) {
//                    Home home = new Home();
//                    home.setVisible(true);
//                    setVisible(false);
//                    Welcome.this.dispose(); // Correctly dispose the Welcome frame
//                } else {
//                    JOptionPane.showMessageDialog(logInUpFrame, "log in failed. Username may already exist.");
//                }
//
//            }
//
//        });
//    }
//
//}
