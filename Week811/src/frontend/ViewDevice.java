package frontend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ViewDevice {

    // creating fonts objects
    Font font20 = new Font("Poppins", Font.BOLD, 20);
    Font font12 = new Font("Poppins", Font.BOLD, 12);
    Font font14 = new Font("Poppins", Font.BOLD, 14);
    JLabel viewProductTitle = new JLabel();
    JFrame mainViewProductFrame = new JFrame();
    JButton updateButton, deleteButton, cancelButton = new JButton();
    JPanel viewProductPanel = new JPanel();
    JTable deviceTable;


    ViewDevice() {
        mainViewProductFrame.getContentPane().setBackground(Color.white);
        mainViewProductFrame.setVisible(true);
        mainViewProductFrame.setSize(865, 700);
        mainViewProductFrame.setLocation(500, 80);
        mainViewProductFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainViewProductFrame.setAlwaysOnTop(true);
        mainViewProductFrame.setResizable(false);

        viewProductFrame();

    }

    public void viewProductFrame() {
        backend.Device deviceObj = new backend.Device();
        viewProductPanel = new JPanel();
        mainViewProductFrame.add(viewProductPanel);
        viewProductPanel.setBackground(Color.white);
        viewProductPanel.setBounds(0, 0, 865, 700);
        viewProductPanel.setLayout(null);


        String[] column = {"Product ID", "Product Name", "Model Number", "Product Type", "Ports", "Description", "Connected With"};
        String[][] data = deviceObj.tableData(7);
//        System.out.println(Arrays.deepToString(data));

        if (!(data == null)){
            deviceTable = new JTable(data,column);
        }else{
            JOptionPane.showMessageDialog(mainViewProductFrame, "No data to view");
        }

        JScrollPane sp = new JScrollPane(deviceTable);
        viewProductPanel.add(sp);
        sp.setBounds(0, 0, 850, 500);

        updateButton = new JButton("Update");
        viewProductPanel.add(updateButton);
        updateButton.setFont(font12);
        updateButton.setBackground(Color.DARK_GRAY);
        updateButton.setForeground(Color.white);
        updateButton.setBounds(150, 530, 150, 30);

        deleteButton = new JButton("Delete");
        viewProductPanel.add(deleteButton);
        deleteButton.setFont(font12);
        deleteButton.setBackground(Color.DARK_GRAY);
        deleteButton.setForeground(Color.white);
        deleteButton.setBounds(350, 530, 150, 30);

        cancelButton = new JButton("Close");
        viewProductPanel.add(cancelButton);
        cancelButton.setFont(font12);
        cancelButton.setBackground(Color.DARK_GRAY);
        cancelButton.setForeground(Color.white);
        cancelButton.setBounds(550, 530, 150, 30);

        // action listener
        cancelButton.addActionListener(e -> {
            mainViewProductFrame.dispose();
        });

        updateButton.addActionListener(e -> {
            int row = deviceTable.getSelectedRow();
            System.out.println(row);
            if (row >= 0) {
                TableModel getModel = deviceTable.getModel();
                String deviceID = (String) getModel.getValueAt(row, 0);
                String deviceName = (String) getModel.getValueAt(row, 1);
                String deviceModelNumber = (String) getModel.getValueAt(row, 2);
                String deviceTypeItem = (String) getModel.getValueAt(row, 3);
                String devicePorts = (String) getModel.getValueAt(row, 4);
                String deviceDescription = (String) getModel.getValueAt(row, 5);

                new UpdateDevice(deviceID, deviceName, deviceModelNumber, deviceTypeItem, devicePorts, deviceDescription);
            } else {
                JOptionPane.showMessageDialog(mainViewProductFrame, "Please select device", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            int row = deviceTable.getSelectedRow();
            if (row>=0){
                TableModel getModel = deviceTable.getModel();
                String deviceID = (String) getModel.getValueAt(row, 0);

                backend.Device obj = new backend.Device();
                boolean result = obj.deleteDevice(deviceID);
                if (result){
                    frontend.AdminDashboard.refreshdDeviceComboBox();
                    JOptionPane.showMessageDialog(mainViewProductFrame, "Device Deleted Successfully");

                }
                else{
                    JOptionPane.showMessageDialog(mainViewProductFrame, "There is error deleting device");
                }
            }
        });

    }

}
