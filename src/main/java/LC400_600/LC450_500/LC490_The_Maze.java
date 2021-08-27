package LC400_600.LC450_500;

import java.util.LinkedList;

public class LC490_The_Maze {

    boolean[][] v = null;
    int m, n;

    public boolean hasPath(int[][] maze, int[] s, int[] d) {
        m = maze.length; n = maze[0].length;
        v = new boolean[m][n];
        return bfs(maze, s, d);
    }

    public boolean bfs(int[][] maze, int[] s, int[] d) {
        LinkedList<int[]> q = new LinkedList<>();
        q.addLast(s);
        v[s[0]][s[1]] = true;
        while (q.size() > 0) {
            int size = q.size();
            while (size-- > 0) {
                int[] p = q.removeFirst();
                if (p[0] == d[0] && p[1] == d[1])
                    return true;
                int x = p[0], y = p[1];
                /* up direction */
                while (--x >= 0 && maze[x][y] == 0) {}
                int[] ne = new int[]{x + 1, y};
                if (!v[x + 1][y]) {
                    q.addLast(ne);
                    v[x + 1][y] = true;
                }

                /* down direction */
                x = p[0];
                while (++x < m && maze[x][y] == 0) {}
                ne = new int[]{x - 1, y};
                if (!v[x - 1][y]) {
                    q.addLast(ne);
                    v[x - 1][y] = true;
                }

                /* left direction */
                x = p[0];
                while (--y >= 0 && maze[x][y] == 0) {}
                ne = new int[]{x, 1 + y};
                if (!v[x][1 + y]) {
                    q.addLast(ne);
                    v[x][1 + y] = true;
                }

                /* right direction */
                y = p[1];
                while (++y < n && maze[x][y] == 0) {}
                ne = new int[]{x, y - 1};
                if (!v[x][y - 1]) {
                    q.addLast(ne);
                    v[x][y - 1] = true;
                }
            }
        }

        return false;
    }
}
