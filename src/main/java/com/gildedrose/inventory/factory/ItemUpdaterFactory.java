package com.gildedrose.inventory.factory;

import com.gildedrose.inventory.updaters.ItemUpdater;

public interface ItemUpdaterFactory {
    ItemUpdater forName(String itemName);
}