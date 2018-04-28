import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int n;

    public Deque()
    {
        n = 0;
    }
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public boolean isEmpty()
    {
        return n == 0;
    }

    public int size()
    {
        return n;
    }

    public void addFirst(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        if (oldFirst == null)
            last = first;
        else
            oldFirst.prev = first;
        n++;
    }

    public void addLast(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if (oldLast == null)
            first = last;
        else
            oldLast.next = last;
        n++;
    }

    public Item removeFirst()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (first == null)
            last = first;
        else
            first.prev = null;
        n--;
        return item;
    }

    public Item removeLast()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        if (last == null)
            first = last;
        else
            last.next = null;
        n--;
        return item;
    }

    private class DequeIterator implements Iterator<Item>
    {
        private Node current = first;

        @Override
        public boolean hasNext()
        {
            return current != null;
        }

        @Override
        public void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        }

        @Override
        public Item next()
        {
            if (current == null)
            {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }
    
    /*  
    public static void main(String[] args)
    {
        Deque<String> dq = new Deque<String>();
        while (!StdIn.isEmpty()) 
        {
            String s = StdIn.readString();
            dq.addLast(s);
        }

        for (String i : dq)
            StdOut.println(i);
    }
    */    
}