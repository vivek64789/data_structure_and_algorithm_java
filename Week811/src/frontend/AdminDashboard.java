package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;


public class AdminDashboard {
    private static AbstractButton getPathSearchButton;
    // creating fonts objects
    Font font20 = new Font("Poppins", Font.BOLD, 20);
    Font font12 = new Font("Poppins", Font.BOLD, 12);
    Font font12Plain = new Font("Poppins", Font.PLAIN, 12);
    Font font14 = new Font("Poppins", Font.BOLD, 14);
    JFrame mainAdminDashboardFrame = new JFrame();
    JPanel topPanel, leftPanel, rightPanel, newPanel = new JPanel();
    JLabel adminDashboardTitle, logout, addNodeTitle, getPathTitle, showPathLabel = new JLabel();
    JTextArea showPathTextArea = new JTextArea();
    JButton addDeviceButton, viewDeviceButton, addNodeDeviceSaveButton, disconnectButton, viewConnectionButton = new JButton();
    static JComboBox<Object> addNodeDevice1Combo, addNodeDevice2Combo, getPathDeviceSourceCombo, getPathDeviceDestinationCombo;
    backend.Device deviceObj;


    AdminDashboard() throws IOException {
//        this.adjacencyGraphObj = new backend.AdjacencyGraph();
//        this.adjacencyGraphObj.matrix = deviceObj.readConnectionFromMatrix();
        deviceObj = new backend.Device();


        adminDashboardFrame();
        mainAdminDashboardFrame.getContentPane().setBackground(Color.white);
        mainAdminDashboardFrame.setVisible(true);
        mainAdminDashboardFrame.setSize(1366, 780);
        mainAdminDashboardFrame.setLocation(0, 0);
        mainAdminDashboardFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainAdminDashboardFrame.setAlwaysOnTop(false);
        mainAdminDashboardFrame.setResizable(false);

    }

    public void adminDashboardFrame() throws IOException {
        topPanelFrame();
        leftPanelFrame();
        rightPanelFrame();
    }

