package frontend;

import model.Device;

import javax.swing.*;
import java.awt.*;


public class Register {

    // creating fonts objects
    Font font20 = new Font("Poppins", Font.BOLD, 20);
    Font font12 = new Font("Poppins", Font.BOLD, 12);
    JFrame mainRegisterFrame = new JFrame();
    JPanel leftPanel, rightPanel = new JPanel();
    JLabel registerTitle,registerImageLabel, fullName, username, email, password, confirmPassword = new JLabel();
    JTextField fullNameField, usernameField, emailField = new JTextField();
    JPasswordField passwordField, confirmPasswordField = new JPasswordField();
    JButton submitButton, resetButton, loginButton = new JButton();
    ImageIcon registerImageIcon = new ImageIcon();


    Register(){
        registrationFrame();
        mainRegisterFrame.getContentPane().setBackground(Color.white);
        mainRegisterFrame.setVisible(true);
        mainRegisterFrame.setSize(1000,500);
        mainRegisterFrame.setLocation(180,150);
        mainRegisterFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainRegisterFrame.setResizable(false);

    }

    public void registrationFrame(){
        rightPanelFrame();
        leftPanelFrame();

    }

    public void leftPanelFrame(){
        createLeftPanel();

        registerImageLabel = new JLabel();
        leftPanel.add(registerImageLabel);
        registerImageIcon = new ImageIcon("src/resources/images/register.jpg");
        registerImageLabel.setIcon(registerImageIcon);
        registerImageLabel.setBounds(10,10,400,400);

    }

    public void rightPanelFrame(){
        createRightPanel();

        // Register here title
        registerTitle = new JLabel("Register Here");
        rightPanel.add(registerTitle);
        registerTitle.setFont(font20);
        registerTitle.setBounds(220,20,200,40);

        // full name label and field

        fullName = new JLabel("Full Name");
        rightPanel.add(fullName);
        fullName.setFont(font12);
        fullName.setBounds(100, 70,100,30);

        fullNameField = new JTextField();
        rightPanel.add(fullNameField);
        fullNameField.setFont(font12);
        fullNameField.setBounds(100,95,350,30);

        // username label and field

        username = new JLabel("Username");
        rightPanel.add(username);
        username.setFont(font12);
        username.setBounds(100, 130,100,30);

        usernameField = new JTextField();
        rightPanel.add(usernameField);
        usernameField.setFont(font12);
        usernameField.setBounds(100,155,350,30);

        // Email label and field

        email = new JLabel("Email");
        rightPanel.add(email);
        email.setFont(font12);
        email.setBounds(100, 190,100,30);

        emailField = new JTextField();
        rightPanel.add(emailField);
        emailField.setFont(font12);
        emailField.setBounds(100,215,350,30);


        // Password label and field

        password = new JLabel("Password");
        rightPanel.add(password);
        password.setFont(font12);
        password.setBounds(100, 250,100,30);

        passwordField = new JPasswordField();
        rightPanel.add(passwordField);
        passwordField.setFont(font12);
        passwordField.setBounds(100,275,350,30);

        // Confirm Password label and field

        confirmPassword = new JLabel("Confirm Password");
        rightPanel.add(confirmPassword);
        confirmPassword.setFont(font12);
        confirmPassword.setBounds(100, 310,150,30);

        confirmPasswordField = new JPasswordField();
        rightPanel.add(confirmPasswordField);
        confirmPasswordField.setFont(font12);
        confirmPasswordField.setBounds(100,335,350,30);

        // submit button
        submitButton = new JButton("Submit");
        rightPanel.add(submitButton);
        submitButton.setFont(font12);
        submitButton.setBackground(Color.DARK_GRAY);
        submitButton.setForeground(Color.white);
        submitButton.setBounds(105,390, 100, 30);
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Reset button
        resetButton = new JButton("Reset");
        rightPanel.add(resetButton);
        resetButton.setFont(font12);
        resetButton.setBackground(Color.DARK_GRAY);
        resetButton.setForeground(Color.white);
        resetButton.setBounds(225,390, 100, 30);
        resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        // Login button
        loginButton = new JButton("Login");
        rightPanel.add(loginButton);
        loginButton.setFont(font12);
        loginButton.setBackground(Color.DARK_GRAY);
        loginButton.setForeground(Color.white);
        loginButton.setBounds(345,390, 100, 30);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // event handling
        submitButton.addActionListener(e -> {
            String getFullName = fullNameField.getText();
            String getUsername = usernameField.getText();
            String getEmail = emailField.getText();
            String getPassword = confirmPasswordField.getText();
            boolean ask = validateRegistration();
            if (ask){
                model.Register registerObj = new model.Register();
                String[] userData = {getFullName, getUsername, getEmail, getPassword};
                boolean result = registerObj.registerUser(userData);
                if (result){
                    JOptionPane.showMessageDialog(mainRegisterFrame, "Account Created Successfully");
                    new Login();
                    mainRegisterFrame.dispose();
                }

            }

            System.out.println("Submit Button Clicked");

        });

        resetButton.addActionListener(e -> {
            fullNameField.setText("");
            usernameField.setText("");
            emailField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
        });

        loginButton.addActionListener(e -> {
            new Login();
            mainRegisterFrame.dispose();
        });
    }

    public void createLeftPanel(){
        leftPanel = new JPanel();
        mainRegisterFrame.add(leftPanel);
        leftPanel.setBackground(Color.white);
        leftPanel.setVisible(true);
        leftPanel.setBounds(0,0,400,500);
        leftPanel.setLayout(null);
    }

    public void createRightPanel(){

        rightPanel = new JPanel();
        mainRegisterFrame.add(rightPanel);
        rightPanel.setBackground(Color.white);
        rightPanel.setVisible(true);
        rightPanel.setBounds(400,0,600,500);
        rightPanel.setLayout(null);

    }

    public boolean validateRegistration(){

        String fullName = fullNameField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (fullName.isEmpty()){
            JOptionPane.showMessageDialog(mainRegisterFrame, "Full Name is required field");
        }
        else if(username.isEmpty()){
            JOptionPane.showMessageDialog(mainRegisterFrame, "Username is required field");
        }
        else if(email.isEmpty()){
            JOptionPane.showMessageDialog(mainRegisterFrame, "Email is required field");
        }
        else if(!email.contains("@")){
            JOptionPane.showMessageDialog(mainRegisterFrame, "Email is not valid");
        }
        else if(password.isEmpty()){
            JOptionPane.showMessageDialog(mainRegisterFrame, "Password field is required");
        }
        else if(confirmPassword.isEmpty()){
            JOptionPane.showMessageDialog(mainRegisterFrame, "Please Confirm your password");
        }
        else if(!confirmPassword.equals(password)){
            JOptionPane.showMessageDialog(mainRegisterFrame, "Password does not match");
        }
        else {
            return true;
        }

        return false;
    }
}
