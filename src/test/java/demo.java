import org.junit.Test;

public class demo {

    @Test
    public void test() {
        String s = "dasfsf";
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            switch (c) {
                case 'a':
                    System.out.println(c + " a case");
                    break;
                default:
                    System.out.println(c + " default case");
            }
        }
    }
}
