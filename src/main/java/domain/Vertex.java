package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 结点数据结构
 * <p>
 * 包含结点ID 以及 所邻接的边集合
 */
public class Vertex {
    public Integer id;
    public List<Edge> edgeList;

    /**
     * 创建指定ID的结点
     *
     * @param id 结点ID
     */
    public Vertex(Integer id) {
        this.id = id;
        this.edgeList = new ArrayList<Edge>();
    }

    /**
     * 向结点添加边
     *
     * @param edge 边对象
     */
    public void addEdge(Edge edge) {
        edgeList.add(edge);
    }

    /**
     * 覆写toString，方便输出
     */
    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                '}';
    }
}
