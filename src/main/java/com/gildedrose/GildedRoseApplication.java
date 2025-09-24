package com.gildedrose;


import com.gildedrose.inventory.GildedRose;
import com.gildedrose.inventory.exceptions.GildedRoseException;
import com.gildedrose.inventory.model.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class GildedRoseApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(GildedRoseApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            log.info("Starting application GildedRose");
            int days = parseDaysArgument(args);
            Item[] items = createSampleItems();

            GildedRose app = new GildedRose(items);
            if (days == 1) {
                app.updateQuality();
            } else {
                app.updateQuality(31);
            }

            log.info("Finish succesfully application GildedRose");
        } catch (GildedRoseException e) {
            log.info("GildedRoseException while executing application GildedRose", e);
        } catch (Exception e) {
            log.info("Error while executing application GildedRose", e);
        } finally {
            log.info("Exit application GildedRose");
        }
    }

    private Item[] createSampleItems() {
        return new Item[] {
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("Conjured Mana Cake", 3, 6) // not working properly yet
        };
    }

    private int parseDaysArgument(String[] args) {
        try {
            if (args == null || args.length == 0) {
                return 1;
            }
            int days = Integer.parseInt(args[0]);
            if (days <= 0 || days >= Integer.MAX_VALUE) {
                throw new GildedRoseException("Invalid days value: must be > 0 and < " + Integer.MAX_VALUE);
            }
            log.info("Running updateQuality for {} days", days);
            return days;
        } catch (NumberFormatException e) {
            throw new GildedRoseException("Argument must be a valid integer", e);
        }
    }
}
