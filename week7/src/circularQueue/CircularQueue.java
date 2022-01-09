package circularQueue;

public class CircularQueue {
	
	int length;
	int queue[] = new int[6];
	int rear;
	int front;
	int size;
	
	public CircularQueue(int length) {
		
		this.length = length;
	}// CircularQueue Constructor end

	public void enQueue(int value) {
		if (!isFull()) {
			this.queue[this.rear] = value;
			this.rear = (this.rear + 1) % 6;
			this.size = this.size + 1;

		}// if condition end
		else {
			System.out.println("Queue is full");
		}// else condition end
	}// enQueue method end

	public void deQueue() {
		if (!isEmpty()) {
			this.front = (this.front + 1) % 6;
			this.size = this.size - 1;

		}// if condition end
		else {
			System.out.println("Queue is empty");
		}// else condition end
	}// deQueue method end

	public boolean isEmpty() {
		return this.size == 0;
	}// isEmpty method end

	public boolean isFull() {
		return this.size == 6;
	}// isFull method end

	public void display() {
		System.out.println("Rear: " + this.rear);
		System.out.println("Front: " + this.front);
		System.out.println("Size: " + this.size);
		
		System.out.print("Actual Array: ");
		System.out.print("[ ");
		for (int i : this.queue) {
			System.out.print(i + " ");
		}// for loop end
		
		System.out.print("]");
		System.out.println("");
		
		System.out.print("Current Element in Queue: ");
		System.out.print("[ ");
		for (int i = 0; i < this.size; i++) {
			System.out.print(this.queue[(this.front + i) % 6] + " ");
		}// for loop end
		
		System.out.print("]");
	}// display method end

}// CircularQueue class end