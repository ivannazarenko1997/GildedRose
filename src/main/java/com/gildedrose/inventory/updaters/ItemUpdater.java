package com.gildedrose.inventory.updaters;

import com.gildedrose.inventory.exceptions.GildedRoseException;
import com.gildedrose.inventory.model.Item;

public interface ItemUpdater {
    void update(Item item) throws GildedRoseException;
}