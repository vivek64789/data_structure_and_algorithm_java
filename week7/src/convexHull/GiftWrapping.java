package convexHull;

//Write a java program to solve convex hull problem.

import java.util.Stack;

public class GiftWrapping {

	public int direction(Coordinate p, Coordinate q, Coordinate r) {

		int area = (q.x - p.x) * (r.y - q.y) - (q.y - p.y) * (r.x - q.x);
		return area;

	}

	public void ConvexHall(Coordinate point[]) {

		// creating stack
		Stack<Coordinate> stack = new Stack<Coordinate>();

		// assuming that the left value is at index 0 of point array
		int left = 0;

		// calculating the length of index
		int n = point.length;

		// we have initialized this loop from 1 because first index is already set as
		// left most
		for (int i = 1; i < n; i++) {

			// determining if the value of x is greater or smaller than other
			if (point[i].x < point[left].x) {
				// if value at x coordinate of one point is smaller than other then changing
				// that to smaller
				left = i;
			}
		}

		// assuming or let that p is index which is at left most side in the graph of
		// point
		int p = left;

		// finding the convex hall points
		// starting do while loop to find if the other values in comparison to point[p]
		// is at left
		do {
			// pushing a value that is determined to be left in stack, by default point[p]
			// is at left
			stack.add(point[p]);

			// we have use modulo here because if we increment then it might be out of bound
			int q = (p + 1) % n;

			// after determining point p and q we need r point in order to find the
			// direction of coordinate so
			for (int r = 0; r < n; r++) {
				// assuming other remaining point as r and comparing it with p and q if that is
				// at left or right or anti-clockwise or clockwise
				if (direction(point[p], point[q], point[r]) > 0) { // sending the value in order to find if the
																	// determinant is greater or smaller than 0

					// now if r is greater than 0 we assume that next q is r and again we will
					// search for r in next iteration
					q = r;
				}
			}
			// if every iteration is complete we have to replace current q point to make it
			// q and again it will run the do while loop
			p = q;

		}
		// we are running this loop till we come to the point where this loop was
		// started
		while (p != left);

		// display the convex hall points

		for (Coordinate i : stack) { // copying all the value in stack and assigning that to i variable
			System.out.println("Convex Hall Point (" + i.x + "," + i.y + ")");
		}
	}// Convex hall method end

}
