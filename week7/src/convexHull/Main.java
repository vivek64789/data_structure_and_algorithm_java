package convexHull;

public class Main {

	public static void main(String[] args) {

		// creating the object of Coordinate class
		Coordinate point[] = new Coordinate[7];

		// Sending value to class Coordinate also called as constructor
		point[0] = new Coordinate(0, 0);
		point[1] = new Coordinate(2, 1);
		point[1] = new Coordinate(2, 2);
		point[2] = new Coordinate(3, 2);
		point[3] = new Coordinate(0, 3);
		point[4] = new Coordinate(5, 0);
		point[5] = new Coordinate(2, 4);
		point[6] = new Coordinate(5, 5);

		// creating the object of class GiftWrapping
		GiftWrapping gfw = new GiftWrapping();

		gfw.ConvexHall(point); // sending Array as argument to find the convex hall

	}
}
