package LC200_400.LC250_300;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.junit.Test;

import java.util.ArrayList;
import java.util.*;

public class LC269_Alien_Dictionary {

    @Test
    public void test() {
        String[] words = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
        String[] words2 = new String[]{"z", "z"};
        String[] words3 = new String[]{"abc", "ab"};
        String[] words4 = new String[]{"wlasd"};
        //System.out.println(alienOrder(words2));
        //System.out.println(alienOrder(words));
        System.out.println(alienOrder(words4));
    }

    class Node {
        char val;
        List<Character> neis;
        int inDegree;

        public Node (char v) {
            inDegree = 0;
            val = v;
            neis = new ArrayList<>();
        }
    }

    Map<Character, Node> cache = new HashMap<>();
    Set<Character> set = new HashSet<>();

    public void createEdge(char ori, char tar) {
        Node o = cache.computeIfAbsent(ori, k -> new Node(ori));
        Node t = cache.computeIfAbsent(tar, k -> new Node(tar));
        o.neis.add(tar);
        t.inDegree++;
    }

    public void handle(String a, String b) {
        for (int i = 0; i < a.length(); ++i)
            if (set.add(a.charAt(i))) cache.put(a.charAt(i), new Node(a.charAt(i)));

        for (int i = 0; i < b.length(); ++i)
            if (set.add(b.charAt(i))) cache.put(b.charAt(i), new Node(b.charAt(i)));

        if (a.equals(b)) return;

        /* a 的优先级比 b 高 */
        char ori = 'a', tar = 'a';
        int len1 = a.length(), len2 = b.length(), len = Math.min(len1, len2);
        int idx1 = 0, idx2 = 0;
        while (idx1 < len && idx2 < len) {
            if (a.charAt(idx1) == b.charAt(idx2)) {
                ++idx1;
                ++idx2;
            } else {
                ori = a.charAt(idx1);
                tar = b.charAt(idx2);
                createEdge(ori, tar);
                return;
            }
        }
    }

    public String alienOrder(String[] words) {

        /* create graph */
        int len = words.length;
        if (len == 1)
            for (int i = 0; i < words[0].length(); ++i) {
                char c = words[0].charAt(i);
                cache.put(c, new Node(c));
                set.add(c);
            }

        for (int i = 0; i < len; ++i) {
            for (int j = i + 1; j < len; ++j) {
                if (check(words[i], words[j])) handle(words[i], words[j]);
                else return "";
            }
        }

        /* topological sort */
        LinkedList<Character> q = new LinkedList<>();
        for (char key : cache.keySet()) {
            Node node = cache.get(key);
            if (node.inDegree == 0) q.addLast(key);
        }

        /*  */
        StringBuilder sb = new StringBuilder();
        while (q.size() > 0) {
            int size = q.size();
            while (size -- > 0) {
                Node node = cache.get(q.removeFirst());
                sb.append(node.val);
                for (char c : node.neis) {
                    Node nei = cache.get(c);
                    if (-- nei.inDegree == 0) q.addLast(c);
                }
            }
        }
        if (sb.length() != set.size()) return "";
        return sb.toString();
    }

    /* 假设 a 比 b 小 */
    private boolean check(String a, String b) {

        int len1 = a.length(), len2 = b.length(), len = Math.min(len1, len2);
        int idx1 = 0, idx2 = 0;
        while (idx1 < len && idx2 < len) {
            if (a.charAt(idx1) == b.charAt(idx2)) {
                ++idx1;
                ++idx2;
            } else {
                return true;
            }
        }
        if (len1 > idx1) {
            return false;
        } else {
            return true;
        }
    }
}
