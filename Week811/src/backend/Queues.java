package backend;

public class Queues {



    public static class Node{

        int data;
        Node next;


        Node(int data){ // Node n = new Node(100); yo call garda constructor
            this.data = data;
            this.next = null;
        }
    }

    Node head = null;
    Node tail = null;

    public void enqueue(int data) {
        Node newNode = new Node(data); // yaha Node banairaxam i mean structure

        if(head==null) {
            head = newNode; // yaha hamile naya node ko address diraxam
            tail = head; // first time node add gardaixaxm soo tail pani head jati hunxa teti nai hunxa
        }
        else {
            // aba next choti feri data pathauda naya node ta banai dinxa
            tail.next = newNode; // aba aaile 104 ko next part ma next node ko address haldim like 104 ko next part ma 108 ani link create hunxa
            tail = newNode; // aaba tail le aghi 104 lai indicate garirathyo but aba naya node ko address hold garxa  aba 108 assign gardiyo

        }
    }

    public int dequeue() {
        int data = head.data;
        head = head.next; // yaha first ko value bahira niskisake paxi feri head chai usko next part ma vako node lai point garxa
        return data;
    }

    public int size() {
        int counter =0;
        Node current = head;
        while(current!=null) {
            counter++;
            current = current.next;
        }
        return counter;
    }


    public void printQueues() {
        Node current = head;
        while(current!=null) {
            System.out.print(current.data +"=>");
            current = current.next;
        }System.out.println("");
    }

    public static void main(String[] args) {
        Queues q = new Queues();

        q.enqueue(20);
        q.enqueue(40);
        q.dequeue();
        q.printQueues();
    }



}
