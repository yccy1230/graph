package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 边数据结构
 * <p>
 * 起点、终点、属性集合
 */
public class Edge {
    public Integer from;
    public Integer to;
    private Map<String, Object> attrs;

    /**
     * 创建一条边
     *
     * @param from 起点ID
     * @param to   终点ID
     */
    public Edge(Integer from, Integer to) {
        this.from = from;
        this.to = to;
        this.attrs = new HashMap<String, Object>();
    }

    /**
     * 新增属性
     *
     * @param key   属性名
     * @param value 属性值
     */
    public void addAttr(String key, Object value) {
        attrs.put(key, value);
    }

    /**
     * 新增属性
     *
     * @param exp 属性表达式（格式：key=value）
     */
    public void addAttr(String exp) {
        if (exp == null) return;
        String[] data = exp.split("=");
        if (data.length < 2) return;
        attrs.put(data[0], data[1]);
    }

    /**
     * 根据 key 获取边上的属性
     *
     * @param key 属性关键字
     */
    public Object getAttr(String key) {
        return attrs.get(key);
    }

    /**
     * 创建一条具有相同边属性，但起点与终点相反的边
     */
    public Edge reverse() {
        Edge e = new Edge(to, from);
        e.attrs = this.attrs;
        return e;
    }
}
