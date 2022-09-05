package company.linkedlist;

public class Node<T> {
    Node<T> next;
    T data;

    public Node(T data) {
        this.data = data;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
    

    public Node<T> getNext() {
        return next;
    }

}
