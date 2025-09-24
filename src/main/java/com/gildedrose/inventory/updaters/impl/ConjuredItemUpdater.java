package com.gildedrose.inventory.updaters.impl;


import com.gildedrose.inventory.exceptions.GildedRoseException;
import com.gildedrose.inventory.model.Item;
import com.gildedrose.inventory.updaters.concrete.BaseUpdater;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ConjuredItemUpdater extends BaseUpdater {
    private static final String logTag = "ConjuredItemUpdater";

    @Override
    public void update(Item item) throws GildedRoseException {
        try {
            decQuality(item, 2);   // before sell date
            decSellIn(item);
            if (item.sellIn < 0) {
                decQuality(item, 2); // extra after sell date
            }
        } catch (Exception e) {
            log.error("Error while executing update method in class:{}", logTag, e);
            throw new GildedRoseException(logTag, e);
        }
    }
}