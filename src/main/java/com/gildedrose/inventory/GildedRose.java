package com.gildedrose.inventory;

import com.gildedrose.inventory.exceptions.GildedRoseException;
import com.gildedrose.inventory.factory.ItemUpdaterFactory;
import com.gildedrose.inventory.factory.impl.DefaultItemUpdaterFactory;
import com.gildedrose.inventory.model.Item;
import com.gildedrose.inventory.updaters.ItemUpdater;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GildedRose {
    private static final ItemUpdaterFactory UPDATER_FACTORY = new DefaultItemUpdaterFactory();
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        try {
            System.out.println("name,    sellIn,   quality");
            for (Item item : items) {
                System.out.println(item);
                ItemUpdater itemNext = UPDATER_FACTORY.forName(item.name);
                itemNext.update(item);
            }
        } catch (GildedRoseException e) {
            log.error("GildedRoseException while executing updateQuality method", e);
        } catch (Exception e) {
            log.error("Exception while executing updateQuality method", e);
        }
    }

    public void updateQuality(int days) {
        try {
            for (int j = 0; j < days; j++) {
                System.out.println("-------- day " + j + " --------");
                updateQuality();
                System.out.println();
            }
        } catch (Exception e) {
            log.error("Exception while executing updateQuality method", e);
        }
    }


}