    public void topPanelFrame() {
        createTopPanel();
        adminDashboardTitle = new JLabel("Admin Dashboard");
        adminDashboardTitle.setFont(font20);
        topPanel.add(adminDashboardTitle);
        adminDashboardTitle.setBounds(580, 7, 200, 40);
        adminDashboardTitle.setForeground(Color.DARK_GRAY);

        logout = new JLabel("LOGOUT");
        topPanel.add(logout);
        logout.setFont(font14);
        logout.setForeground(Color.red);
        logout.setBounds(1250, 7, 60, 40);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // action listener
        logout.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                new Login();
                mainAdminDashboardFrame.dispose();
            }

        });

    }

    public void leftPanelFrame() throws IOException {
        createLeftPanel();
        addDeviceButton = new JButton("Add Device");
        leftPanel.add(addDeviceButton);
        addDeviceButton.setFont(font14);
        addDeviceButton.setBackground(Color.DARK_GRAY);
        addDeviceButton.setForeground(Color.white);
        addDeviceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addDeviceButton.setBounds(80, 50, 150, 30);

        viewDeviceButton = new JButton("View Device");
        leftPanel.add(viewDeviceButton);
        viewDeviceButton.setFont(font14);
        viewDeviceButton.setBackground(Color.DARK_GRAY);
        viewDeviceButton.setForeground(Color.white);
        viewDeviceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewDeviceButton.setBounds(260, 50, 150, 30);

        viewConnectionButton = new JButton("View Connections");
        leftPanel.add(viewConnectionButton);
        viewConnectionButton.setFont(font14);
        viewConnectionButton.setBackground(Color.DARK_GRAY);
        viewConnectionButton.setForeground(Color.white);
        viewConnectionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewConnectionButton.setBounds(145, 100, 200, 30);

        // creating labels and fields to Connect Path

        addNodeTitle = new JLabel("Connect Two Graph");
        leftPanel.add(addNodeTitle);
        addNodeTitle.setFont(font14);
        addNodeTitle.setBounds(175, 150, 150, 30);

        // creating labels and fields to Search Shortest Path

        getPathTitle = new JLabel("Search Shortest Path");
        leftPanel.add(getPathTitle);
        getPathTitle.setFont(font14);
        getPathTitle.setBounds(170, 350, 250, 30);


        getPathSearchButton = new JButton("Search");
        leftPanel.add(getPathSearchButton);
        getPathSearchButton.setFont(font12);
        getPathSearchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        getPathSearchButton.setBounds(200, 450, 100, 30);

        showPathLabel = new JLabel("");
        leftPanel.add(showPathLabel);
        showPathLabel.setFont(font14);
        showPathLabel.setBounds(140, 530, 200, 30);

        showPathTextArea = new JTextArea();
        leftPanel.add(showPathTextArea);
        showPathTextArea.setFont(font12);
        showPathTextArea.setBounds(40,530,400,60);

        addDeviceButton.addActionListener(e -> {
            new AddDevice();

        });

        viewDeviceButton.addActionListener(e -> {
            new ViewDevice();
        });

        viewConnectionButton.addActionListener(e -> {
            try {
                backend.GraphVisualization obj = new backend.GraphVisualization();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        backend.Device obj = new backend.Device();

        String[][] data = obj.tableData(7);
        String[] deviceNames;
        if (!(data==null)){
            deviceNames = new String[data.length];
            for (int i = 0; i < data.length; i++) {
                deviceNames[i] = data[i][1];
            }


        DefaultComboBoxModel deviceDataModelSource = new DefaultComboBoxModel(deviceNames);
        DefaultComboBoxModel deviceDataModelDestination = new DefaultComboBoxModel(deviceNames);
        DefaultComboBoxModel device1 = new DefaultComboBoxModel(deviceNames);
        DefaultComboBoxModel device2 = new DefaultComboBoxModel(deviceNames);

        addNodeDevice1Combo = new JComboBox<>();
        addNodeDevice1Combo.setModel(device1);
        leftPanel.add(addNodeDevice1Combo);
        addNodeDevice1Combo.setBounds(50, 200, 150, 30);

        // product1 combo
        addNodeDevice2Combo = new JComboBox<>();
        addNodeDevice2Combo.setModel(device2);
        leftPanel.add(addNodeDevice2Combo);
        addNodeDevice2Combo.setBounds(300, 200, 150, 30);

        addNodeDeviceSaveButton = new JButton("Connect");
        leftPanel.add(addNodeDeviceSaveButton);
        addNodeDeviceSaveButton.setFont(font12);
        addNodeDeviceSaveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addNodeDeviceSaveButton.setBounds(100, 250, 150, 30);
        addNodeDeviceSaveButton.setForeground(Color.DARK_GRAY);


        disconnectButton = new JButton("Disconnect");
        leftPanel.add(disconnectButton);
        disconnectButton.setFont(font12);
        disconnectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        disconnectButton.setBounds(240, 250, 150, 30);
        disconnectButton.setForeground(Color.red);


        getPathDeviceSourceCombo = new JComboBox<>();
        getPathDeviceSourceCombo.setModel(deviceDataModelSource);
        getPathDeviceSourceCombo.setEditable(false);
        leftPanel.add(getPathDeviceSourceCombo);
        getPathDeviceSourceCombo.setFont(font12Plain);
        getPathDeviceSourceCombo.setBounds(50, 400, 150, 30);

        // Destination combo
        getPathDeviceDestinationCombo = new JComboBox<>();
        getPathDeviceDestinationCombo.setModel(deviceDataModelDestination);
        getPathDeviceDestinationCombo.setEditable(false);
        leftPanel.add(getPathDeviceDestinationCombo);
        getPathDeviceDestinationCombo.setFont(font12Plain);
        getPathDeviceDestinationCombo.setBounds(300, 400, 150, 30);

        addNodeDeviceSaveButton.addActionListener(e -> {
            try {
                saveConnection();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        disconnectButton.addActionListener(e -> {
            try {
                removeConnection();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


        getPathSearchButton.addActionListener(e -> {
            try {
                findShortestPath();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }}

    public void rightPanelFrame() {
        createRightPanel();

    }

    public void createTopPanel() {
        topPanel = new JPanel();
        mainAdminDashboardFrame.add(topPanel);
        topPanel.setBounds(0, 0, 1350, 50);
        topPanel.setBackground(Color.white);
        topPanel.setVisible(true);
        topPanel.setLayout(null);

    }


    public void createLeftPanel() {
        leftPanel = new JPanel();
        mainAdminDashboardFrame.add(leftPanel);
        leftPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        leftPanel.setBounds(0, 50, 500, 690);
        leftPanel.setBackground(Color.white);
        leftPanel.setVisible(true);
        leftPanel.setLayout(null);
    }

    public void createRightPanel() {

        rightPanel = new JPanel();
        mainAdminDashboardFrame.add(rightPanel);
        rightPanel.setBounds(500, 0, 850, 740);
        rightPanel.setBackground(Color.white);
        rightPanel.setVisible(true);
        rightPanel.setLayout(null);

        newPanel = new JPanel();
        mainAdminDashboardFrame.add(newPanel);
        newPanel.setBounds(500, 0, 850, 690);
        newPanel.setBackground(Color.white);
        newPanel.setVisible(false);
        newPanel.setLayout(null);

    }

    public static void refreshdDeviceComboBox() {
        getPathDeviceSourceCombo.removeAllItems();
        getPathDeviceDestinationCombo.removeAllItems();
        addNodeDevice1Combo.removeAllItems();
        addNodeDevice2Combo.removeAllItems();

        backend.Device obj = new backend.Device();

        String[][] data = obj.tableData(7);
        String[] deviceNames = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            deviceNames[i] = data[i][1];
        }

        for (int i = 0; i < deviceNames.length; i++) {
            getPathDeviceSourceCombo.addItem(deviceNames[i]);
            getPathDeviceDestinationCombo.addItem(deviceNames[i]);
            addNodeDevice1Combo.addItem(deviceNames[i]);
            addNodeDevice2Combo.addItem(deviceNames[i]);
        }
    }

    public void removeConnection() throws IOException {
        int selectedDevice = addNodeDevice1Combo.getSelectedIndex();
        int connectedDevice = addNodeDevice2Combo.getSelectedIndex();

        boolean result = this.deviceObj.removeConnectionFromMatrix(selectedDevice, connectedDevice);
        if (result) {
            JOptionPane.showMessageDialog(mainAdminDashboardFrame, "Path removed successfully");
        }

    }


    public void saveConnection() throws IOException {

        int selectedDevice = addNodeDevice1Combo.getSelectedIndex();
        int connectedDevice = addNodeDevice2Combo.getSelectedIndex();

        boolean result = this.deviceObj.addConnectionToMatrix(selectedDevice, connectedDevice);
        if (result) {
            JOptionPane.showMessageDialog(mainAdminDashboardFrame, "Path added successfully");
        }
    }


    public void findShortestPath() throws IOException {
        backend.Device obj = new backend.Device();
        int source = getPathDeviceSourceCombo.getSelectedIndex();
        int destination = getPathDeviceDestinationCombo.getSelectedIndex();
        String pathLabel = "";
         new backend.ShortestPathVisualization(source,destination);
        String[] path = obj.findShortestPath(source, destination);
        System.out.println(Arrays.toString(path));
        for (int i = path.length - 1; i >= 0; i--) {
            if (i == 0) {
                pathLabel += path[i];
            } else {
                pathLabel += path[i] + " -----> ";
            }
        }
        showPathTextArea.setText(pathLabel);
    }
}
