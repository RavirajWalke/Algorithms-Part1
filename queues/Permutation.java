import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation 
{
    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) 
        {
            String s = StdIn.readString();
            rq.enqueue(s);
        }

        for (String i : rq)
        {
            if (n-- > 0)
                StdOut.println(i);
        }
    }
}
