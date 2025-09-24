package com.gildedrose.inventory.updaters.impl;


import com.gildedrose.inventory.exceptions.GildedRoseException;
import com.gildedrose.inventory.model.Item;
import com.gildedrose.inventory.updaters.concrete.BaseUpdater;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public final class AgedBrieUpdater extends BaseUpdater {
    private static final String logTag = "AgedBrieUpdater";

    @Override
    public void update(Item item) throws GildedRoseException {
        try {
            incQuality(item, 1);
            decSellIn(item);
            if (item.sellIn < 0) {
                incQuality(item, 1);
            }
        } catch (Exception e) {
            log.error("Error while executing update method in class:{}", logTag, e);
            throw new GildedRoseException(logTag, e);
        }
    }
}