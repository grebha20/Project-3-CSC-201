import java.util.ArrayList;

public class main
{
    public static void main(String[] args)
    {
        GraphMatrix graph=new GraphMatrix();
        graph.init(8);
        graph.addEdge(0,1,1);
        graph.addEdge(0,2,1);
        graph.addEdge(1,3,1);
        graph.addEdge(1,4,1);
        graph.addEdge(2,5,1);
        graph.addEdge(2,6,1);
        graph.addEdge(2,7,1);
//        int[] neighbor=graph.neighbors(0);
//        for(int i=0; i<neighbor.length;i++)
//        {
//            System.out.println(neighbor[i]);
//        }

        ArrayList<Integer> bfs=graph.BFS(0);
        for(int i=0; i<bfs.size();i++)
        {
            System.out.println(bfs.get(i));
        }

        System.out.println(graph.hasPath(0,6));
        System.out.println(graph.hasPath(1,7));
    }
}
