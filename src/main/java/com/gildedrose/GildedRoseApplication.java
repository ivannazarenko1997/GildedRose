package com.gildedrose;


import com.gildedrose.boot.DaysResolver;
import com.gildedrose.boot.InventoryInputResolver;
import com.gildedrose.inventory.GildedRose;
import com.gildedrose.inventory.exceptions.GildedRoseException;
import com.gildedrose.inventory.model.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class GildedRoseApplication implements CommandLineRunner {
    private final DaysResolver daysResolver;
    private final InventoryInputResolver inventoryInputResolver;
    public static void main(String[] args) {
        SpringApplication.run(GildedRoseApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            log.info("Starting application GildedRose");
            int days = daysResolver.parseDaysArgument(args);
            Item[] items =  inventoryInputResolver.resolve(args);
            GildedRose app = new GildedRose(items);
            if (days == 1) {
                app.updateQuality();
            } else {
                app.updateQuality(days);
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

}
