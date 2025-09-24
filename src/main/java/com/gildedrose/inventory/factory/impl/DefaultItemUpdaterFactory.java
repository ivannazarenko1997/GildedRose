package com.gildedrose.inventory.factory.impl;


import com.gildedrose.inventory.enums.ItemNames;
import com.gildedrose.inventory.factory.ItemUpdaterFactory;
import com.gildedrose.inventory.updaters.ItemUpdater;
import com.gildedrose.inventory.updaters.impl.AgedBrieUpdater;
import com.gildedrose.inventory.updaters.impl.BackstagePassUpdater;
import com.gildedrose.inventory.updaters.impl.ConjuredItemUpdater;
import com.gildedrose.inventory.updaters.impl.NormalItemUpdater;
import com.gildedrose.inventory.updaters.impl.SulfurasUpdater;

public final class DefaultItemUpdaterFactory implements ItemUpdaterFactory {

    @Override
    public ItemUpdater forName(String itemName) {
        ItemNames kind = ItemNames.of(itemName);
        switch (kind) {
            case AGED_BRIE:
                return new AgedBrieUpdater();
            case BACKSTAGE:
                return new BackstagePassUpdater();
            case SULFURAS:
                return new SulfurasUpdater();
            case CONJURED:
                return new ConjuredItemUpdater();
            case OTHER:
            default:
                return new NormalItemUpdater();
        }
    }
}