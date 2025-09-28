package com.gildedrose.boot.impl;

import com.gildedrose.boot.InventoryInputResolver;
import com.gildedrose.boot.ItemParser;
import com.gildedrose.boot.SampleInventorySeeder;
import com.gildedrose.inventory.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryInputResolverImpl implements InventoryInputResolver {

    private final ItemParser itemParser;
    private final SampleInventorySeeder sampleInventorySeeder;
    public Item[] resolve(String[] args) {
        String csv = argValue(args, "--csv=");
        if (csv != null && !csv.isBlank()) {
            return itemParser.fromCsv(csv);
        }
        return sampleInventorySeeder.sampleItems();
    }

    public String argValue(String[] args, String prefix) {
        if (args == null) return null;
        for (String a : args) {
            if (a != null && a.startsWith(prefix)) {
                return a.substring(prefix.length()).trim();
            }
        }
        return null;
    }
}
