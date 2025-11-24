public class LinkedList
{
    private class Node
    {
    int data;
    Node next;
    }

    Node head;

    public void add(int newvalue)
    {
        Node newNode = new Node();
        newNode.data = newvalue;
        newNode.next = head;
        head = newNode;
    }
}

public void main(String[] args)
{
    LinkedList list = new LinkedList();
    list.add(1);
    list.add(34);
    list.add(56);
    list.add(2);
    list.add(89);
}

