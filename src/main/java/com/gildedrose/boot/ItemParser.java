package com.gildedrose.boot;


import com.gildedrose.inventory.exceptions.GildedRoseException;
import com.gildedrose.inventory.model.Item;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class ItemParser {
    public Item[] fromCsv(String csvPath) {
        Path p = Path.of(csvPath);
        if (!Files.exists(p)) {
            throw new GildedRoseException("CSV not found: " + csvPath);
        }
        List<Item> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(p.toFile()))) {
            String line;
            boolean headerMaybe = true;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (headerMaybe && line.toLowerCase().startsWith("name,")) {
                    headerMaybe = false;
                    continue;
                }
                String[] parts = line.split(",", -1);
                if (parts.length < 3) {
                    throw new GildedRoseException("Invalid CSV line: " + line);
                }
                String name = parts[0].trim();
                int sellIn = Integer.parseInt(parts[1].trim());
                int quality = Integer.parseInt(parts[2].trim());
                list.add(new Item(name, sellIn, quality));
                headerMaybe = false;
            }
        } catch (Exception ex) {
            throw new GildedRoseException("Failed to parse CSV: " + csvPath, ex);
        }
        return list.toArray(new Item[0]);
    }
}
