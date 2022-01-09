package backend;

import java.io.IOException;
import java.util.ArrayList;

public class Device {

    FileHandling fileHandlingObj = new FileHandling();
    public ArrayList<String> allDevicesArrayList = fetchAllDevices();
    String[][] deviceArray = tableData(7);
    public int totalDevices = allDevicesArrayList.size();
    int endId = 0;
    public AdjacencyGraph deviceGraph;

    public Device() {
        if (this.totalDevices > 0) {
            this.endId = Integer.parseInt(
                    this.allDevicesArrayList.get(this.allDevicesArrayList.size() - 1).split(",")[0]);
        }
        deviceGraph = new AdjacencyGraph();

    }

    public static void main(String[] args) throws IOException {
        Device d = new Device();

        d.deviceGraph.shortestPathAlgorithm(0, 9);
    }

    public String[] findShortestPath(int source, int destination) throws IOException {
        int[] path = this.deviceGraph.shortestPathAlgorithm(source,destination);
        String[] pathDevices = new String[path.length];
        for (int i = 0; i < path.length; i++) {
            pathDevices[i] = convertIdToName(Integer.toString(path[i]));
        }
        return pathDevices;
    }



    public boolean addConnectionToMatrix(int source, int destination) throws IOException {

        return this.deviceGraph.addEdge(source,destination);
    }

    public boolean removeConnectionFromMatrix(int source, int destination) throws IOException {

        return this.deviceGraph.removeEdge(source,destination);
    }

    public int[][] readConnectionFromMatrix() throws IOException {
        return this.deviceGraph.readMatrix();
    }

    public ArrayList<String> fetchAllDevices() {
        return fileHandlingObj.fetchAllDevices(Variables.deviceFileName);
    }

    public String[][] tableData(int columnLength) {
        if (allDevicesArrayList.size() > 0) {
            String data[][] = new String[allDevicesArrayList.size()][columnLength];
            for (int i = 0; i < allDevicesArrayList.size(); i++) {
                for (int j = 0; j < columnLength; j++) {
                    data[i][j] = allDevicesArrayList.get(i).split(",")[j];
                }
            }
            return data;
        } else {
            return null;
        }
    }


    public String convertIdToName(String ID) {
        String name = "";
        for (int i = 0; i < this.totalDevices; i++) {
            if (deviceArray[i][0].equals(ID)) {
                name = deviceArray[i][1];
            }
        }
        return name;
    }

    public String[] findDeviceLine(String ID) {
        String[] line = null;
        for (int i = 0; i < this.totalDevices; i++) {
            if (deviceArray[i][0].equals(ID)) {
                line = deviceArray[i];
            }
        }
        return line;
    }

    public String convertNameToId(String name) {
        String ID = "";
        for (int i = 0; i < this.totalDevices; i++) {
            if (deviceArray[i][1].equals(name)) {
                ID = deviceArray[i][0];
            }
        }
        return ID;
    }

    public boolean addDevice(String[] deviceDetail) {
        FileHandling fileHandlingObj = new FileHandling();
        return fileHandlingObj.create(Variables.deviceFileName, deviceDetail);
    }

    public boolean deleteDevice(String ID) {
        FileHandling fileHandlingObj = new FileHandling();
        return fileHandlingObj.deleteData(Variables.deviceFileName, ID);
    }

    public boolean updateDevice(String[] newData) {
        return fileHandlingObj.UpdateFile(Variables.deviceFileName, newData);
    }

}
