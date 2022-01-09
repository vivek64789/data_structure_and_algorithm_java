package backend;

public class LinkedList {

    Node head = null;
    Node tail = null;

    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public void addNode(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            tail = head;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
    }

    public int size(){
        int counter = 0;
        Node current = head;
        while(current!=null){
            counter++;
            current = current.next;
        }
        return  counter;
    }

    public int get(int i){
        Node current = head;
        if (size()>0 && i<size()){
            for (int j = 0; j<i; j++){
                current = current.next;
            }return current.data;
        }
        return -1;
    }

    public int getindex(int sendData){
        Node current = head;
        if(size()>0){
            for(int j=0; j<size(); j++){
                if (current.data == sendData){
                    return j;
                }
                current = current.next;
            }
        }
        return -1;
    }

}
