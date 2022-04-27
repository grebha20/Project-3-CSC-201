
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphMatrix implements Graph
{
    private int[][] array;
    private int edgecount;
    private boolean[] Visited;

    // creates a Node array of length n
    @Override
    public void init(int n)
    {
        array=new int[n][n];
        edgecount=0;
        Visited=new boolean[nodeCount()];
    }

    //returns amount of Nodes in the array
    @Override
    public int nodeCount()
    {
        return array.length;
    }

    //returns variable edgecount in constant time
    @Override
    public int edgeCount()
    {
        return edgecount;
    }

    //
    @Override
    public void addEdge(int v, int w, int wgt)
    {
        array[v][w]=wgt;
        edgecount++;
    }

    //returns the weight value of array[v][w]
    @Override
    public int getWeight(int v, int w)
    {
        return array[v][w];
    }

    //Sets the weight of array[v][w]
    @Override
    public void setWeight(int v, int w, int wgt)
    {
        array[v][w]=wgt;
    }

    //makes the weight equal to zero, removing the edge
    @Override
    public void removeEdge(int v, int w)
    {
        array[v][w]=0;
    }

    //returns true if there is an edge between v and w
    @Override
    public boolean hasEdge(int v, int w)
    {
        if(array[v][w]>0)
        {
            return true;
        }
        return false;
    }

    //Checks row v for nonzero numbers, adds them to an arraylist, and
    // then returns an array of length arraylist.size()
    @Override
    public int[] neighbors(int v)
    {
        ArrayList<Integer> neighbor= new ArrayList<>();
        for(int i=0; i<nodeCount();i++)
        {
            if(array[v][i]>0)
            {
                neighbor.add(i);
            }
        }
        int[] neighbor2=new int[neighbor.size()];
        for(int j=0; j<neighbor2.length;j++)
        {
            neighbor2[j]=neighbor.get(j);
        }

        return neighbor2;
    }

    //sets all indices in Visited array to false
    @Override
    public void resetVisited()
    {
        for(int i=0; i< Visited.length;i++)
        {
            Visited[i]=false;
        }
    }

    //
    @Override
    public ArrayList<Integer> BFS(int v)
    {
        ArrayList<Integer> BFS=new ArrayList<>();
        BFS.add(v);
        Visited[v]=true;
        Queue<Integer> toVisit=new LinkedList<>();
        toVisit.add(v);

        while(!toVisit.isEmpty())
        {
            int[] list= neighbors(toVisit.remove());
            for(int i=0; i<list.length;i++)
            {
                if(Visited[list[i]]==false)
                {
                    Visited[list[i]]=true;
                    toVisit.add(list[i]);
                    BFS.add(list[i]);
                }
            }
        }

        return BFS;
    }

    //Checks if there is a path from v to w, based on BFS.
    // If the BFS of v contains w, then there is a path
    @Override
    public boolean hasPath(int v, int w)
    {
        resetVisited();
        ArrayList<Integer> bfs=BFS(v);
        if(bfs.contains(w))
        {
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Integer> topologicalSort()
    {
        ArrayList<Integer> bfs= BFS(0);
        return null;
    }
}
