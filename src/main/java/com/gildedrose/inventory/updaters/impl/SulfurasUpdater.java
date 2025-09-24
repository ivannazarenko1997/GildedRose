package com.gildedrose.inventory.updaters.impl;

import com.gildedrose.inventory.exceptions.GildedRoseException;
import com.gildedrose.inventory.model.Item;
import com.gildedrose.inventory.updaters.concrete.BaseUpdater;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class SulfurasUpdater extends BaseUpdater {
    private static final String logTag = "SulfurasUpdater";

    public void update(Item item) {
        try {
            //do nothing
        } catch (Exception e) {
            log.error("Error while executing update method in class:{}", logTag, e);
            throw new GildedRoseException(logTag, e);
        }
    }
}