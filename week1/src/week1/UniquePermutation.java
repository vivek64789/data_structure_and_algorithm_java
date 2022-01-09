package week1;

/*
 * 
 * a)provided set of binary numbers in a linked list, find all unique permutations and return each integer value 
 * of generated permutations as linked list. [3 Marks]
	Input: 1 0 1
	Permutation:
	[1, 0, 1] 5
	[1, 1, 0] 6
	[0, 1, 1] 3
	Output: 5 6 3

*/

import java.util.*;

public class UniquePermutation {

	static LinkedList<Integer> binaryNumber = new LinkedList<Integer>();
	static LinkedList<Integer> currentBinaryNumber = new LinkedList<Integer>();
	static LinkedList<LinkedList<Integer>> permutedBinaryNumber = new LinkedList<LinkedList<Integer>>();
	static LinkedList<Boolean> visited = new LinkedList<Boolean>();
	static String bitValue = "";
	static LinkedList<Integer> binaryOutput = new LinkedList<>();
	static int biNum;
	static int decimalNumber = 0;
	static int reminder = 1;
	static int i = 1;

	static void binaryConversion(String bit) {

		biNum = Integer.parseInt(bit);

		CalculateBinary();

	} // binaryConversion method end

	static void CalculateBinary() {

		while (biNum != 0) {

			reminder = biNum % 10;
			decimalNumber = decimalNumber + reminder * i;
			i = i * 2;
			biNum = biNum / 10;

		} // while loop end

		binaryOutput.add(decimalNumber);

	} // CalculateBinary method end

	static LinkedList<Integer> backTrackingMethod() {

		if (currentBinaryNumber.size() == binaryNumber.size()) {
			permutedBinaryNumber.add(currentBinaryNumber);

			for (int i = 0; i < currentBinaryNumber.size(); i++) {

				bitValue = bitValue + currentBinaryNumber.get(i);

			} // for loop end

			binaryConversion(bitValue);
			bitValue = "";
			decimalNumber = 0;
			i = 1;
			reminder = 1;

		} // if condition end

		for (int i = 0; i < (int) binaryNumber.size(); i++) {

			if (visited.get(i)) {
				continue;
			} // if condition end

			if (i > 0 && (binaryNumber.get(i) == binaryNumber.get(i - 1)) && !visited.get(i - 1)) {
				continue;
			} // if condition end

			visited.set(i, true);

			currentBinaryNumber.add(binaryNumber.get(i));

			backTrackingMethod();

			visited.set(i, false);

			currentBinaryNumber.remove(currentBinaryNumber.size() - 1);

		} // for loop end

		return currentBinaryNumber;

	} // backTrackingMethod method end

	static LinkedList<LinkedList<Integer>> permuteDuplicateBinary() {

		Collections.sort(binaryNumber);

		for (int i = 0; i < binaryNumber.size(); i++) {
			visited.add(false);
		} // for loop end

		backTrackingMethod();

		return permutedBinaryNumber;

	} // permuteDuplicateBinary method end

	public static void printOutput() {
		int size = binaryOutput.size();
		for (int i = 0; i < size; i++) {

			System.out.print(binaryOutput.pop() + "----->");

		} // for loop end
	}// printOutput method end

	public static void main(String[] args) {

		binaryNumber.add(1);
		binaryNumber.add(0);
		binaryNumber.add(1);

		permuteDuplicateBinary();
		printOutput();

	} // main method end
} // UniquePermutation class end
