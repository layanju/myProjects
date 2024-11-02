/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack305;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;

public class Welcome extends JFrame {

    // Main method to start the consultation system
    public void Start() {
        // Create a new JFrame for the welcome window
        JFrame myFrame = new JFrame("Consultation System");

        // Set size of JFrame
        myFrame.setSize(900, 700);

        // Set default close operation to close the application
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a JLabel for the title under the logo
        JLabel titleLabel = new JLabel("Welcome to the Consultation System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // customize the font and size
        titleLabel.setBounds(0, 420, myFrame.getWidth(), 50); // position the title below the logo and center it

        // Add the title to the frame
        myFrame.getContentPane().add(titleLabel);

        // Create a JLabel with an ImageIcon for the background
        ImageIcon backgroundImage = new ImageIcon(Welcome.class.getResource("/image/background2.png"));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        // Set bounds of backgroundLabel to cover the entire JFrame
        backgroundLabel.setBounds(0, -40, myFrame.getWidth(), myFrame.getHeight());

        // Set layout of JFrame to null to allow manual positioning of components
        myFrame.setLayout(null);

        // Add backgroundLabel to the content pane of the JFrame
        myFrame.getContentPane().add(backgroundLabel);

        // Create login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(275, 500, 150, 45); // set button size and position
        loginButton.setBackground(Color.decode("#74b4c4")); // change the button background color

        // Create sign-up button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(475, 500, 150, 45); // set button size and position
        signUpButton.setBackground(Color.decode("#74b4c4")); // change the button background color

        // Make the JFrame visible
        myFrame.setVisible(true);

        // Add action listeners for button events
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // open the login frame when the login button is pressed
                openloginFrame();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // open the sign-up frame when the sign-up button is pressed
                openSignUpFrame();
                dispose(); // close the welcome page when opening the login window
            }
        });

        // Add the login button to the content pane of the JFrame
        myFrame.getContentPane().add(loginButton);

        // Ensure the button is in front of the background label
        myFrame.getContentPane().setComponentZOrder(loginButton, 0);

        // Add the sign-up button to the content pane of the JFrame
        myFrame.getContentPane().add(signUpButton);

        // Ensure the button is in front of the background label
        myFrame.getContentPane().setComponentZOrder(signUpButton, 0);
    }

    // Method to display a frame for role selection
    public static void RoleSelectionFrame() {
        // create a JFrame for selecting roles
        JFrame roleFrame = new JFrame("Select Role");
        roleFrame.setSize(900, 700); // set frame size
        roleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close the application on exit
        roleFrame.setLayout(null); // manual layout for components

        // create user role button
        JButton userButton = new JButton("User");
        userButton.setBounds(250, 300, 150, 45); // set button position
        userButton.setFont(new Font("Arial", Font.BOLD, 18)); // enlarge the font size
        roleFrame.add(userButton); // add the user button to the frame

        // create consultant role button
        JButton consultantButton = new JButton("Consultant");
        consultantButton.setBounds(500, 300, 150, 45); // set button position
        consultantButton.setFont(new Font("Arial", Font.BOLD, 18)); // enlarge the font size
        roleFrame.add(consultantButton); // add the consultant button to the frame

        // make the frame visible
        roleFrame.setVisible(true);
    }

    // Method to open the sign-up frame
    public void openSignUpFrame() {
        // create a new frame for sign-up
        JFrame signUpFrame = new JFrame("Sign Up");
        signUpFrame.setSize(900, 700); // set frame size
        signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close only the current frame

        // Create a JLabel with an ImageIcon for the background
        ImageIcon signUpBackground = new ImageIcon(Welcome.class.getResource("/image/signup1.png"));
        JLabel backgroundLabel = new JLabel(signUpBackground);
        backgroundLabel.setBounds(0, 0, signUpFrame.getWidth(), signUpFrame.getHeight()); // set background size

        // set layout to null for manual positioning
        signUpFrame.setLayout(null);

        // add components to the sign-up window (e.g., labels and text fields)
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(500, 250, 100, 25); // set position
        userLabel.setFont(new Font("Arial", Font.BOLD, 18)); // set font size
        signUpFrame.add(userLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(600, 250, 250, 35); // set text field size and position
        signUpFrame.add(usernameField);

        // create the email label and text field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(500, 300, 100, 25); // set label position
        emailLabel.setFont(new Font("Arial", Font.BOLD, 18)); // set font size
        signUpFrame.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(600, 300, 250, 35); // set text field size and position
        signUpFrame.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(500, 350, 100, 25); // set label position
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18)); // set font size
        signUpFrame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(600, 350, 250, 35); // set text field size and position
        signUpFrame.add(passwordField);

        // create a sign-up button
        JButton createAccountButton = new JButton("Sign Up");
        createAccountButton.setBounds(650, 420, 150, 45); // set button size and position
        signUpFrame.add(createAccountButton);
        createAccountButton.setBackground(Color.decode("#74b4c4")); // set button background color

        // add the background image to the sign-up window
        signUpFrame.getContentPane().add(backgroundLabel);
        
        
        // make the sign-up window visible
        signUpFrame.setVisible(true);
//open home page when entering button
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

        
       
                Home home = new Home();
//                home.Homeconsulation();
                home.HomeUser();
                home.setVisible(true);
                setVisible(false);
                dispose(); // close the account page when opening the home window
            }

        });
         

    }
    
        

    

    // Method to open the login frame
    public void openloginFrame() {
        // create a new frame for login
        JFrame logInUpFrame = new JFrame("Log in");
        logInUpFrame.setSize(900, 700); // set frame size
        logInUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close only the current frame

        // Create a JLabel with an ImageIcon for the background
        ImageIcon loginBackground = new ImageIcon(Welcome.class.getResource("/image/login1.png"));
        JLabel backgroundLabel = new JLabel(loginBackground);
        backgroundLabel.setBounds(0, 0, logInUpFrame.getWidth(), logInUpFrame.getHeight()); // set background size

        // set layout to null for manual positioning
        logInUpFrame.setLayout(null);

        // add components to the login window (e.g., labels and text fields)
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(500, 250, 100, 25); // set label position
        userLabel.setFont(new Font("Arial", Font.BOLD, 18)); // set font size
        logInUpFrame.add(userLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(600, 250, 250, 35); // set text field size and position
        logInUpFrame.add(usernameField);

        // create the password label and text field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(500, 300, 100, 25); // set label position
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18)); // set font size
        logInUpFrame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(600, 300, 250, 35); // set text field size and position
        logInUpFrame.add(passwordField);

        // create a login button
        JButton createAccountButton = new JButton("Login");
        createAccountButton.setBounds(650, 420, 150, 45); // set button size and position
        logInUpFrame.add(createAccountButton);
        createAccountButton.setBackground(Color.decode("#74b4c4")); // set button background color
        

        // add the background image to the login window
        logInUpFrame.getContentPane().add(backgroundLabel);
  
       
        // make the login window visible
        logInUpFrame.setVisible(true);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Home home = new Home();
                home.setVisible(true);
                Welcome.this.dispose(); // Correctly dispose the Welcome frame
            }

        });
        
        
        
        
        
    }

}
