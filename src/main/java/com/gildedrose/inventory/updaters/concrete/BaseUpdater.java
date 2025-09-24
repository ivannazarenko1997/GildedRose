package com.gildedrose.inventory.updaters.concrete;

import com.gildedrose.inventory.model.Item;
import com.gildedrose.inventory.updaters.ItemUpdater;

public abstract class BaseUpdater implements ItemUpdater {
    protected static final int MIN_QUALITY = 0;
    protected static final int MAX_QUALITY = 50;

    protected void incQuality(Item item, int by) {
        if (by <= 0)
            return;
        item.quality = Math.min(MAX_QUALITY, item.quality + by);
    }

    protected void decQuality(Item item, int by) {
        if (by <= 0)
            return;
        item.quality = Math.max(MIN_QUALITY, item.quality - by);
    }

    protected void decSellIn(Item item) {
        item.sellIn -= 1;
    }
}