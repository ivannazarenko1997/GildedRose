package com.gildedrose;

import com.gildedrose.inventory.GildedRose;
import com.gildedrose.inventory.exceptions.GildedRoseException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

final class GildedRoseApplicationTest {

    private int invokeParseDaysArgument(String[] args) {
        try {
            GildedRoseApplication app = new GildedRoseApplication();
            Method m = GildedRoseApplication.class.getDeclaredMethod("parseDaysArgument", String[].class);
            m.setAccessible(true);
            Object result = m.invoke(app, (Object) args);
            return (int) result;
        } catch (InvocationTargetException ite) {
            // unwrap the real cause thrown from the method
            if (ite.getCause() instanceof RuntimeException re) throw re;
            throw new RuntimeException(ite.getCause());
        } catch (Exception e) {
            throw new RuntimeException("Reflection failure", e);
        }
    }

    @Test
    void whenArgsNull_returnsDefault1() {
        assertEquals(1, invokeParseDaysArgument(null));
    }

    @Test
    void whenArgsEmpty_returnsDefault1() {
        assertEquals(1, invokeParseDaysArgument(new String[]{}));
    }

    @Test
    void whenValidPositiveInt_returnsThatValue() {
        assertEquals(20, invokeParseDaysArgument(new String[]{"20"}));
    }

    // ---- invalid -> GildedRoseException ----

    @Test
    void whenNonInteger_throwsGildedRoseException() {
        assertThrows(GildedRoseException.class,
                () -> invokeParseDaysArgument(new String[]{"abc"}));
    }

    @Test
    void whenZero_throwsGildedRoseException() {
        assertThrows(GildedRoseException.class,
                () -> invokeParseDaysArgument(new String[]{"0"}));
    }

    @Test
    void whenNegative_throwsGildedRoseException() {
        assertThrows(GildedRoseException.class,
                () -> invokeParseDaysArgument(new String[]{"-5"}));
    }

    @Test
    void whenMaxIntegerOrMore_throwsGildedRoseException() {
        assertThrows(GildedRoseException.class,
                () -> invokeParseDaysArgument(new String[]{String.valueOf(Integer.MAX_VALUE)}));
    }

    @Test
    void nullItemsArray_isNoop_three_days_andDoesNotThrow() {
        GildedRose app = new GildedRose(null);
        assertDoesNotThrow(() -> app.updateQuality(3));
    }
    @Test
    void nullItemsArray_isNoop_andDoesNotThrow() {
        GildedRose app = new GildedRose(null);
        assertDoesNotThrow(() -> app.updateQuality());
    }
}
