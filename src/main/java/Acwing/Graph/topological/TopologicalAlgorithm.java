package Acwing.Graph.topological;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class TopologicalAlgorithm {

    @Test
    public void test() {
        int n = 4;
        int[][] edges = new int[][]{{1,2}, {3,2}, {1,3}, {4,2}};
        topologicalSort(n, edges);
    }


    /**
     * 拓扑排序
     *
     * 建图
     * 找到这么多图节点中 入度为0的节点，让他的后继节点的入度 - 1，重复
     *
     */

    class Node {
        int val;
        int inDegree;
        List<Integer> neis;
        public Node(int v) {
            this.val = v;
            this.inDegree = 0;
            this.neis = new ArrayList<>();
        }
    }

    public Map<Integer, Node> cache = new HashMap<>();

    public void topologicalSort(int n, int[][] edges) {

        /* 创建节点 */
        for (int i = 1; i <= n; ++i) {
            Node node = new Node(i);
            cache.put(i, node);
        }

        /* 添加边和入度 */
        for (int[] edge : edges) {
            Node ori = cache.get(edge[0]), tar = cache.get(edge[1]);
            ori.neis.add(tar.val);
            tar.inDegree++;
        }

        /* topological sort */

        /* 使用一个queue 记录当前入度为0的节点 */
        LinkedList<Integer> q = new LinkedList<>();

        /* 先找出入度为0的节点 */
        for (int key : cache.keySet()) {
            Node node = cache.get(key);
            if (node.inDegree == 0) q.addLast(node.val);
        }

        while (q.size() > 0) {
            int size = q.size();
            while (size -- > 0) {
                Node node = cache.get(q.removeFirst());
                System.out.print(" " + node.val);
                for (int idx : node.neis) {
                    Node nei = cache.get(idx);
                    /* 表明这个邻节点 入度0 */
                    if (--nei.inDegree == 0) q.addLast(idx);
                }
            }
            System.out.println();
        }
    }




}
