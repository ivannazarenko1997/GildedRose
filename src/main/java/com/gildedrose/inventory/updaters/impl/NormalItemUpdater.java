package com.gildedrose.inventory.updaters.impl;

import com.gildedrose.inventory.exceptions.GildedRoseException;
import com.gildedrose.inventory.model.Item;
import com.gildedrose.inventory.updaters.concrete.BaseUpdater;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class NormalItemUpdater extends BaseUpdater {
    private static final String logTag = "NormalItemUpdater";

    @Override
    public void update(Item item) {
        try {
            decQuality(item, 1);      // day decay
            decSellIn(item);          // end-of-day
            if (item.sellIn < 0) {    // expired: extra decay
                decQuality(item, 1);
            }
        } catch (Exception e) {
            log.error("Error while executing update method in class:{}", logTag, e);
            throw new GildedRoseException(logTag, e);
        }
    }
}