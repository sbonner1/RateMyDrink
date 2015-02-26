package cs.ycp.edu.cs481.ratemydrink.dummy;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aaron on 2/24/2015.
 */
public class DummyBeers {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyBeer> BEERS = new ArrayList<DummyBeer>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyBeer> BEER_MAP = new HashMap<String, DummyBeer>();

    static {
        // Add 3 sample items.
        addItem(new DummyBeer("1", "Budweiser", "Ale", "ABV: 4.5%", 300, "An american classic, wheat ale", 2.0f));
        addItem(new DummyBeer("2", "Yuengling", "Lager", "ABV: 5.0%", 350, "An american lager, a wunnabe craft brew", 3.0f));
        addItem(new DummyBeer("3", "Natural Ice", "Ale", "ABV: 3.0", 250, "The choice of frats, this cheap ale is a water alternative", 2.0f));
    }

    private static void addItem(DummyBeer item) {
        BEERS.add(item);
        BEER_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyBeer {
        public String id;
        public String name;
        public String ABV;
        public String type;
        public float calories;
        public String description;
        public float rating;

        public DummyBeer(String id, String content, String type, String abv, float calories, String description, float rating) {
            this.id = id;
            this.name = content;
            this.type = type;
            this.ABV = abv;
            this.calories = calories;
            this.description = description;
            this.rating = rating;
        }


        @Override
        public String toString() {
            return name;
        }
    }
}
