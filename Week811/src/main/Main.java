package main;
import com.formdev.flatlaf.FlatIntelliJLaf;
import frontend.Login;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf() {
            });
        } catch (Exception ex) {
            System.err.println("Failed to initialize UI");
        }
        new Login();
    }

}

