import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private int n = 0;
    private Item[] items;
    public RandomizedQueue()                 // construct an empty randomized queue
    {
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty()                 // is the randomized queue empty?
    {
        return n == 0;
    }

    public int size()                        // return the number of items on the randomized queue
    {
        return n;
    }
    

    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size(); i++)
            copy[i] = items[i];
        items = copy;
    }

    public void enqueue(Item item)           // add the item
    {
        if (item == null)
            throw new IllegalArgumentException();

        if (items.length == n)
            resize(items.length * 2);
        items[n++] = item;
    }

    public Item dequeue()                    // remove and return a random item
    {
        if (size() == 0)
            throw new java.util.NoSuchElementException();

        int i = StdRandom.uniform(n);
        Item item = items[i];
        items[i] = items[n-1];
        
        if (n > 0 && n == items.length/4) {
            resize(n * 2);
        }
        n--;
        return item;
    }

    public Item sample()                     // return a random item (but do not remove it)
    {
        if (size() == 0)
            throw new java.util.NoSuchElementException();

        return items[StdRandom.uniform(n)];
    }

    private class RandomIterator implements Iterator<Item>
    {
        private int i = 0;
        private int[] indices;

        public RandomIterator()
        {
            indices = new int[n];
            for (int j = 0; j < n; j++)
                indices[j] = j;
            StdRandom.shuffle(indices);
        }

        @Override
        public boolean hasNext()
        {
            return i < n;
        }

        @Override
        public Item next()
        {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            return items[indices[i++]];
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }

    }

    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new RandomIterator();
    }

    /*  
    public static void main(String[] args)
    {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) 
        {
            String s = StdIn.readString();
            rq.enqueue(s);
        }

        for (String i : rq)
            StdOut.println(i);
    }
    */
}