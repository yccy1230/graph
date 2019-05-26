import constant.GraphType;
import domain.Graph;

/**
 * 驱动函数
 */
public class Driver {
    public static void main(String[] args) {
        Graph g = Graph.loadGraph("src/main/resources/directed", GraphType.DIRECTED);
        System.out.println("BFS");
        g.BFS(1);
        System.out.println("DFS");
        g.DFS(1);
    }
}
