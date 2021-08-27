package LC400_600.LC400_450;

import org.junit.Test;

import java.util.Arrays;

public class LC421_Maximum_XOR_Of_Two_Numbers_In_An_Array {

    @Test
    public void test() {
        int ma = findMaximumXOR(new int[]{4,6,7});
        System.out.println(ma);
    }


    int N = 200010, M = N * 32;
    int[][] son = new int[M][2];
    int idx = 0;

    public int in(int num) {
        int res = 0, p = 0, f = 0;
        boolean flag = false;
        for (int i = 0; i < 32; ++i) {
            int u = (num >> (31 - i)) & 1;
            int v = u == 0 ? 1 : 0;
            // insert
            if (son[p][u] == 0) son[p][u] = ++idx;
            // find
            p = son[p][u];
            if (!flag && son[f][v] != 0) {
                res |= (1 << (31 - i));
                f = son[f][v];
            } else if (!flag && son[f][u] != 0) {
                f = son[f][u];
            } else if (!flag) {
                flag = true;
            }
        }
        return flag == true ? 0 : res;
    }

    public int findMaximumXOR(int[] nums) {
        int m = 0;
        for (int v : nums) m = Math.max(m, in(v));
        return m;
    }
}
