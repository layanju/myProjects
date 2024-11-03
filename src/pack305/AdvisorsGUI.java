package pack305;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class AdvisorsGUI {

    private JFrame frame;
    private JButton advisor1Button;
    private JButton advisor2Button;
    private JButton advisor3Button;
    private JButton advisor4Button;
    Color buttonColor = new Color(116, 180, 196); // #74b4c4, color for the buttons
    BufferedImage backgroundImage;
    BufferedImage consultationBackgroundImage; // background image for the consultation window

    public AdvisorsGUI() {
        try {
            // load the main background image from resources
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/image/bground1.png"));
        } catch (IOException e) {
            System.out.println("Error loading background image: " + e.getMessage());
        }
        createMainGUI(); // create the main GUI
    }

    private void createMainGUI() {
        // create the main window
        frame = new JFrame("Consultment App");
        frame.setSize(900, 700); // set window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit on close

        // create a custom panel for drawing the background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // draw the background image
                }
            }
        };
        backgroundPanel.setLayout(null); // set null layout for absolute positioning
        frame.setContentPane(backgroundPanel); // set the content pane to the custom background panel

        // main title label
        JLabel label = new JLabel("Choose a talent");
        label.setBounds(150, 50, 200, 70); // set position and size
        label.setFont(new Font("Serif", Font.BOLD, 30)); // set font
        backgroundPanel.add(label); // add label to background panel

        // create the button for drawing talent
        JButton drawingButton = new JButton("Drawing");
        drawingButton.setBounds(180, 150, 250, 100); // set button size and position
        drawingButton.setFont(new Font("Arial", Font.PLAIN, 24)); // set font
        drawingButton.setBackground(buttonColor); // set button color
        drawingButton.setToolTipText("Click to view drawing consultants."); // tooltip for button

        // load drawing image from resources
        try {
            BufferedImage drawingImage = ImageIO.read(getClass().getResourceAsStream("/image/draw.png"));
            if (drawingImage != null) {
                Image scaledImage = drawingImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // scale the image
                ImageIcon drawingIcon = new ImageIcon(scaledImage); // set image icon
                drawingButton.setIcon(drawingIcon); // add icon to button
                drawingButton.setVerticalTextPosition(SwingConstants.BOTTOM); // text below the icon
                drawingButton.setHorizontalTextPosition(SwingConstants.CENTER); // center align the text
            }
        } catch (IOException e) {
            System.out.println("Error loading drawing image: " + e.getMessage());
        }

        // add action listener to the button
        drawingButton.addActionListener((ActionEvent e) -> {
            createConsultationGUI(); // open consultation window
        });
        backgroundPanel.add(drawingButton); // add drawing button to background panel

        // create the button for music talent
        JButton musicButton = new JButton("Music");
        musicButton.setBounds(500, 150, 250, 100); // set size and position
        musicButton.setFont(new Font("Arial", Font.PLAIN, 24)); // set font
        musicButton.setBackground(buttonColor); // set background color
        musicButton.setToolTipText("Click to view music consultants."); // set tooltip

        // load music image from resources
        try {
            BufferedImage musicImage = ImageIO.read(getClass().getResourceAsStream("/image/music.png"));
            if (musicImage != null) {
                Image scaledImage = musicImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // scale the image
                ImageIcon musicIcon = new ImageIcon(scaledImage); // set image icon
                musicButton.setIcon(musicIcon); // add icon to button
                musicButton.setVerticalTextPosition(SwingConstants.BOTTOM); // text below the icon
                musicButton.setHorizontalTextPosition(SwingConstants.CENTER); // center align the text
            }
        } catch (IOException e) {
            System.out.println("Error loading music image: " + e.getMessage());
        }

        // add action listener to the music button
        musicButton.addActionListener((ActionEvent e) -> {
            createConsultationGUI(); // open consultation window
        });
        backgroundPanel.add(musicButton); // add music button to background panel

        // create the button for writing talent
        JButton writingButton = new JButton("Writing");
        writingButton.setBounds(180, 300, 250, 100); // set size and position
        writingButton.setFont(new Font("Arial", Font.PLAIN, 24)); // set font
        writingButton.setBackground(buttonColor); // set background color
        writingButton.setToolTipText("Click to view writing consultants."); // set tooltip

        // load writing image from resources
        try {
            BufferedImage writingImage = ImageIO.read(getClass().getResourceAsStream("/image/writing.png"));
            if (writingImage != null) {
                Image scaledImage = writingImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // scale the image
                ImageIcon writingIcon = new ImageIcon(scaledImage); // set image icon
                writingButton.setIcon(writingIcon); // add icon to button
                writingButton.setVerticalTextPosition(SwingConstants.BOTTOM); // text below the icon
                writingButton.setHorizontalTextPosition(SwingConstants.CENTER); // center align the text
            }
        } catch (IOException e) {
            System.out.println("Error loading writing image: " + e.getMessage());
        }

        // add action listener to the writing button
        writingButton.addActionListener((ActionEvent e) -> {
            createConsultationGUI(); // open consultation window
        });
        backgroundPanel.add(writingButton); // add writing button to background panel

        // create the button for photography talent
        JButton photographyButton = new JButton("Photography");
        photographyButton.setBounds(500, 300, 250, 100); // set size and position
        photographyButton.setFont(new Font("Arial", Font.PLAIN, 24)); // set font
        photographyButton.setBackground(buttonColor); // set background color
        photographyButton.setToolTipText("Click to view photography consultants."); // set tooltip

        // load photography image from resources
        try {
            BufferedImage photographyImage = ImageIO.read(getClass().getResourceAsStream("/image/pic.png"));
            if (photographyImage != null) {
                Image scaledImage = photographyImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // scale the image
                ImageIcon photographyIcon = new ImageIcon(scaledImage); // set image icon
                photographyButton.setIcon(photographyIcon); // add icon to button
                photographyButton.setVerticalTextPosition(SwingConstants.BOTTOM); // text below the icon
                photographyButton.setHorizontalTextPosition(SwingConstants.CENTER); // center align the text
            }
        } catch (IOException e) {
            System.out.println("Error loading photography image: " + e.getMessage());
        }

        // add action listener to the photography button
        photographyButton.addActionListener((ActionEvent e) -> {
            createConsultationGUI(); // open consultation window
        });
        backgroundPanel.add(photographyButton); // add photography button to background panel

        // make the main window visible
        frame.setVisible(true);
    }

    // function to display the consultation window with a new background
    public void createConsultationGUI() {
        try {
            JFrame frame = new JFrame("List of Consultants"); // create new frame for consultants
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set close operation
            // load consultation window background image from resources
            ImageIcon background = new ImageIcon(getClass().getResource("/image/bground1.png"));

            JLabel backgroundLabel = new JLabel(background); // create a label for the background
            backgroundLabel.setBounds(0, -40, 900, 700); // set background size

            // create a custom panel to draw the consultation background
            JPanel backgroundPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (consultationBackgroundImage != null) {
                        g.drawImage(consultationBackgroundImage, 0, 0, getWidth(), getHeight(), this); // draw the consultation background
                    }
                }
            };
            backgroundPanel.setLayout(new BorderLayout()); // set layout for the background panel
            frame.setContentPane(backgroundPanel); // set the content pane for the consultation window

            frame.setPreferredSize(new Dimension(900, 700)); // set preferred size
            frame.pack(); // adjust window size

            // panel for the title
            JPanel titlePanel = new JPanel();
            titlePanel.setLayout(new FlowLayout()); // set layout for title panel
            JLabel titleLabel = new JLabel("Consultants"); // create title label
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // set font for title
            titlePanel.setOpaque(false); // make title panel transparent to show background
            titlePanel.add(titleLabel); // add title label to title panel
            backgroundPanel.add(titlePanel, BorderLayout.PAGE_START); // add title panel to background panel

            // panel to hold the advisors/consultants
            JPanel advisorsPanel = new JPanel();
            advisorsPanel.setLayout(new BoxLayout(advisorsPanel, BoxLayout.Y_AXIS)); // set vertical layout for advisors
            advisorsPanel.setOpaque(false); // make advisors panel transparent

            img(advisorsPanel);

            // add advisors to the panel
            // add a scroll pane for advisors panel
            JScrollPane scrollPane = new JScrollPane(advisorsPanel);
            scrollPane.setOpaque(false); // make scroll pane transparent
            scrollPane.getViewport().setOpaque(false); // make the viewport of the scroll pane transparent
            backgroundPanel.add(scrollPane, BorderLayout.CENTER); // add the scroll pane to the background panel

            frame.setVisible(true); // make the consultation window visible
        } catch (SQLException ex) {
            Logger.getLogger(AdvisorsGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void img(JPanel advisorsPanel) throws SQLException {

        String ConnectionURL = "jdbc:mysql://localhost:3306/project2";

        Connection con = DriverManager.getConnection(ConnectionURL, "root", "layanasdf");

        Statement st = con.createStatement();

        String sql = "SELECT * FROM appointments";
        String fullName ;
        ResultSet rs = st.executeQuery(sql);
        Set<String> advisorNames = new HashSet<>();
        while (rs.next()) {

            String Fname = rs.getString("FName");
            String Lname = rs.getString("LName");
            fullName = Fname + " " + Lname;
            advisorNames.add(fullName);
          
            // add advisors to the panel
        }
        // الآن، يمكننا إضافة الأسماء الفريدة إلى اللوحة
        for (String name : advisorNames) {
            addAdvisorButton(advisorsPanel, "Counselor: " + name);
          

        }

    }

    // function to make an image circular
    private BufferedImage makeCircularImage(BufferedImage image, int size) {
        BufferedImage scaledImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = scaledImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // enable anti-aliasing
        g2.drawImage(image, 0, 0, size, size, null); // scale the image
        g2.dispose();

        BufferedImage circularImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        g2 = circularImage.createGraphics();
        g2.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, size, size)); // clip the image to a circular shape
        g2.drawImage(scaledImage, 0, 0, null); // draw the scaled image
        g2.dispose();
        return circularImage; // return the circular image
    }

    // function to add an advisor button with a circular image
    private void addAdvisorButton(JPanel panel, String fullName) {
        JButton advisorButton = new JButton(); // create a new button for the advisor
        advisorButton.setPreferredSize(new Dimension(400, 80)); // تعيين حجم ثابت للزر
        advisorButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        advisorButton.setBackground(new Color(0xE2F2F5)); // set background color
        advisorButton.setHorizontalAlignment(SwingConstants.CENTER);
        advisorButton.setText("Counselor: " + fullName); // تعيين النص ليحتوي على الاسم الكامل

        advisorButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    Booking booking = new Booking(fullName);
                } catch (SQLException ex) {
                    Logger.getLogger(AdvisorsGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        // load and set advisor image
        advisorButton.setHorizontalTextPosition(SwingConstants.RIGHT); // position text to the right of the icon
        panel.add(advisorButton); // add the advisor button to the panel
    }
}
