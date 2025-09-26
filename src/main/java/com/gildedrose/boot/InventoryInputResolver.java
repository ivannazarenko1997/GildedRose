package com.gildedrose.boot;

import com.gildedrose.inventory.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryInputResolver {

    private final ItemParser itemParser;
    private final SampleInventorySeeder sampleInventorySeeder;
    public Item[] resolve(String[] args) {
        String csv = argValue(args, "--csv=");
        if (csv != null && !csv.isBlank()) {
            return itemParser.fromCsv(csv);
        }
        return sampleInventorySeeder.sampleItems();
    }

    private String argValue(String[] args, String prefix) {
        if (args == null) return null;
        for (String a : args) {
            if (a != null && a.startsWith(prefix)) {
                return a.substring(prefix.length()).trim();
            }
        }
        return null;
    }
}
