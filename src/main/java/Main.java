import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
public class Main {
    public static void main(final String[] args) {
        MyCollection<Integer> a = new MyCollection<>();
        a.add(0);
        a.add(0);
        a.add(0);
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        a.add(0);
        a.add(2);
        a.add(5);
        a.add(4);
        a.add(0);
        System.out.println(Arrays.toString(a.toArray()));
        ArrayList<Integer> n = new ArrayList<Integer>();
        n.add(5);
        n.add(4);
        n.add(0);
        System.out.println(Arrays.toString(n.toArray()));
        System.out.println(a.retainAll(n));
        System.out.println(Arrays.toString(a.toArray()));
    }
}