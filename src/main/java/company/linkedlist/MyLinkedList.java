package company.linkedlist;


import company.exceptions.IllegalPositionException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLinkedList<T> {
    Node<T> head;
    private final Logger LOGGER = Logger.getLogger("Logger.info");


    public void insert(T data) {
        Node<T> node = new Node<T>(data);
        if (head == null) {
            this.head = node;
        } else {
            Node<T> last = this.head;
            while (last.next != null) {
                last = last.getNext();
            }
            last.setNext(node);
        }
    }


    public void printLinkedList() {
        Node<T> value = head;
        while (value != null) {
            LOGGER.log(Level.INFO, String.valueOf(value.data));
            value = value.next;
        }
    }

    public int size() {
        int size = 0;
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
            size++;
        }
        return size;
    }


    public void insertAt(T data, int position) throws IllegalPositionException {
        Node<T> nodeToInsert = new Node<T>(data);
        if (position < 0 || position > this.size()) {
            throw new IllegalPositionException("This is an illegal position");
        }
        if (position == 0) {
            nodeToInsert.setNext(head);
            head = nodeToInsert;
        } else {
            Node<T> current = this.head;
            for (int i = 0; i < position - 1; i++) {
                current = current.getNext();
            }
            nodeToInsert.setNext(current.getNext());
            current.setNext(nodeToInsert);
        }
    }

    public void deleteNodeAt(int index) {
        Node<T> node = head;
        for (int i = 0; i < index - 1; i++) {
            node = node.getNext();
        }
        node.setNext(node.getNext().getNext());
    }
}
