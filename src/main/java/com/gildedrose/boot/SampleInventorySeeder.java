package com.gildedrose.boot;



import com.gildedrose.inventory.model.Item;
import org.springframework.stereotype.Component;

@Component
public class SampleInventorySeeder {

    public Item[] sampleItems() {
        return new Item[] {
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 12),
                new Item("Sulfuras, Hand of Ragnaros", -1, 14),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("Conjured Mana Cake", 3, 6)
        };
    }
}
