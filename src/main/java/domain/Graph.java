package domain;

import constant.Constants;
import constant.GraphType;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 图数据结构
 * <p>
 * 包含结点集合（Map：<ID,Vertex>）以及图类型
 */
public class Graph {
    private Map<Integer, Vertex> vertices;
    private GraphType type;

    public Graph() {
        this.vertices = new HashMap<Integer, Vertex>();
    }

    /**
     * 载入有向图数据文件
     *
     * @param filePath 文件路径
     * @param type     图类型（{@link GraphType#DIRECTED,GraphType#DIRECTED}）
     */
    public static Graph loadGraph(String filePath, GraphType type) {
        Graph g = new Graph();
        g.type = type;
        File file = new File(filePath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            Map<Integer, Vertex> vertexMap = new HashMap<Integer, Vertex>();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(Constants.FILE_SPLITTER);
                if (data.length < 2) continue;

                Integer from = Integer.parseInt(data[0]);
                Integer to = Integer.parseInt(data[1]);

                //build edge
                Edge edge = new Edge(from, to);
                for (int i = 2; i < data.length; i++) {
                    edge.addAttr(data[i]);
                }

                //build vertex
                Vertex sourceVertex = vertexMap.get(from);
                Vertex targetVertex = vertexMap.get(to);
                if (sourceVertex == null) sourceVertex = new Vertex(from);
                if (targetVertex == null) targetVertex = new Vertex(to);

                sourceVertex.addEdge(edge);
                vertexMap.put(from, sourceVertex);
                if (type == GraphType.UNDIRECTED) {
                    targetVertex.addEdge(edge.reverse());
                }
                vertexMap.put(to, targetVertex);
            }
            g.vertices.putAll(vertexMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return g;
    }

    /**
     * 广度优先遍历
     *
     * @param source 起始节点ID
     */
    public void BFS(Integer source) {
        //输出序列
        Queue<Vertex> q = new LinkedList<Vertex>();
        //距离及访问标记
        Map<Integer, Integer> distance = new HashMap<Integer, Integer>();
        //加载起始节点
        Vertex sourceVertex = vertices.get(source);
        if (sourceVertex == null) return;
        q.add(sourceVertex);
        distance.put(source, 0);

        while (!q.isEmpty()) {
            //输出队首元素
            Vertex v = q.poll();
            System.out.println(v + ";distance:" + distance.get(v.id));
            Integer curDist = distance.get(v.id) + 1;
            for (Edge e : v.edgeList) {
                //将未被访问的邻居加入输出序列并标记
                if (!distance.containsKey(e.to)) {
                    distance.put(e.to, curDist);
                    q.add(vertices.get(e.to));
                }
            }
        }
    }

    /**
     * 深度优先遍历
     *
     * @param source 起始节点ID
     */
    public void DFS(Integer source) {
        visit(new HashMap<Integer, Boolean>(), source);
    }

    /**
     * 深度优先遍历递归辅助函数
     *
     * @param visited 存储已经访问到的结点
     * @param start   起始访问结点
     */
    public void visit(Map<Integer, Boolean> visited, Integer start) {
        //base case
        if (visited.containsKey(start)) return;
        Vertex sourceVertex = vertices.get(start);
        if (sourceVertex == null) return;
        visited.put(start, true);
        for (Edge e : sourceVertex.edgeList) {
            //visit neighbour recursive
            visit(visited, e.to);
        }
        System.out.println(vertices.get(start));
    }
}
