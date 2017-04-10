import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */

class Graph
{
    int v;
    ArrayList al[];
    Graph(int v)
    {
        this.v=v;
        al=new ArrayList[v];
        for(int i=0;i<v;i++)
            al[i]=new ArrayList<Integer>();
    }
    void addEdge(int x,int y)
    {
        al[x-1].add(y-1);
    }
    void ts()
    {
        LinkedList<Integer> ll=new LinkedList<Integer>();
        boolean visited[]=new boolean[this.v];
        for(int i=0;i<this.v;i++)
        {
            if(!visited[i])
            {
                dfsTraverse(i,visited,ll);
            }
        }
        Iterator<Integer> itr=ll.iterator();
        while(itr.hasNext())
            System.out.print(""+itr.next()+' ');
    }
    void dfsTraverse(int i,boolean visited[],LinkedList<Integer> ll)
    {
        visited[i]=true;
        Iterator<Integer> itr=al[i].iterator();
        while(itr.hasNext())
        {
            int a=itr.next();
            if(!visited[a])
                dfsTraverse(a,visited,ll);
        }
        ll.addFirst(i);
    }
}
class Test
{
	public static void main (String[] args) throws java.lang.Exception
	{
	    Graph g=new Graph(6);
        g.addEdge(6, 3);
        g.addEdge(6, 1);
        g.addEdge(5, 1);
        g.addEdge(5, 2);
        g.addEdge(3, 4);
        g.addEdge(4, 2);
 
        System.out.println("Following is Topological Sort "+
                           "(starting from vertex 1)");
 
        g.ts();
	}
}