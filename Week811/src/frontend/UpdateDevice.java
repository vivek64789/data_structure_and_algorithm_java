package frontend;

import backend.Device;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class UpdateDevice extends AddDevice {

    UpdateDevice(String deviceId,String deviceName,String deviceModelNumber, String deviceTypeItem, String devicePorts, String deviceDescription) {
        addDeviceTitle.setText("Update Product");
        deviceIdField.setText(deviceId);
        deviceNameField.setText(deviceName);
        deviceModelNumberField.setText(deviceModelNumber);
        deviceTypeCombo.setSelectedItem(deviceTypeItem);
        devicePortsField.setText(devicePorts);
        deviceDescriptionField.setText(deviceDescription);

        saveButton.setText("Update");

        for (ActionListener al : saveButton.getActionListeners()) {
            saveButton.removeActionListener(al);
        }
        saveButton.addActionListener(e -> {
            String getDeviceId = deviceIdField.getText();
            String getDeviceName = deviceNameField.getText();
            String getDeviceModelNumber = deviceModelNumberField.getText();
            String getDeviceType = (String) deviceTypeCombo.getSelectedItem();
            String getDevicePorts = devicePortsField.getText();
            String getDeviceDescription = deviceDescriptionField.getText();

            String[] data = {getDeviceId, getDeviceName, getDeviceModelNumber, getDeviceType, getDevicePorts, getDeviceDescription, getDeviceId};
            backend.Device obj = new backend.Device();
            boolean result = obj.updateDevice(data);
            if (result){
                JOptionPane.showMessageDialog(mainAddDeviceFrame, ""+getDeviceName+" updated Successfully");
            }
            else{
                JOptionPane.showMessageDialog(mainAddDeviceFrame, "Error while updating", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


    }

}
