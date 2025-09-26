package com.gildedrose;

import com.gildedrose.boot.DaysResolver;
import com.gildedrose.boot.InventoryInputResolver;
import com.gildedrose.inventory.GildedRose;
import com.gildedrose.inventory.exceptions.GildedRoseException;
import com.gildedrose.inventory.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;

class GildedRoseApplicationTest {

    @Mock
    private DaysResolver daysResolver;

    @Mock
    private InventoryInputResolver inventoryInputResolver;

    @InjectMocks
    private GildedRoseApplication application;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void run_shouldUpdateQualityForOneDay_whenDaysIsOne() throws Exception {
        String[] args = {"--days=1"};
        Item[] items = {new Item("Aged Brie", 10, 20)};
        when(daysResolver.parseDaysArgument(args)).thenReturn(1);
        when(inventoryInputResolver.resolve(args)).thenReturn(items);

        try (MockedConstruction<GildedRose> mocked = mockConstruction(GildedRose.class,
                (mock, context) -> doNothing().when(mock).updateQuality())) {
            application.run(args);
        }

        verify(daysResolver).parseDaysArgument(args);
        verify(inventoryInputResolver).resolve(args);
    }

    @Test
    void run_shouldUpdateQualityForMultipleDays_whenDaysGreaterThanOne() throws Exception {
        String[] args = {"--days=5"};
        Item[] items = {new Item("Backstage passes", 15, 20)};
        when(daysResolver.parseDaysArgument(args)).thenReturn(5);
        when(inventoryInputResolver.resolve(args)).thenReturn(items);

        try (MockedConstruction<GildedRose> mocked = mockConstruction(GildedRose.class,
                (mock, context) -> doNothing().when(mock).updateQuality(5))) {
            application.run(args);
        }

        verify(daysResolver).parseDaysArgument(args);
        verify(inventoryInputResolver).resolve(args);
    }

    @Test
    void run_shouldHandleGildedRoseException() throws Exception {
        String[] args = {"--days=3"};
        when(daysResolver.parseDaysArgument(args)).thenThrow(new GildedRoseException("Invalid input"));

        application.run(args);
    }

    @Test
    void run_shouldHandleGenericException() throws Exception {
        String[] args = {"--days=3"};
        when(daysResolver.parseDaysArgument(args)).thenThrow(new RuntimeException("Unknown error"));

        application.run(args);
    }
}
