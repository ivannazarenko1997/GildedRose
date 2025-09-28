package com.gildedrose.boot;

import com.gildedrose.inventory.model.Item;

public interface InventoryInputResolver {

    Item[] resolve(String[] args);

    String argValue(String[] args, String prefix);
}
