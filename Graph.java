import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Graph{
    private int v;
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
    void bfs(int v)
    {
        if(v>this.v)
        {
            System.out.println("Invalid starting vertex");
            return;
        }
        boolean visited[]=new boolean[this.v];
        LinkedList<Integer> q=new LinkedList<Integer>();
        visited[v-1]=true;
        q.add(v-1);
        while(q.size()>0)
        {
            int a=q.poll();
            System.out.print(""+a+' ');
            Iterator<Integer> itr=al[a].iterator();
            while(itr.hasNext())
            {
                int b=itr.next();
                if(!visited[b])
                {
                    visited[b]=true;
                    q.add(b);
                    
                }
            }
        }
    }
}
class Test
{
	public static void main (String[] args) throws java.lang.Exception
	{
	    Graph g=new Graph(4);
	    g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        g.addEdge(3, 4);
        g.addEdge(4, 4);
 
        System.out.println("Following is Breadth First Traversal "+
                           "(starting from vertex 3)");
 
        g.bfs(3);
	}
}
