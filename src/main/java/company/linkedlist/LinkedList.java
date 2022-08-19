package company.linkedlist;

public class LinkedList<T> {
    Node<T> head;
    public void insert(T val){
        Node<T> obj = new Node<T>();
        if (head == null) {
            obj.next = null;
        }
        else {
            obj.next = head;
        }
        obj.data = val;
        head = obj;
    }

    public void insertInt(){
        for (int i = 1; i< 10; i++){
            insert((T)(Object) i);
        }
    }

    public void printLinkedList(){
        Node<T> value = head;
        while (value != null){
            System.out.println(value.data);
            value = value.next;
        }
    }

}
