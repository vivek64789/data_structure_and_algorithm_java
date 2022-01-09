package week5;


import java.util.*;
class Graph {

    /*
     * this class finds the vulnerable points, means if the vertices is disconnected then that will split the graph
     * into more 2 or more than 2 networks
     */

    private final int totalVertices; // No. of vertices

    private final LinkedList[] adjacencyList;
    int totalTime = 0;

    Graph(int vertex) {

        this.totalVertices = vertex;
        adjacencyList = new LinkedList[vertex];
        for (int i = 0; i < vertex; ++i)
            adjacencyList[i] = new LinkedList();
    }

    void addEdge(int source, int destination) {

        adjacencyList[source].add(destination);
        adjacencyList[destination].add(source);
    }

    void checkVulnerablePoint(int parentVertex, boolean[] visited, int[] allPrevPath,
                              int[] prevPath, int[] parentVertices, boolean[] vulnerablePoints) {

        int children = 0;

        visited[parentVertex] = true;

        allPrevPath[parentVertex] = prevPath[parentVertex] = ++totalTime;

        Iterator<Integer> iterator = adjacencyList[parentVertex].iterator();
        while (iterator.hasNext()) {
            int childVertex = iterator.next();

            if (!visited[childVertex]) {
                children++;
                parentVertices[childVertex] = parentVertex;
                checkVulnerablePoint(childVertex, visited, allPrevPath, prevPath, parentVertices, vulnerablePoints);

                prevPath[parentVertex] = Math.min(prevPath[parentVertex], prevPath[childVertex]);


                if (parentVertices[parentVertex] == -1 && children > 1)
                    vulnerablePoints[parentVertex] = true;

                if (parentVertices[parentVertex] != -1 && prevPath[childVertex] >= allPrevPath[parentVertex])
                    vulnerablePoints[parentVertex] = true;
            }

            else if (childVertex != parentVertices[parentVertex])
                prevPath[parentVertex] = Math.min(prevPath[parentVertex], allPrevPath[childVertex]);
        }
    }

    void vulnerablePoints() {

        boolean[] visited = new boolean[totalVertices];
        int[] allPrevPath = new int[totalVertices];
        int[] prevPath = new int[totalVertices];
        int[] parent = new int[totalVertices];
        boolean[] vulnerablePoints = new boolean[totalVertices];


        for (int i = 0; i < totalVertices; i++) {
            parent[i] = -1;
            visited[i] = false;
            vulnerablePoints[i] = false;
        }

        for (int i = 0; i < totalVertices; i++)
            if (!visited[i])
                checkVulnerablePoint(i, visited, allPrevPath, prevPath, parent, vulnerablePoints);


        for (int i = 0; i < totalVertices; i++)
            if (vulnerablePoints[i] == true)
                System.out.print(i + ", ");
    }


    public static void main(String[] args) {
        Graph obj = new Graph(14);
        obj.addEdge(5, 9);
        obj.addEdge(5, 7);
        obj.addEdge(9, 7);
        obj.addEdge(7, 12);
        obj.addEdge(12, 13);
        System.out.print("Vulnerable Points: ");
        obj.vulnerablePoints();
        System.out.println();
    }
}
