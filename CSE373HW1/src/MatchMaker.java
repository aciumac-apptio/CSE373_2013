// TODO: Remove each 'todo' comment once I implement each part!

// TODO: comment header for my program
import java.util.*;

public class MatchMaker {
    // TODO: declare my private fields here
    private Map<String, List<String>> malePreference;
    private Map<String, List<String>> femalePreference;
    private Map<String, Map<String, String>> matches;
    private boolean isStable;
    // Key: "M" -> map of men's engagements, "F" -> map of female's engagements

    // TODO: comment header
    public MatchMaker() {
        // TODO: implement the constructor
        this.malePreference = new TreeMap<String, List<String>>();
        this.femalePreference = new TreeMap<String, List<String>>();
        this.matches = new TreeMap<String, Map<String, String>>();
        this.isStable = false;
    }

    // TODO: comment header
    public void addPerson(String name, String gender, String[] partners) {
        // TODO: implement this method
        if (gender.equalsIgnoreCase("M")) {
            //Place into map of males
            malePreference.put(name, Arrays.asList(partners));
        } else {
            //Place into map of females
            femalePreference.put(name, Arrays.asList(partners));
        }
    }

    // TODO: comment header
    public int getRank(String name, String partner) {
        List<String> mates = new ArrayList<String>();
        if (malePreference.containsKey(name)) {
            mates = malePreference.get(name);
        } else {
            mates = femalePreference.get(name);
        }

        if (mates.indexOf(partner) == -1) {
            return mates.lastIndexOf("") + 1;
        }

        return mates.indexOf(partner) + 1;
    }

    // TODO: comment header
    public boolean isStable() {
        // TODO: implement this method
        return isStable;
    }

    // TODO: comment header
    public void nextRound() {
        // TODO: implement this method
        if (matches.get("M") == null) {
            buildMapOfMaps();
        }
            Set<String> men = malePreference.keySet();
            this.isStable = true;
            for (String male : men) {
                //if man is single and at least one woman LEFT TO CONSIDER (not single)
                if (matches.get("M").get(male).equals("") && !malePreference.get(male).get(malePreference.get(male).size() - 1).equals("")) {
                    this.isStable = false;
                    int index = malePreference.get(male).lastIndexOf("") + 1;
                    String woman = malePreference.get(male).get(index);
                    List<String> temp = malePreference.get(male);
                    temp.set(index, "");
                    malePreference.put(male, temp);
                    // if a given woman is single
                    if (matches.get("F").get(woman).equals("")) {
                        System.out.println(male + " proposes to " + woman);
                        matches.get("M").put(male, woman);
                        matches.get("F").put(woman, male);
                    } else {
                        String currentMan = matches.get("F").get(woman);
                        // If woman likes new guy better than old
                        if (femalePreference.get(woman).indexOf(currentMan) > femalePreference.get(woman).indexOf(male)) {
                            System.out.println(male + " proposes to " + woman);
                            // male and female become engaged
                            matches.get("M").put(male, woman);
                            matches.get("F").put(woman, male);
                            //current man becomes single
                            matches.get("M").put(currentMan, "");
                        }
                    }
                }
            }
    }

    private void buildMapOfMaps() {
        Set<String> men = malePreference.keySet(); //Order Does Matter Here
        Set<String> women = femalePreference.keySet();
        Map<String, String> menMatch = new TreeMap<>();
        Map<String, String> femMatch = new TreeMap<>();

        for (String man: men) {
            menMatch.put(man, "");
        }

        for (String woman : women) {
            femMatch.put(woman, "");
        }

        matches.put("M", menMatch);
        matches.put("F", femMatch);
    }

    // TODO: comment header
    public void printMatches() {
        // TODO: implement this method
        Set<String> men = matches.get("M").keySet();
        Set<String> fem = matches.get("F").keySet();

        // person partner;
        for (String male : men) {
            String woman = matches.get("M").get(male);
            if (!woman.equals("")) {
                System.out.println(male + ": engaged to " +  woman + " (rank " + getRank(male, woman) + ")");
            } else {
                System.out.println(male + ": single");
            }
        }

        for (String female : fem) {
            String man = matches.get("F").get(female);
            if (!man.equals("")) {
                System.out.println(female + ": engaged to " +  man + " (rank " + getRank(female, man) + ")");
            } else {
                System.out.println(female + ": single");
            }
        }
        this.isStable = isStable();
    }
}