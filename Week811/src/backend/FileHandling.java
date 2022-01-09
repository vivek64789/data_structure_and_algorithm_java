package backend;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandling {

    public boolean create(String fileName, String[] data) {
        boolean result=false;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.canWrite()) {
                StringBuilder newData = new StringBuilder();
                for (int i = 0; i < data.length; i++) {
                    if (i != (data.length - 1)) {
                        newData.append(data[i]).append(",");
                    } else {
                        newData.append(data[i]);
                    }
                }
                FileWriter fileWriterObj = new FileWriter(file, true);
                BufferedWriter buw = new BufferedWriter(fileWriterObj);
                buw.write(newData + "\n");
                buw.close();
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean writeGraphMatrix(String filename, StringBuilder matrixBuilder) throws IOException {

        boolean result=false;
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(matrixBuilder.toString());
            writer.close();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int[][] readMatrix() throws IOException {
        int[][] board = new int[10][10];
        BufferedReader reader = new BufferedReader(new FileReader(Variables.matrixFileName));
        String line = "";
        int row = 0;
        while((line = reader.readLine()) != null)
        {
            String[] cols = line.split(",");
            int col = 0;
            for(String  c : cols)
            {
                board[row][col] = Integer.parseInt(c);
                col++;
            }
            row++;
        }
        reader.close();
        return board;
    }

    public boolean UpdateFile(String fileName, String[] changeData) {
        boolean result = false;
        try {
            File fl = new File(fileName);
            if (!fl.exists()) {
                fl.createNewFile();
            }
            if (fl.canWrite()) {
                ArrayList<String> oldData = fetchAllDevices(fileName);
                String newData = "";
                for (int i = 0; i < changeData.length; i++) {
                    if (i != (changeData.length - 1)) {
                        newData += changeData[i] + ",";
                    } else {
                        newData += changeData[i];
                    }
                }
                FileWriter fileWriterObj = new FileWriter(fl);
                BufferedWriter buw = new BufferedWriter(fileWriterObj);
                for (String d : oldData) {
                    if (d.split(",")[0].equals(changeData[0])) {
                        buw.write(newData + "\n");
                    } else {
                        buw.write(d + "\n");
                    }
                }
                buw.close();
                result = true;
            }else {
                result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteData(String fileName, String ID) {
        boolean result = false;
        try {
            File fl = new File(fileName);
            if (!fl.exists()) {
                fl.createNewFile();
            }
            if (fl.canWrite()) {
                ArrayList<String> oldData = fetchAllDevices(fileName);
                FileWriter fileWriterObj = new FileWriter(fl);
                BufferedWriter buw = new BufferedWriter(fileWriterObj);
                for (String d : oldData) {
                    if (!d.split(",")[0].equals(ID)) {
                        buw.write(d + "\n");
                    }
                }
                buw.close();
                result = true ;
            }else {
                result = false;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return result;
    }

    public String[] readFile(String fileName, String search, int searchIndex) {
        String[] fileData = null;
        try {
            File file = new File(Variables.userFileName);
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNext()) {
                String[] data = fileReader.nextLine().split(",");
                if (search.toLowerCase().equals(data[searchIndex].toLowerCase())) {
                    fileData = data;
                    break;
                }
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
        return fileData;
    }

    public ArrayList<String> fetchAllDevices(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File fl = new File(fileName);
            if (fl.exists()) {
                Scanner fileReader = new Scanner(fl);
                while (fileReader.hasNext()) {
                    fileData.add(fileReader.nextLine());
                }
                fileReader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileData;
    }
}
