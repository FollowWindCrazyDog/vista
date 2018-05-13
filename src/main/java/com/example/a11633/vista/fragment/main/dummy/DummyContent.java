package com.example.a11633.vista.fragment.main.dummy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, DummyItem> ITEM_MAP = new HashMap<Integer, DummyItem>();



    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }


    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem{
        public final int id;
        public final int content;
        public final String details;
        public int favorable;
        public int heat;
        public Object val;

        public DummyItem(int id, int content, String details,int favorable,int heat) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.favorable = favorable;
            this.heat = heat;
        }

        public DummyItem(int id, int content, String details, int favorable, int heat, Object val) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.favorable = favorable;
            this.heat = heat;
            this.val = val;
        }

        @Override
        public String toString() {
            return ""+content;
        }

    }

    public static final Comparator<DummyItem> FAVORABLE_COMPARATOR = new FavorableComparator();
    public static final Comparator<DummyItem> HEAT_COMPARATOR = new HeatComparator();

    private static class  FavorableComparator implements Comparator<DummyItem>{
        @Override
        public int compare(DummyItem dummyItem, DummyItem t1) {
            return dummyItem.favorable-t1.favorable;
        }
    }

    private static class  HeatComparator implements Comparator<DummyItem>{
        @Override
        public int compare(DummyItem dummyItem, DummyItem t1) {
            return dummyItem.heat-t1.heat;
        }
    }


}
