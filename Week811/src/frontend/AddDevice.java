package frontend;

import backend.Device;
import backend.FileHandling;
import backend.Variables;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddDevice {

    // creating fonts objects
    Font font20 = new Font("Poppins", Font.BOLD, 20);
    Font font12 = new Font("Poppins", Font.BOLD, 12);
    Font font14 = new Font("Poppins", Font.BOLD, 14);
    public JFrame mainAddDeviceFrame = new JFrame();
    JLabel addDeviceTitle, deviceId, deviceName, deviceModelNumber, deviceDescription, deviceType, devicePorts = new JLabel();
    JComboBox<String> deviceTypeCombo;
    JPanel addDevicePanel = new JPanel();
    JTextField deviceIdField, deviceNameField, deviceModelNumberField, devicePortsField = new JTextField();
    JTextArea deviceDescriptionField = new JTextArea();
    JButton saveButton, resetButton, cancelButton = new JButton();



    public AddDevice() {
        mainAddDeviceFrame.getContentPane().setBackground(Color.white);
        mainAddDeviceFrame.setVisible(true);
        mainAddDeviceFrame.setSize(865, 700);
        mainAddDeviceFrame.setLocation(500, 80);
        mainAddDeviceFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainAddDeviceFrame.setAlwaysOnTop(true);
        mainAddDeviceFrame.setResizable(false);
        addDeviceFrame();
    }

    public void addDeviceFrame() {
        backend.Device deviceObj = new backend.Device();
        addDevicePanel = new JPanel();
        mainAddDeviceFrame.add(addDevicePanel);
        addDevicePanel.setLayout(null);
        addDevicePanel.setBounds(0, 0, 865, 700);
        addDevicePanel.setBackground(Color.white);


        addDeviceTitle = new JLabel("Add Device");
        addDeviceTitle.setFont(font20);
        addDeviceTitle.setBounds(370, 20, 200, 40);
        addDevicePanel.add(addDeviceTitle);


        deviceId = new JLabel("Device ID");
        addDevicePanel.add(deviceId);
        deviceId.setFont(font12);
        deviceId.setBounds(250, 100, 150, 30);

        deviceIdField = new JTextField();
        addDevicePanel.add(deviceIdField);
        deviceIdField.setFont(font12);
        deviceIdField.setBounds(250, 125, 350, 30);


        deviceName = new JLabel("Device Name");
        addDevicePanel.add(deviceName);
        deviceName.setFont(font12);
        deviceName.setBounds(250, 165, 150, 30);

        deviceNameField = new JTextField();
        addDevicePanel.add(deviceNameField);
        deviceNameField.setFont(font12);
        deviceNameField.setBounds(250, 190, 350, 30);


        deviceModelNumber = new JLabel("Device Model Number");
        addDevicePanel.add(deviceModelNumber);
        deviceModelNumber.setFont(font12);
        deviceModelNumber.setBounds(250, 230, 150, 30);

        deviceModelNumberField = new JTextField();
        addDevicePanel.add(deviceModelNumberField);
        deviceModelNumberField.setFont(font12);
        deviceModelNumberField.setBounds(250, 255, 350, 30);

        deviceType = new JLabel("Device Type");
        addDevicePanel.add(deviceType);
        deviceType.setFont(font12);
        deviceType.setBounds(250, 295, 150, 30);

        String[] deviceNames = {"Router", "Server", "Switch", "Hub", "Bridge", "Gateway", "Modem","Access Point", "Mobile", "Computer"} ;
        deviceTypeCombo= new JComboBox<String>(deviceNames);
        addDevicePanel.add(deviceTypeCombo);
        deviceTypeCombo.setFont(font12);
        deviceTypeCombo.setBounds(250, 320, 350, 30);


        devicePorts = new JLabel("Device Ports");
        addDevicePanel.add(devicePorts);
        devicePorts.setFont(font12);
        devicePorts.setBounds(250, 360, 150, 30);

        devicePortsField = new JTextField();
        addDevicePanel.add(devicePortsField);
        devicePortsField.setFont(font12);
        devicePortsField.setBounds(250, 385, 350, 30);

        deviceDescription = new JLabel("Device Description");
        addDevicePanel.add(deviceDescription);
        deviceDescription.setFont(font12);
        deviceDescription.setBounds(250, 425, 150, 30);

        deviceDescriptionField = new JTextArea();
        addDevicePanel.add(deviceDescriptionField);
        deviceDescriptionField.setFont(font12);
        deviceDescriptionField.setBorder(BorderFactory.createLineBorder(Color.gray));
        deviceDescriptionField.setBounds(250, 450, 350, 70);

        // button

        saveButton = new JButton("Save");
        addDevicePanel.add(saveButton);
        saveButton.setFont(font12);
        saveButton.setBackground(Color.DARK_GRAY);
        saveButton.setForeground(Color.white);
        saveButton.setBounds(280, 540, 80, 30);
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        resetButton = new JButton("Reset");
        addDevicePanel.add(resetButton);
        resetButton.setFont(font12);
        resetButton.setBackground(Color.DARK_GRAY);
        resetButton.setForeground(Color.white);
        resetButton.setBounds(385, 540, 80, 30);
        resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cancelButton = new JButton("Cancel");
        addDevicePanel.add(cancelButton);
        cancelButton.setFont(font12);
        cancelButton.setBackground(Color.DARK_GRAY);
        cancelButton.setForeground(Color.white);
        cancelButton.setBounds(485, 540, 80, 30);
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        // Action listener

        saveButton.addActionListener(e -> {
            String getDeviceId = deviceIdField.getText();
            String getDeviceName = deviceNameField.getText();
            String getDeviceModelNumber = deviceModelNumberField.getText();
            String getDeviceType = (String) deviceTypeCombo.getSelectedItem();
            String getDevicePorts = devicePortsField.getText();
            String getDeviceDescription = deviceDescriptionField.getText();

            String[] data = {getDeviceId, getDeviceName, getDeviceModelNumber, getDeviceType, getDevicePorts, getDeviceDescription, getDeviceId};
            boolean result = deviceObj.addDevice(data);
            if (result){
                frontend.AdminDashboard.refreshdDeviceComboBox();
                JOptionPane.showMessageDialog(mainAddDeviceFrame, ""+getDeviceName+" Added Successfully");
            }else {
                JOptionPane.showMessageDialog(mainAddDeviceFrame, ""+getDeviceName+" There is an Error");
            }

        });

        cancelButton.addActionListener(e -> {
            mainAddDeviceFrame.dispose();
        });

        resetButton.addActionListener(e -> {
            deviceNameField.setText("");
            deviceModelNumberField.setText("");
            deviceTypeCombo.setSelectedIndex(0);
            devicePortsField.setText("");
            deviceDescriptionField.setText("");
        });
    }

    String getDeviceId() {
        backend.FileHandling fileHandlingObj = new backend.FileHandling();
        ArrayList<String> data = fileHandlingObj.fetchAllDevices(Variables.deviceFileName);
        if (data.size() > 0) {
            int prevId = Integer.parseInt(data.get(data.size() - 1).split(",")[0]);
            return Integer.toString(prevId + 1);
        } else {
            return "0";
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf() {
            });
        } catch (Exception ex) {
            System.err.println("Failed to initialize UI");
        }
        new AddDevice();
    }
}
