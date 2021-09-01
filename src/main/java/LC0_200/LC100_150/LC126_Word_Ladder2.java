package LC0_200.LC100_150;

import com.sun.javafx.collections.MappingChange;
import org.junit.Test;

import java.util.*;

public class LC126_Word_Ladder2 {


    @Test
    public void test() {
        //System.out.println(calDis("lot", "dog"));

        List<String> path = new ArrayList<>();
        List<String> wordList = new ArrayList<>();
        wordList.add("red"); wordList.add("ted"); wordList.add("rex");
        wordList.add("tex"); wordList.add("tax"); wordList.add("tad");

        List<List<String>> ladders = findLadders("red", "tax", wordList);
        for (List<String> ls : ladders) {
            for (String s : ls) {
                System.out.print(" " + s);
            }
            System.out.println();
        }
    }


    class Node {
        String val;
        List<String> neis;
        public Node (String v) {
            this.val = v;
            this.neis = new ArrayList<>();
        }
    }

    Map<String, List<String>> prev = new HashMap<>();
    /* cache represents the all the words that fit in the current format */
    /* eg. h*t  will have hot, hit, etc. */
    Map<String /* format */, List<String>> cache = new HashMap<>();

    Map<String, Node> id2node = new HashMap<>();

    Map<String, Boolean> used = new HashMap<>();

    List<List<String>> re = new ArrayList<>();

    public List<List<String>> findLadders(String b, String e, List<String> ws) {
        int len = ws.size();
        handle(ws);
        if (!used.containsKey(b)) {
            List<String> ls = new ArrayList<>();
            ls.add(b);
            handle(ls);
        }
        bfs(b, e);
        return re;
    }

    /* path to record the path */
    public void bfs(String b, String e) {
        LinkedList<String> q = new LinkedList<>();
        q.addLast(b);
        used.put(b, true);

        boolean flag = true;
        while (flag && q.size() > 0) {
            int size = q.size();

            Set<String> toBeMarked = new HashSet<>();
            while (size -- > 0) {
                Node node = id2node.get(q.removeFirst());
                if (node.val.equals(e)) {
                    flag = false;
                    continue;
                }
                for (String neiName : node.neis) {
                    if (used.get(neiName)) continue; /* used the node */
                    List<String> pres = prev.computeIfAbsent(neiName, k -> new ArrayList<>());
                    pres.add(node.val);
                    toBeMarked.add(neiName);
                }
            }
            for (String s : toBeMarked) {
                q.addLast(s);
                used.put(s, true);
            }
        }
        if (!flag) buildPath(b, e, new LinkedList<String>());
    }

    /* 使用dfs来找前驱节点，并且建立路径 */
    public void buildPath(String b, String e, LinkedList<String> path) {
        path.addFirst(e);
        List<String> pres = prev.get(e);
        if (e.equals(b)) {
            re.add((List<String>) path.clone());
            path.removeFirst();
            return;
        }
        for (String p : pres) buildPath(b, p, path);
        path.removeFirst();
    }

    /* create node and graph */
    public void handle(List<String> ws) {
        /* asterisk * */
        for (String w : ws) {

            id2node.put(w, new Node(w));
            used.put(w, false);

            int len = w.length();
            char[] cs = w.toCharArray();
            for (int i = 0; i < len; ++i) {
                char c = cs[i];
                cs[i] = '*';
                String format = new String(cs);
                List<String> ls = cache.computeIfAbsent(format, k -> new ArrayList<>());
                /* add w to all it's neighboors */
                for (String id : ls) {
                    Node nei = id2node.get(id);
                    nei.neis.add(w);
                    id2node.get(w).neis.add(nei.val);
                }
                ls.add(w);
                cs[i] = c;
            }
        }
    }

}
