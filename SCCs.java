import java.util.*;
import java.lang.*;
import java.io.*;

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
        al[x].add(y);
    }
    void computeft(int u,boolean visited[],LinkedList <Integer> ll)
    {
        visited[u]=true;
        Iterator<Integer> itr=al[u].iterator();
        while(itr.hasNext())
        {
            int v=itr.next();
            if(!visited[v])
                computeft(v,visited,ll);
        }
        ll.addFirst(u);
    }
    void transpose(Graph gt)
    {
        for(int i=0;i<this.v;i++)
        {
            Iterator<Integer> itr=al[i].iterator();
            while(itr.hasNext())
            {
                int v=itr.next();
                gt.addEdge(v,i);
            }
        }
    }
    void dfs(int u,boolean visited[])
    {
        visited[u]=true;
        System.out.print(""+u+' ');
        Iterator<Integer> itr=al[u].iterator();
        while(itr.hasNext())
        {
            int v=itr.next();
            if(!visited[v])
                dfs(v,visited);
        }
    }
    void printSCCs()
    {
        LinkedList<Integer> ll=new LinkedList<Integer>();
        boolean visited[]=new boolean[this.v];
        computeft(0,visited,ll);
        Graph gt=new Graph(this.v);
        transpose(gt);
        visited=new boolean[this.v];
        Iterator<Integer> itr=ll.iterator();
        while(itr.hasNext())
        {
            int u=itr.next();
            if(!visited[u])
            {
                gt.dfs(u,visited);
                System.out.println();
            }
        }
    }
}
class Test
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Graph g=new Graph(5);
		g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
     
        System.out.println("Following are strongly connected components in "+
            "given graph");
        g.printSCCs();// your code goes here
	}
}
