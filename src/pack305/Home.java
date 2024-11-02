/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack305;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author lyana
 */

// class for displaying images
class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel() throws IOException {
        image = ImageIO.read(getClass().getResource("/image/bground2.png"));
    }
}

// class for the messaging window
class Messages extends JFrame {

    public Messages() throws IOException {

        // create the main window for the messaging interface
        setTitle("messaging window");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        // customize fonts for buttons and other components
        Font newFont = new Font("Arial", Font.BOLD, 16);
        Font newFont2 = new Font("Arial", Font.BOLD, 12);

        // add background image to the window
        ImageIcon backgroundImage = new ImageIcon(Messages.class.getResource("/image/bground1.png"));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, -40, getWidth(), getHeight());

        // top bar for the messaging title
        JLabel bar = new JLabel("Messages");
        bar.setFont(new Font("Arial", Font.BOLD, 25));
        bar.setBounds(89, 0, 297, 90);

        // create the left-side panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#74b4c4"));
        panel.setLayout(null);
        panel.setBounds(0, 0, 298, 661);

        // chat panel
        JLabel chat = new JLabel();
        chat.setBounds(300, 520, 590, 140);
        chat.setLayout(null);

        // buttons for message categories
        JButton allMsg = new JButton("All msg");
        allMsg.setBackground(Color.decode("#FFFFFF"));
        allMsg.setBorder(BorderFactory.createLineBorder(Color.black));
        allMsg.setFont(newFont2);
        allMsg.setBounds(0, 90, 100, 30);

        JButton ongoingMsg = new JButton("Ongoing");
        ongoingMsg.setBackground(Color.decode("#FFFFFF"));
        ongoingMsg.setBorder(BorderFactory.createLineBorder(Color.black));
        ongoingMsg.setFont(newFont2);
        ongoingMsg.setBounds(99, 90, 100, 30);

        JButton upcomingMsg = new JButton("Upcoming");
        upcomingMsg.setBackground(Color.decode("#FFFFFF"));
        upcomingMsg.setBorder(BorderFactory.createLineBorder(Color.black));
        upcomingMsg.setFont(newFont2);
        upcomingMsg.setBounds(198, 90, 100, 30);

        // label to display the username
        JLabel userLabel = new JLabel("");
        userLabel.setFont(newFont);
        userLabel.setBounds(300, 60, 581, 30);

        // chat display area
        JTextArea chatArea = new JTextArea();
        chatArea.setBorder(BorderFactory.createLineBorder(Color.black));
        chatArea.setBounds(300, 90, 590, 428);
        chatArea.setEditable(false);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(13, 10, 460, 120);
        textArea.setBorder(BorderFactory.createLineBorder(Color.black));

        JButton sendMsg = new JButton("SEND");
        sendMsg.setBackground(Color.decode("#74b4c4"));
        sendMsg.setBorder(BorderFactory.createLineBorder(Color.black));
        sendMsg.setBounds(485, 10, 90, 120);

        // hide chat areas by default until a user is selected
        chatArea.setVisible(false);
        textArea.setVisible(false);
        sendMsg.setVisible(false);

        // list of users
        ImagePanel userList = new ImagePanel();
        userList.setLayout(new BoxLayout(userList, BoxLayout.Y_AXIS));
        userList.setBounds(0, 2, 297, 540);

        // add users with their statuses
        String[][] users = {
            {"Ahmed Counselor", "ongoing"},
            {"Rawan Counselor", "upcoming"},
            {"Marya Counselor", "ended"}
        };

        for (int i = 0; i < users.length; i++) {
            String userName = users[i][0];
            String userStatus = users[i][1];

            // status icon
            BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = image.createGraphics();
            if (userStatus.equals("ongoing")) {
                g2.setColor(Color.GREEN);
            } else if (userStatus.equals("upcoming")) {
                g2.setColor(Color.ORANGE);
            } else {
                g2.setColor(Color.GRAY);
            }
            g2.fillOval(0, 0, 10, 10); // draw the status circle
            g2.dispose();
            Icon statusIcon = new ImageIcon(image);

            // button for each user
            JButton userButton = new JButton(userName, statusIcon);
            userButton.setFont(newFont);
            userButton.setBackground(Color.decode("#E2F2F5"));
            userButton.setBorder(BorderFactory.createLineBorder(Color.black));
            userButton.setHorizontalAlignment(SwingConstants.LEFT);
            userButton.setMaximumSize(new Dimension(280, 30));

            userButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userLabel.setText(userName);
                    chatArea.setVisible(true);
                    textArea.setVisible(true);
                    sendMsg.setVisible(true);
                }
            });

            userList.add(userButton);
            userList.add(Box.createVerticalStrut(1)); // add spacing between buttons
        }

                
        JButton backButton = new JButton("← Back");
        backButton.setBounds(3, 3, 80, 45); // set button size and position
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
     
        add(backButton);
        backButton.setBackground(Color.decode("#74b4c4")); // set button background color

       
// Add action listener for back button event
backButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        Home home = new Home();
                home.setVisible(true);
               
    }
    
});

        // add scroll pane for user list
        JScrollPane listScrollPane = new JScrollPane(userList);
        listScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        listScrollPane.setBounds(0, 120, 297, 540);

        // add components to the window
        add(bar);
        add(panel);
        add(chat);
        add(chatArea);
        add(userLabel);
        panel.add(allMsg);
        panel.add(ongoingMsg);
        panel.add(upcomingMsg);
        panel.add(listScrollPane);
        chat.add(textArea);
        chat.add(sendMsg);
        add(backgroundLabel);

        
        

        // make the window visible
        setVisible(true);
    }
}

