package backend;

import java.io.IOException;
import java.util.*;

public class AdjacencyGraph {

    int vertex = 10;
    int[] path;
    public int[][] matrix;
    FileHandling fileHandlingObj;
    Device deviceObj;


    public AdjacencyGraph() {

        this.fileHandlingObj = new FileHandling();
        this.matrix = new int[vertex][vertex];
    }

    public boolean addEdge(int source, int destination) throws IOException {
        deviceObj = new Device();
        matrix = deviceObj.readConnectionFromMatrix();
        if (source != destination) {
            matrix[source][destination] = 1;
            matrix[destination][source] = 1;
            createMatrix();
//            printMatrix();
        }
        return true;
    }

    public boolean removeEdge(int source, int destination) throws IOException {
        deviceObj = new Device();
        matrix = deviceObj.readConnectionFromMatrix();
        if (source != destination) {
            matrix[source][destination] = 0;
            matrix[destination][source] = 0;
            createMatrix();
//            printMatrix();
        }
        return true;
    }

    public void createMatrix() throws IOException {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                builder.append(matrix[i][j] + "");
                if (j < matrix.length - 1)
                    builder.append(",");
            }
            builder.append("\n");
        }
        fileHandlingObj.writeGraphMatrix(Variables.matrixFileName, builder);
    }

    public int[][] readMatrix() throws IOException {
        int[][] matrixData = fileHandlingObj.readMatrix();
        printReadMatrix(matrixData);
        return matrixData;
    }


    public void printMatrix() {
        System.out.println("Matrix:");
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printReadMatrix(int[][] matrixData) {

        System.out.println("Matrix:");
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                System.out.print(matrixData[i][j] + " ");
            }
            System.out.println();
        }
    }


    public int[] shortestPathAlgorithm(int source, int destination) throws IOException {
        deviceObj = new Device();
        int[][] fileMatrixData = deviceObj.readConnectionFromMatrix();

        boolean[] visited = new boolean[vertex];
        int[] minDistance = new int[vertex];
        int[] prevPath = new int[vertex];

        for (int i = 0; i < vertex; i++) {

            minDistance[i] = Integer.MAX_VALUE;
            prevPath[i] = -1;
        }

        minDistance[source] = 0;

        for (int i = 0; i < fileMatrixData.length; i++) {

            int minVertex = findMinVertex(minDistance, visited);
            visited[minVertex] = true;

            for (int j = 0; j < fileMatrixData.length; j++) {

                if (fileMatrixData[minVertex][j] != 0 && !visited[j]) {

                    int newDistance = minDistance[minVertex] + fileMatrixData[minVertex][j];

                    if (newDistance < minDistance[j]) {
                        minDistance[j] = newDistance;
                        prevPath[j] = minVertex;

                    }
                }
            }
        }
        return generateShortestPath(source, destination, prevPath, minDistance);
    }

    public int findMinVertex(int[] minDistance, boolean[] visited) {
        int minVertex = -1;
        for (int i = 0; i < minDistance.length; i++) {
            if (!visited[i] && (minVertex == -1 || minDistance[i] < minDistance[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }


    int[] generateShortestPath(int source, int destination, int[] path, int[] minDistance) {
        System.out.println(Arrays.toString(path));
        int crawl = destination;
        LinkedList pathList = new LinkedList();
        pathList.addNode(crawl);
        while (path[crawl] != -1) {
            pathList.addNode(path[crawl]);
            crawl = path[crawl];
        }
        int[] shortPath = new int[pathList.size()];
        for (int i = 0; i < pathList.size(); i++) {
            shortPath[i] = pathList.get(i);
        }
        return shortPath;
    }

}