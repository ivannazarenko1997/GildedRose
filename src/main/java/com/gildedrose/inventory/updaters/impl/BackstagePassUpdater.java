package com.gildedrose.inventory.updaters.impl;


import com.gildedrose.inventory.exceptions.GildedRoseException;
import com.gildedrose.inventory.model.Item;
import com.gildedrose.inventory.updaters.concrete.BaseUpdater;
import lombok.extern.slf4j.Slf4j;

/**
 * Backstage: +1 normally, +2 (<=10), +3 (<=5), then 0 after concert.
 */
@Slf4j
public final class BackstagePassUpdater extends BaseUpdater {
    private static final String logTag = "BackstagePassUpdater";

    @Override
    public void update(Item item) throws GildedRoseException {
        try {
            incQuality(item, 1);
            if (item.sellIn <= 10)
                incQuality(item, 1);
            if (item.sellIn <= 5)
                incQuality(item, 1);

            decSellIn(item);

            if (item.sellIn < 0) {
                item.quality = 0;
            }
        } catch (Exception e) {
            log.error("Error while executing update method in class:{}", logTag, e);
            throw new GildedRoseException(logTag, e);
        }
    }
}
