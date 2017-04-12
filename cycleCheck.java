import java.util.*;
import java.lang.*;
import java.io.*;

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
    void addEdge(int u,int v)
    {
        al[u].add(v);
    }
    boolean isCyclic()
    {
        boolean flag=false;
        boolean visited[]=new boolean[this.v];
        for(int i=0;i<this.v;i++)
        {
            boolean rstk[]=new boolean[this.v];
            if(!visited[i])
                flag=flag||dfs(i,visited,rstk);
            if(flag)
                return flag;
        }
        return flag;
    }
    boolean dfs(int u,boolean visited[],boolean rstk[])
    {
        visited[u]=true;
        rstk[u]=true;
        Iterator<Integer> itr=al[u].iterator();
        while(itr.hasNext())
        {
            int v=itr.next();
            if(rstk[v])
                return true;
            if(!visited[v])
                return dfs(v,visited,rstk);
        }
        return false;
    }
}
class Test
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Graph g=new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
 
        if(g.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contain cycle");
	}
}
