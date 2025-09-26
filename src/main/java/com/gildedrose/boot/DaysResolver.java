package com.gildedrose.boot;

import com.gildedrose.inventory.exceptions.GildedRoseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DaysResolver {

    public int parseDaysArgument(String[] args) {
        try {
            int days = 1; // default
            if (args != null) {
                for (String a : args) {
                    if (a != null && a.startsWith("--days=")) {
                        String raw = a.substring("--days=".length()).trim();
                        try {
                            days = Integer.parseInt(raw);
                        } catch (NumberFormatException e) {
                            throw new GildedRoseException("Argument --days must be a valid integer", e);
                        }
                    }
                }
            }
            if (days <= 0 || days >= Integer.MAX_VALUE) {
                throw new GildedRoseException("Invalid days value: must be > 0 and < " + Integer.MAX_VALUE);
            }
            log.info("Running updateQuality for {} days", days);
            return days;
        } catch (Exception e) {
            throw new GildedRoseException("Argument must be a valid integer", e);
        }
    }
}
