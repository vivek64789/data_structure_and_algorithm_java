package frontend;

import backend.GraphVisualization;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Login {
    // creating fonts objects
    Font font20 = new Font("Poppins", Font.BOLD, 20);
    Font font12 = new Font("Poppins", Font.BOLD, 12);
    Font font14 = new Font("Poppins", Font.BOLD, 14);
    JFrame mainLoginFrame = new JFrame();
    JPanel loginPanel = new JPanel();
    JLabel loginTitle, username, password = new JLabel();
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton, registerButton = new JButton();


    public Login() {
        mainLoginFrame.getContentPane().setBackground(Color.white);
        mainLoginFrame.setVisible(true);
        mainLoginFrame.setSize(500, 500);
        mainLoginFrame.setLocation(400, 150);
        mainLoginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainLoginFrame.setResizable(false);
        loginFrame();


    }

    public void loginFrame() {

        createLoginPanel();

        loginTitle = new JLabel("Login Here");
        loginPanel.add(loginTitle);
        loginTitle.setFont(font20);
        loginTitle.setBounds(180, 20, 150, 40);
        loginTitle.setForeground(Color.DARK_GRAY);

        username = new JLabel("Username");
        loginPanel.add(username);
        username.setFont(font14);
        username.setBounds(70, 100, 150, 30);
        username.setForeground(Color.DARK_GRAY);

        usernameField = new JTextField();
        loginPanel.add(usernameField);
        usernameField.setFont(font12);
        usernameField.setBounds(70, 130, 350, 30);
        usernameField.setForeground(Color.DARK_GRAY);


        password = new JLabel("Password");
        loginPanel.add(password);
        password.setFont(font14);
        password.setBounds(70, 170, 150, 30);
        password.setForeground(Color.DARK_GRAY);

        passwordField = new JPasswordField();
        loginPanel.add(passwordField);
        passwordField.setFont(font12);
        passwordField.setBounds(70, 200, 350, 30);
        passwordField.setForeground(Color.DARK_GRAY);

        loginButton = new JButton("Login");
        loginPanel.add(loginButton);
        loginButton.setFont(font12);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(Color.DARK_GRAY);
        loginButton.setForeground(Color.white);
        loginButton.setBounds(100, 250, 100, 30);

        registerButton = new JButton("Register");
        loginPanel.add(registerButton);
        registerButton.setFont(font12);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.setBackground(Color.DARK_GRAY);
        registerButton.setForeground(Color.white);
        registerButton.setBounds(300, 250, 100, 30);


        registerButton.addActionListener(e -> {
            new Register();
            mainLoginFrame.dispose();
        });

        loginButton.addActionListener(e -> {

            String getUsername = usernameField.getText();
            String getPassword = passwordField.getText();
            model.Login loginObj = new model.Login();
            boolean result = loginObj.checkLogin(getUsername,getPassword);
            if(result){
                try {
                    new AdminDashboard();
                    new backend.GraphVisualization();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                mainLoginFrame.dispose();
            }
            else {
                JOptionPane.showMessageDialog(mainLoginFrame, "Invalid Username or Password", "Invalid", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void createLoginPanel() {
        loginPanel = new JPanel();
        mainLoginFrame.add(loginPanel);
        loginPanel.setBackground(Color.white);
        loginPanel.setBounds(0, 0, 500, 500);
        loginPanel.setLayout(null);
        loginPanel.setVisible(true);

    }

}