// class for the home of user page
public class Home extends JFrame {

    public void HomeUser() {

        // create the main window for the home page
        setTitle("home");
        setSize(900, 700);  // set the window size
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // set close operation for the exit button
        setLayout(null);

        // add background image
        ImageIcon backgroundImage = new ImageIcon(Home.class.getResource("/image/bground2.png"));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, -40, getWidth(), getHeight());

        // home page buttons
        JButton consultationButton = new JButton("Consultation");
        JButton msgButton = new JButton("Messages");

    
     
        //invoke consultation page when entering button
                consultationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                  AdvisorsGUI consultationPage=new AdvisorsGUI();
              
                dispose(); // close the home page when opening the messages window
            }
        });
        
        
        msgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Messages();
                } catch (IOException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose(); // close the home page when opening the messages window
            }
        });
        

        // create the profile panel
        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(Color.decode("#74b4c4"));
        profilePanel.setBounds(12, 10, 870, 150);
        profilePanel.setLayout(new BorderLayout());

        // placeholder for profile image
        ImageIcon originalIcon = new ImageIcon(Home.class.getResource("/image/profile.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(150, 130, Image.SCALE_SMOOTH);
        JLabel img = new JLabel(new ImageIcon(scaledImage));
        img.setBounds(10, 10, 150, 130);
        profilePanel.add(img, BorderLayout.WEST);

        // display username
        JLabel username = new JLabel("User", SwingConstants.LEFT);
        username.setBounds(200, 50, 100, 45);
        username.setFont(new Font("Arial", Font.BOLD, 24));
        profilePanel.add(username);

        // add the profile panel to the main window
        add(profilePanel);
        
  

        // set background color for the consultation button using a custom color code
        consultationButton.setBackground(Color.decode("#74b4c4"));

        // create a new font for the buttons with arial font bold style and size 25
        Font newFont = new Font("Arial", Font.BOLD, 25);

        // apply the custom font to the consultation button
        consultationButton.setFont(newFont);

        // apply the same custom font to the messages button
        msgButton.setFont(newFont);

        // set background color for the messages button using the same custom color code
        msgButton.setBackground(Color.decode("#74b4c4"));

        // set the position and size of the consultation button (x y width height)
        consultationButton.setBounds(170, 300, 250, 120);

        // set the position and size of the messages button (x y width height)
        msgButton.setBounds(470, 300, 250, 120);

        // add a black border around the messages button with a thickness of 2 pixels
        msgButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        // add a black border around the consultation button with a thickness of 2 pixels
        consultationButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        // add the consultation button to the main window
        add(consultationButton);

        // add the messages button to the main window
        add(msgButton);

        // add the background image label to the main window to serve as the background
        add(backgroundLabel);
    }
    
    // class for the home page


    public void Homeconsulation() {

        // create the main window for the home page
        setTitle("home");
        setSize(900, 700);  // set the window size
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // set close operation for the exit button
        setLayout(null);

        // add background image
        ImageIcon backgroundImage = new ImageIcon(Home.class.getResource("/image/bground2.png"));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, -40, getWidth(), getHeight());

        // home page buttons
        JButton consultationButton = new JButton("Scheduling");
        JButton msgButton = new JButton("Messages");

    
     
        //invoke consultation page when entering button
                consultationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                  AdvisorsGUI consultationPage=new AdvisorsGUI();
              
                dispose(); // close the home page when opening the messages window
            }
        });
        
        
        msgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Messages();
                } catch (IOException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose(); // close the home page when opening the messages window
            }
        });
        

        // create the profile panel
        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(Color.decode("#74b4c4"));
        profilePanel.setBounds(12, 10, 870, 150);
        profilePanel.setLayout(new BorderLayout());

        // placeholder for profile image
        ImageIcon originalIcon = new ImageIcon(Home.class.getResource("/image/profile.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(150, 130, Image.SCALE_SMOOTH);
        JLabel img = new JLabel(new ImageIcon(scaledImage));
        img.setBounds(10, 10, 150, 130);
        profilePanel.add(img, BorderLayout.WEST);

        // display username
        JLabel username = new JLabel("Consultative", SwingConstants.LEFT);
        username.setBounds(200, 50, 100, 45);
        username.setFont(new Font("Arial", Font.BOLD, 24));
        profilePanel.add(username);

        // add the profile panel to the main window
        add(profilePanel);
        
  

        // set background color for the consultation button using a custom color code
        consultationButton.setBackground(Color.decode("#74b4c4"));

        // create a new font for the buttons with arial font bold style and size 25
        Font newFont = new Font("Arial", Font.BOLD, 25);

        // apply the custom font to the consultation button
        consultationButton.setFont(newFont);

        // apply the same custom font to the messages button
        msgButton.setFont(newFont);

        // set background color for the messages button using the same custom color code
        msgButton.setBackground(Color.decode("#74b4c4"));

        // set the position and size of the consultation button (x y width height)
        consultationButton.setBounds(170, 300, 250, 120);

        // set the position and size of the messages button (x y width height)
        msgButton.setBounds(470, 300, 250, 120);

        // add a black border around the messages button with a thickness of 2 pixels
        msgButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        // add a black border around the consultation button with a thickness of 2 pixels
        consultationButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        // add the consultation button to the main window
        add(consultationButton);

        // add the messages button to the main window
        add(msgButton);

        // add the background image label to the main window to serve as the background
        add(backgroundLabel);
    }
}

