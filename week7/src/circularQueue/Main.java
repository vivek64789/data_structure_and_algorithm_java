package circularQueue;

public class Main {

	public static void main(String[] args) {
		CircularQueue soft = new CircularQueue(6);
		soft.enQueue(110);
		soft.enQueue(220);
		soft.enQueue(330);
		soft.enQueue(440);
		soft.enQueue(550);
		soft.enQueue(660);
		soft.deQueue();
		soft.deQueue();
		soft.enQueue(660);	
		soft.deQueue();
		soft.deQueue();

		soft.display();
		
	}// main method end
}// Main class end
