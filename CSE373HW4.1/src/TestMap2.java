/**
 * Created by Artem on 2/20/2017.
 */
public class TestMap2 {

    public static void main(String[] args) {
        Map<Integer, String> test = new HashMap<>();
      /*  System.out.println("Is it empty?: " + test.isEmpty());
        test.put(3, "ab");
        test.put(13, "bac");
        //test.put(13, "new value");
        test.put(33, "vvvvvvv");
        test.put(23, "sfasf");
        System.out.println("Is it empty?: " + test.isEmpty());*/
        //System.out.println(test.get(3));
/*        test.remove(3);
        System.out.println(test.get(13));
        System.out.print(test.size());
        System.out.println(test.containsKey(3));
        System.out.println(test.containsKey(13));
        System.out.println(test.containsKey(23));
        System.out.println(test.keySet());*/
        // Generic put
        test.put(54, "hi");
        test.put(77, "ok");
        test.put(42, "wa");
        test.put(84, "yo");

        // Remove and replace values for dublicate keys
        test.remove(42);
        test.put(54, "ME");
        test.put(-9,"U");
        test.put(37,"ha");

        //Rehash
        test.put(24,"oo");
        test.put(42,"DT");
        // Triggers rehash
        test.put(79, "pp");
        test.remove(37);
        test.remove(24);

        System.out.println(test);




        System.out.println();

    }

}
