package com.gildedrose;

import com.gildedrose.boot.DaysResolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DaysResolverTest {

    private final DaysResolver resolver = new DaysResolver();

    @Test
    void shouldParseValidDaysArgument() {
        String[] args = {"--days=3"};
        int days = resolver.parseDaysArgument(args);
        assertEquals(3, days);
    }

    @Test
    void shouldReturnDefaultWhenNoArgumentProvided() {
        String[] args = {};
        int days = resolver.parseDaysArgument(args);
        assertEquals(1, days); // or default value defined in your class
    }

    @Test
    void shouldThrowExceptionForInvalidArgument() {
        String[] args = {"--days=abc"};
        assertThrows(RuntimeException.class, () -> resolver.parseDaysArgument(args));
    }
}
