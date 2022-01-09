package week1;

//Given array of contiguous prime number, return array with missing element in between.
//Input [2, 3, 5, 11, 17, 23]
//Output: [7, 13, 19]


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Prime {

	public int findMaxInArray(int[] givenPrimeNumber) {

		int max = givenPrimeNumber[0];

		for (int i = 0; i < givenPrimeNumber.length; i++) {

			if (givenPrimeNumber[i] > max) {
				max = givenPrimeNumber[i];
				
			} // if condition end
		} // for loop end

		return max;
		
	} // findMaxInArray method end

	public List<Integer> findPrimeNumbers(int n) {
		boolean checkPrime[] = new boolean[n + 1];
		Arrays.fill(checkPrime, true);
		for (int p = 2; p * p <= n; p++) {
			if (checkPrime[p]) {
				for (int i = p * 2; i <= n; i += p) {
					checkPrime[i] = false;
					
				} // for loop end 
			} // if condition end
		}
		List<Integer> primeNumbers = new LinkedList<>();
		for (int i = 2; i <= n; i++) {
			if (checkPrime[i]) {
				primeNumbers.add(i);
			
			} // if condition end
		}// for loop end
		
		return primeNumbers;
	}
	
	public int[] findMissingPrimeNumbers(List<Integer> primeNumbers, int[] givenPrimeNumbers) {
		
 		
		for(int i=0; i<givenPrimeNumbers.length; i++) {
			
			for(int j = 0; j<primeNumbers.size(); j++) {

				int currentNumber = givenPrimeNumbers[i];
				if(primeNumbers.contains(currentNumber)) {
					
					int index = primeNumbers.indexOf(currentNumber);
					primeNumbers.remove(index);
					
				} // if condition end
			}// inner for loop end
		} // outer for loop end
		
		int[] missingPrimeNumbers = new int[primeNumbers.size()];
		for(int i = 0; i<missingPrimeNumbers.length; i++) {
			
			missingPrimeNumbers[i] = primeNumbers.get(i);
			
		} // for loop end
		
		return missingPrimeNumbers;
		
	} // findMissingPrimeNumbers method end

	public static void main(String[] args) {

		Prime obj = new Prime();
		
		int[] givenPrimeNumber = { 2, 3, 5, 11, 17, 23 };
		
		int max = obj.findMaxInArray(givenPrimeNumber);
		
		int[] missingPrimeNumbers = obj.findMissingPrimeNumbers(obj.findPrimeNumbers(max), givenPrimeNumber);
		
		
		System.out.print("[ ");
		for(int i=0; i<missingPrimeNumbers.length; i++){
			
			System.out.print(missingPrimeNumbers[i]+" ");
			
		} // for loop end
		
		System.out.print("]");

	} // main method end

} // Prime class end