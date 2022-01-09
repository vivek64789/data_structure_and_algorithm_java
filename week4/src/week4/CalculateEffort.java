package week4;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class CalculateEffort {

    int difference = Integer.MIN_VALUE;

    public int findEffort(int[][] heights, String type) {
        int row = heights.length;
        int column = heights[0].length;
        PriorityQueue<Point> points = new PriorityQueue<Point>((a, b) -> b.diff - a.diff);


        if (type.equals("MIN")) {
            points = new PriorityQueue<Point>((a, b) -> a.diff - b.diff);
        }
        points.add(new Point(0, 0, heights[0][0], 0));
        Set<String> visited = new HashSet<>();
        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        while (!points.isEmpty()) {
            Point curr = points.poll();
            difference = Math.max(difference, curr.diff);
            if (curr.x == row - 1 && curr.y == column - 1) return difference;

            visited.add(getKey(curr.x, curr.y));
            for (int[] d : directions) {
                int nextX = d[0] + curr.x;
                int nextY = d[1] + curr.y;
                String key = getKey(nextX, nextY);

                if (nextX < 0 || nextX >= row || nextY < 0 || nextY >= column || visited.contains(key)) continue;

                int nextDiff = Math.abs(heights[nextX][nextY] - curr.h);
                points.add(new Point(nextX, nextY, heights[nextX][nextY], nextDiff));
            }
        }

        return -1;
    }

    private String getKey(int x, int y) {
        return x + "->" + y;
    }

    class Point {
        int x;
        int y;
        int h;
        int diff;

        public Point(int x, int y, int h, int diff) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.diff = diff;
        }
    }


    int minimumEffort(int[][] heights) {
        return findEffort(heights, "MIN");
    }

    int maximumEffort(int[][] heights) {
        return findEffort(heights, "MAX");
    }

    public static void main(String[] args) {

        int[][] heights = {{1, 3, 8, 9}, {8, 7, 2, 6}, {13, 3, 6, 4}, {13, 1, 5, 3}};
        CalculateEffort calculateEffortObj = new CalculateEffort();
        int min = calculateEffortObj.minimumEffort(heights);
        int max = calculateEffortObj.maximumEffort(heights);

        System.out.println("Minimum Effort: " + min);
        System.out.println("Maximum Effort: " + max);
        System.out.println("Absolute Difference: " + (max - min));

    }
}