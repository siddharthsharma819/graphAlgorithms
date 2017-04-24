import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Graph{
    private int v;
    ArrayList al[];
    int time=0;
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
        al[y].add(x);
    }
    void bridges()
    {
        boolean visited[]=new boolean[this.v];
        int parent[]=new int[this.v];
        int low[]=new int[this.v];
        int disc[]=new int[this.v];
        for(int i=0;i<visited.length;i++)
        {
            parent[i]=-1;
        }
        for(int i=0;i<visited.length;i++)
        {
            time=0;
            if(!visited[i])
                dfs(i,visited,parent,low,disc);
        }
    }
    void dfs(int u,boolean visited[],int parent[],int low[],int disc[])
    {
        visited[u]=true;
        low[u]=disc[u]=(++time);
        Iterator<Integer> itr=al[u].iterator();
        while(itr.hasNext())
        {
            int v=itr.next();
            if(!visited[v])
            {
                parent[v]=u;
                dfs(v,visited,parent,low,disc);
                low[u]=Math.min(low[u],low[v]);
                if(low[v]>disc[u])
                    System.out.println(u+" "+v);
            }
            else if(v!=parent[u])
                low[u]=Math.min(low[u],disc[v]);
        }
    }
}
class Test
{
	public static void main (String[] args) throws java.lang.Exception
	{
	    System.out.println("\nBridges in first graph");
        Graph g1=new Graph(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        g1.bridges();
 
        System.out.println("\nBridges in second graph");
        Graph g2=new Graph(4);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        g2.bridges();
 
        System.out.println("\nBridges points in third graph");
        Graph g3=new Graph(7);
        g3.addEdge(0, 1);
        g3.addEdge(1, 2);
        g3.addEdge(2, 0);
        g3.addEdge(1, 3);
        g3.addEdge(1, 4);
        g3.addEdge(1, 6);
        g3.addEdge(3, 5);
        g3.addEdge(4, 5);
        g3.bridges();
	}
}
a