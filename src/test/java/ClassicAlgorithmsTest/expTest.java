package ClassicAlgorithmsTest;

import ClassicAlgorithms.stack.expression.expression;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.sun.tools.doclint.Entity.sub;

public class expTest {

    @Test
    public void test() {
        com(new int[]{10,1,2,7,6,1,5}, 8);
    }

    List<List<Integer>> re = new ArrayList<>();

    public List<List<Integer>> com(int[] c, int t) {
        Arrays.sort(c);
        r(c, t, new ArrayList<>(), 0);
        for (List<Integer> ls : re) {
            for (int i : ls) System.out.print(" " + i);
            System.out.println();
        }
        return re;
    }


    public void r(int[] c, int t, List<Integer> tem, int level) {
        if (t == 0) {
            List<Integer> a = new ArrayList<>();
            for (int i : tem) a.add(i);
            re.add(a);
            return;
        }

        for (int i = level; i < c.length; ++i) {
            if (c[i] > t) break;
            if (i >= 1 && i != level && c[i] == c[i - 1]) continue;
            tem.add(c[i]);
            r(c, t - c[i], tem, 1 + i);
            tem.remove(tem.size() - 1);
        }
    }








}
