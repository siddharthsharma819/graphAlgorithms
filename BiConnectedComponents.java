import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Graph{
    private int v;
    ArrayList al[];
    int time=0,count=0;
    class Edge
		{
			int u;
			int v;
			Edge(int x,int y)
			{
				this.u=x;
				this.v=y;
			}
			public boolean equals(Edge b)
			{
				if(b.u==this.u && b.v==this.v)
					return true;
				else
					return false;
			}
			public String toString()
			{
				return u+"--"+v;
			}
		}
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
    void bcc()
    {
        boolean visited[]=new boolean[this.v];
        int parent[]=new int[this.v];
        int low[]=new int[this.v];
        int disc[]=new int[this.v];
        boolean ap[]=new boolean[this.v];
		Stack<Edge> stk=new Stack<Edge>();
        for(int i=0;i<visited.length;i++)
        {
            disc[i] = -1;
			low[i] = -1;
			parent[i] = -1;
        }
        int flag=0;
        for(int i=0;i<visited.length;i++)
        {
            flag=0;
            if(!visited[i])
                dfs(i,visited,parent,low,disc,stk);
            if(stk.size()>0)
            {
                flag=1;
                this.count++;
            }
		    while(stk.size()>0)
			    System.out.print(""+stk.pop()+' ');
			if(flag!=0)
			    System.out.println();
        }
    }
    void dfs(int u,boolean visited[],int parent[],int low[],int disc[],Stack<Edge> stk)
    {
        visited[u]=true;
        low[u]=disc[u]=(++time);
        int children=0;
        Iterator<Integer> itr=al[u].iterator();
        while(itr.hasNext())
        {
            int v=itr.next();
            if(!visited[v])
            {
                children++;
                parent[v]=u;
				stk.push(new Edge(u,v));
                dfs(v,visited,parent,low,disc,stk);
                low[u]=Math.min(low[u],low[v]);
                if( (parent[u]!=-1 && low[v]>=disc[u]) || (parent[u]==-1 && children>1) )
                {
                    this.count++;
					while(!(stk.peek().equals(new Edge(u,v))))
						System.out.print(""+stk.pop()+' ');
					System.out.println(stk.pop());
				}
            }
            else if(v!=parent[u] && low[u] > disc[v])
            {
                low[u]=Math.min(low[u],disc[v]);
                stk.push(new Edge(u,v));
            }
        }
    }
}
class Test
{
	public static void main (String[] args) throws java.lang.Exception
	{
	    Graph g = new Graph(12);
        g.addEdge(0,1);
        g.addEdge(1,0);
        g.addEdge(1,2);
        g.addEdge(2,1);
        g.addEdge(1,3);
        g.addEdge(3,1);
        g.addEdge(2,3);
        g.addEdge(3,2);
        g.addEdge(2,4);
        g.addEdge(4,2);
        g.addEdge(3,4);
        g.addEdge(4,3);
        g.addEdge(1,5);
        g.addEdge(5,1);
        g.addEdge(0,6);
        g.addEdge(6,0);
        g.addEdge(5,6);
        g.addEdge(6,5);
        g.addEdge(5,7);
        g.addEdge(7,5);
        g.addEdge(5,8);
        g.addEdge(8,5);
        g.addEdge(7,8);
        g.addEdge(8,7);
        g.addEdge(8,9);
        g.addEdge(9,8);
        g.addEdge(10,11);
        g.addEdge(11,10);
 
        g.bcc();
 
        System.out.println("Above are " + g.count +
                           " biconnected components in graph");
	}
}
