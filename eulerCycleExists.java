/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;
class Graph
{
    int v;
    ArrayList al[];
    int vin[];
    Graph(int v)
    {
        this.v=v;
        al=new ArrayList[v];
        for(int i=0;i<v;i++)
            al[i]=new ArrayList<Integer>();
        vin=new int[this.v];
    }
    void addEdge(int u,int v)
    {
        al[u].add(v);
    }
    Graph transpose()
    {
        Graph g=new Graph(this.v);
        for(int i=0;i<this.v;i++)
        {
            Iterator<Integer> itr=al[i].iterator();
            while(itr.hasNext())
            {
                int v=itr.next();
                g.addEdge(v,i);
            }
        }
        return g;
    }
    void dfs(int u,boolean visited[])
    {
        visited[u]=true;
        Iterator<Integer> itr=al[u].iterator();
        while(itr.hasNext())
        {
            int v=itr.next();
            vin[v]++;
            if(!visited[v])
                dfs(v,visited);
        }
    }
    boolean scc()
    {
        boolean visited[]=new boolean[this.v];
        dfs(0,visited);
        for(int i=0;i<this.v;i++)
            if(!visited[i])
                return false;
        Graph g=transpose();
        for(int i=0;i<this.v;i++)
            visited[i]=false;
        g.dfs(0,visited);
        for(int i=0;i<this.v;i++)
            if(!visited[i])
                return false;
        return true;
    }
    boolean ec()
    {
        if(!scc())
            return false;
        for(int i=0;i<this.v;i++)
        {
            if(al[i].size()!=vin[i])
                return false;
        }
        return true;
    }
}
/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
	    Graph g = new Graph(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 0);
 
        if (g.ec())
            System.out.println("Given directed graph is eulerian.");
        else
            System.out.println("Given directed graph is NOT eulerian.");// your code goes here
	}
}
