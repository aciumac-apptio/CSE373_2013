// TODO: Remove each 'todo' comment once I implement each part!

// TODO: comment header
import java.awt.*;
import java.util.*;

import com.google.common.collect.*;

public class StarChart {
    // TODO: declare my private fields here
    public int width;
    public int height;

    public Table<Double, Double, Star> starCoordinates;
    public BiMap<String, Star> stars;

    public Set<Star> supernovas;
    public Set<Star> unnamedStars;
    public ListMultimap<String, String> constellations;

    // TODO: comment header
    public StarChart(int width, int height) {
        // TODO: implement the constructor
        this.width = width;
        this.height = height;

        this.starCoordinates = HashBasedTable.create();
        this.stars = HashBiMap.create();

        this.supernovas = new HashSet<>();
        this.unnamedStars = new HashSet<>();
        this.constellations = ArrayListMultimap.create();
    }

    // TODO: comment header
    public void addStar(Star star, String name) {
        // TODO: implement this method
        if (name == null) {
            this.unnamedStars.add(star);
        } else {
            this.stars.put(name,star);
        }
        starCoordinates.put(star.getX(), star.getY(), star);
    }

    // TODO: comment header
    public void addConstellation(String[] starNames, String constellationName) {
        // TODO: implement this method
        this.constellations.putAll(constellationName, Arrays.asList(starNames));
    }

    // TODO: comment header
    public String getName(Star star) {
        // TODO: implement this method
        if (stars.containsValue(star)){
            BiMap<Star, String> reversed = stars.inverse();
            return reversed.get(star);
        } else {
            return null;
        }
    }

    // TODO: comment header
    public Star getStar(double x, double y) {
        // TODO: implement this method
        if (starCoordinates.contains(x,y)) {
            return starCoordinates.get(x,y);
        } else {
            return null;
        }
    }

    // TODO: comment header
    public Star getStar(String name) {
        // TODO: implement this method
        if (stars.keySet().contains(name)) {
            return stars.get(name);
        } else {
            return null;
        }
    }

    // TODO: comment header
    public void draw(Graphics g, boolean showStarNames) {
        // TODO: implement this method
        Set<Star> combined = new HashSet<>();
        combined.addAll(stars.values());
        combined.addAll(unnamedStars);

        for (Star s : combined) {
            if (supernovas.contains(s)) {
                g.setColor(Color.RED);
            } else {
                g.setColor(s.pixelColor());
            }
            g.fillRect(s.pixelX(width), s.pixelY(height), s.pixelSize(), s.pixelSize());
        }

        if (!constellations.isEmpty()) {
            drawConstellations(g, showStarNames);
        }
    }

    private void drawConstellations(Graphics g, boolean showStarNames) {
        Set<String> constNames = constellations.keySet();
        for (String constel : constNames) {
            for (int i = 0; i < constellations.get(constel).size();i+=2) {
                Star star1 = stars.get(constellations.get(constel).get(i));
                Star star2 = stars.get(constellations.get(constel).get(i+1));
                if (showStarNames) {
                    g.setColor(star1.pixelColor());
                    g.drawString(constellations.get(constel).get(i), star1.pixelX(width), star1.pixelY(height));
                    g.setColor(star2.pixelColor());
                    g.drawString(constellations.get(constel).get(i+1),star2.pixelX(width), star2.pixelY(height));
                }

                g.setColor(Color.YELLOW);
                g.drawLine(star1.pixelX(width), star1.pixelY(height),star2.pixelX(width), star2.pixelY(height));
            }
            Star lastStar = stars.get(constellations.get(constel).get(constellations.get(constel).size() - 1));
            g.drawString(constel,lastStar.pixelX(width), lastStar.pixelY(height));
        }
    }

    // TODO: comment header
    public int supernova(Star star) {
        // TODO: implement this method
//        Set<Star> destroyedStars = new HashSet<>();
//        if (supernovas.contains(star)) {
//           return 0;
//        }
//
//        destroyedStars.add(star);
//        destroyedStars = buildSupernovaRange(star, destroyedStars);
//        supernovas.addAll(destroyedStars);
//
//        return destroyedStars.size();

       Queue<Star> starsToProcess = new LinkedList<>();
       starsToProcess.add(star);
       Set<Star> starsToStore = new HashSet<>();
       starsToStore.add(star);

       starsToStore = buildSupernovaRange(starsToProcess,starsToStore);
       supernovas.addAll(starsToStore);

       return starsToStore.size();
    }

/*   private Set<Star> buildSupernovaRange(Star star, Set<Star> destroyedStars) {
        Set<Star> combined = new HashSet<>();
        combined.addAll(stars.values());
        combined.addAll(unnamedStars);

        destroyedStars.add(star);
        for (Star s : combined) {
            if (s.distance(star) <= 0.25 && !destroyedStars.contains(s) && !supernovas.contains(s)) {
                destroyedStars.add(s);
            }
        }
        return destroyedStars;
    }*/

    private Set<Star> buildSupernovaRange(Queue<Star> starsToProcess, Set<Star> starsToStore) {
        if (starsToProcess.isEmpty()) {
            return new HashSet<>();
        } else {
            Star star = starsToProcess.remove();
            Set<Star> combined = new HashSet<>();
            combined.addAll(stars.values());
            combined.addAll(unnamedStars);

            for (Star s : combined) {
                if (s.distance(star) <= 0.079 && !starsToStore.contains(s) && !supernovas.contains(s) && !starsToProcess.contains(s)) {

                    //Make choice
                    starsToStore.add(s);
                    if (!starsToProcess.contains(s) && !s.equals(star)) {
                        starsToProcess.add(s);
                    }

                    //Explore
                    //starsToProcess.add(s);
                    //starsToStore.addAll(buildSupernovaRange(starsToProcess, starsToStore));

                    //Unmake choice
                    //starsToProcess.remove(s);

                }
            }
            supernovas.add(star);

            starsToStore.addAll(buildSupernovaRange(starsToProcess, starsToStore));
            return starsToStore;
        }
    }



}