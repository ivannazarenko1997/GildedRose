package com.gildedrose;

import com.gildedrose.inventory.GildedRose;
import com.gildedrose.inventory.model.Item;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

final class GildedRoseTest {

    private static void updateNTimes(Item[] items, int n) {
        GildedRose app = new GildedRose(items);
        for (int i = 0; i < n; i++) app.updateQuality();
    }

    @Test
    void normalItem_degrades_by_1_before_sell_date() {
        Item[] items = { new Item("Normal Item", 5, 10) };
        updateNTimes(items, 1);
        assertEquals(4, items[0].sellIn);
        assertEquals(9, items[0].quality);
    }

    @Test
    void normalItem_degrades_by_2_after_sell_date() {
        Item[] items = { new Item("Normal Item", 0, 10) };
        updateNTimes(items, 1);
        assertEquals(-1, items[0].sellIn);
        assertEquals(8, items[0].quality);
    }

    @Test
    void quality_never_negative() {
        Item[] items = { new Item("Normal Item", 0, 0) };
        updateNTimes(items, 3);
        assertEquals(0, items[0].quality);
    }

    @Test
    void agedBrie_increases_and_capped_at_50() {
        Item[] items = { new Item("Aged Brie", 2, 49) };
        updateNTimes(items, 2);
        assertEquals(0, items[0].sellIn);
        assertEquals(50, items[0].quality);
    }

    @Test
    void agedBrie_increases_twice_after_sell_date() {
        Item[] items = { new Item("Aged Brie", 0, 10) };
        updateNTimes(items, 1);
        assertEquals(-1, items[0].sellIn);
        assertEquals(12, items[0].quality);
    }

    @Test
    void backstage_increases_as_concert_approaches() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20) };
        updateNTimes(items, 1);
        assertEquals(14, items[0].sellIn);
        assertEquals(21, items[0].quality); // +1
    }

    @Test
    void backstage_plus2_when_10_or_less() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 25) };
        updateNTimes(items, 1);
        assertEquals(9, items[0].sellIn);
        assertEquals(27, items[0].quality); // +2
    }

    @Test
    void backstage_plus3_when_5_or_less() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 25) };
        updateNTimes(items, 1);
        assertEquals(4, items[0].sellIn);
        assertEquals(28, items[0].quality); // +3
    }

    @Test
    void backstage_drops_to_zero_after_concert() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 40) };
        updateNTimes(items, 1);
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    void sulfuras_never_changes_and_quality_stays_80() {
        Item[] items = { new Item("Sulfuras, Hand of Ragnaros", 0, 80) };
        updateNTimes(items, 3);
        assertEquals(0, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    void conjured_degrades_twice_as_fast_before_sell_date() {
        Item[] items = { new Item("Conjured Mana Cake", 3, 10) };
        updateNTimes(items, 1);
        assertEquals(2, items[0].sellIn);
        assertEquals(8, items[0].quality); // -2
    }

    @Test
    void conjured_degrades_four_after_sell_date() {
        Item[] items = { new Item("Conjured Mana Cake", 0, 10) };
        updateNTimes(items, 1);
        assertEquals(-1, items[0].sellIn);
        assertEquals(6, items[0].quality); // -4 total
    }

    @Test
    void quality_never_above_50_except_sulfuras() {
        Item[] items = {
                new Item("Aged Brie", 1, 50),
                new Item("Sulfuras, Hand of Ragnaros", 5, 80)
        };
        updateNTimes(items, 2);
        assertEquals(50, items[0].quality);
        assertEquals(80, items[1].quality);
    }
}
