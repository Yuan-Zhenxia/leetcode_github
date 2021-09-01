package Acwing.Graph.dijkstra;

import org.junit.Test;

import java.util.*;

public class Dijkstra {

    class Node {
        int val;
        List<int[]> neis;
        public Node(int v) { this.val = v; }
    }

    public Map<Integer, Node> cache = new HashMap<>();
    final int max = Integer.MAX_VALUE;

    public void dijkstra(int[][] edges, int n) {
        int re = 0;
        /* create all nodes */
        for (int i = 1; i <= n; ++i) {
            Node node = new Node(i);
            node.neis = new ArrayList<>();
            cache.put(i, node);
        }

        /* add all edges */
        for (int[] e : edges) {
            int ori = e[0], tar = e[1], len = e[2];
            int[] edge = new int[]{tar, len};
            cache.get(ori).neis.add(edge);
        }

        /* dijkstra */

        /* 使用一个distance数组，来记录到所有点的距离 */
        int[] dis = new int[n + 5];
        Arrays.fill(dis, max);

        /* 跟新第一个节点的边 */
        updateDis(1, dis);

        /* 找出dis中最短的边，那个节点，跟新他的路劲 */
        int times = n - 1;
        while (times-- > 0) {
            int min = max, idx = 0;
            for (int i = 1; i <= n; ++i)
                if (dis[i] != 0 && dis[i] != max && dis[i] < min) {
                    min = dis[i];
                    idx = i;
                }

            if (min == max) {
                /* 没找到跟新的点 */
                System.out.println("-1");
                return;
            }
            re += min;
            /* 跟新这个节点 */
            updateDis(idx, dis);
        }

        System.out.println(re);
    }

    /* 把某个点加入集合 并且跟新距离 */
    public void updateDis(int ori, int[] dis) {
        /* 跟新第一个节点的边 */
        Node o = cache.get(ori);
        dis[o.val] = 0;
        for (int[] edge : o.neis) {
            int tar = edge[0];
            int len = edge[1];
            dis[tar] = dis[tar] > len ? len : dis[tar];
        }
    }


    @Test
    public void test() {
        int[][] edges = new int[][]{{1,2,2}, {2,3,1}, {1,3,4}};
        dijkstra(edges, 3);
    }
}
