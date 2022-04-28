import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphMatrix implements Graph
{
    //initial declaration of array graph, edgecount, and visited array
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

    //returns edgecount in constant time
    @Override
    public int edgeCount()
    {
        return edgecount;
    }

    //adds an edge between point v and w, with a given weight
    //edgecount is updated
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
    //edgecount is updated
    @Override
    public void removeEdge(int v, int w)
    {
        array[v][w]=0;
        edgecount--;
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
        //adds the indices of the nodes v shares an edge with
        ArrayList<Integer> neighbor= new ArrayList<>();
        for(int i=0; i<nodeCount();i++)
        {
            if(array[v][i]>0)
            {
                neighbor.add(i);
            }
        }

        //converts values of neighbors from an arraylist to an array
        int[] neighbor2= new int[neighbor.size()];
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

    //Returns a list of integers, starting with Node v, and prints out all of v's nodes,
    // and then the nodes of v's nodes, and so on.
    @Override
    public ArrayList<Integer> BFS(int v)
    {
        ArrayList<Integer> BFS=new ArrayList<>();
        BFS.add(v);
        Visited[v]=true;

        //Creates queue of Nodes to visit and find the neighbors of
        Queue<Integer> toVisit=new LinkedList<>();
        toVisit.add(v);

        //BFS is added to, and Visited is updated until the toVisit queue is empty
        while(!toVisit.isEmpty())
        {
            int[] list= neighbors(toVisit.remove());
            for(int i=0; i<list.length;i++)
            {
                //If the index has not been visited, it is marked as having been visited, and
                //it is added to the BFS list before its nodes, which are added to toVisit to be
                // checked for neighbors
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
        //rests visited to make sure no errors are made during BFS
        //BFS(v) is run to check if it contains w
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
        resetVisited();

        ArrayList<Integer> topo= new ArrayList<>();

        //finds Node that have no pointers towards it
        int i=0;
        boolean in=false;
        while(i<array.length)
        {
            for(int j=0; j<array.length;j++)
            {
                if(array[i][j]>0)
                {
                    in=true;
                }
            }
            if(in==true)
            {
                topo.add(i);
            }

            i++;
            in=false;

        }

        for(int j=0; j<topo.size();j++)
        {
            int[] list= neighbors(j);
            Visited[j]=true;

            for(int k=0; k<list.length;k++)
            {
                if(Visited[list[k]]==false)
                {
                    Visited[list[i]]=true;
                    topo.add(list[i]);
                }
            }
        }

        boolean allVisited=false;
        while(allVisited==false)
        {


            allVisited=true;
            for(int j=0; j< Visited.length;j++)
            {
                if(Visited[j]==false)
                {
                    allVisited=false;
                }
            }
        }

        return null;
    }
}
