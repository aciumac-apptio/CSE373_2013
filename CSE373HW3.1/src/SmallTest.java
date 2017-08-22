/**
 * Created by Artem on 2/19/2017.
 */
public class SmallTest {

    public static void main (String[] args) {
        Deque<String> deque = new ArrayDeque<>();
        deque.addLast("A");
        deque.addLast("B");
        deque.addLast("C");
        deque.addLast("D");
        deque.addLast("E");
        deque.addLast("F");

        System.out.println(deque.toString());

        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();

        System.out.println(deque.toString());

/*        deque.addFirst("M");
        deque.addFirst("N");
        deque.addFirst("O");

        System.out.println(deque.toString());

        deque.addFirst("P");
        deque.addFirst("Q");

        System.out.println(deque.toString());
        System.out.println(deque.peekFirst());
        System.out.println(deque.peekLast());*/


        deque.removeFirst();

        deque.addLast("G");
        deque.addLast("H");
        deque.addLast("I");
        deque.addLast("J");

        System.out.println(deque.toString());

        deque.addLast("K");
        deque.addLast("L");
        deque.addLast("M");
        deque.addLast("N");
        System.out.println(deque.peekLast());

        deque.addLast("O");
        System.out.println(deque.peekLast());


        System.out.println(deque.toString());
        System.out.println(deque.toString());

    }




}
