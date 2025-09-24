package com.gildedrose.inventory.enums;

public enum ItemNames {
    AGED_BRIE("Aged Brie"),
    BACKSTAGE("Backstage passes to a TAFKAL80ETC concert"),
    SULFURAS("Sulfuras, Hand of Ragnaros"),
    CONJURED("Conjured"),
    OTHER("");

    private final String label;

    ItemNames(String label) {
        this.label = label;
    }

    public static ItemNames of(String name) {
        if (name == null)
            return OTHER;
        if (CONJURED.matches(name))
            return CONJURED;
        for (ItemNames t : values()) {
            if (t != CONJURED && t != OTHER && t.matches(name))
                return t;
        }
        return OTHER;
    }

    public String label() {
        return label;
    }

    public boolean matches(String name) {
        if (name == null)
            return false;
        String n = name.trim();
        switch (this) {
            case CONJURED:
                return n.startsWith(label);
            case OTHER:
                return false;
            default:
                return n.equals(label);
        }
    }
}