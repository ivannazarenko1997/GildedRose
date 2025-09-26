package com.gildedrose;

import com.gildedrose.boot.InventoryInputResolver;
import com.gildedrose.boot.ItemParser;
import com.gildedrose.boot.SampleInventorySeeder;
import com.gildedrose.inventory.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryInputResolverTest {

    @Mock
    private ItemParser itemParser;

    @Mock
    private SampleInventorySeeder sampleInventorySeeder;

    private InventoryInputResolver resolver;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        resolver = new InventoryInputResolver(itemParser, sampleInventorySeeder);
    }

    @Test
    void shouldUseItemParserWhenCsvArgProvided() {
        // Arrange
        String[] args = {"--csv=/path/to/file.csv"};
        Item[] parsedItems = { new Item("Elixir", 5, 7) };
        when(itemParser.fromCsv("/path/to/file.csv")).thenReturn(parsedItems);

        // Act
        Item[] result = resolver.resolve(args);

        // Assert
        assertArrayEquals(parsedItems, result);
        verify(itemParser).fromCsv("/path/to/file.csv");
        verifyNoInteractions(sampleInventorySeeder);
    }

    @Test
    void shouldUseSampleSeederWhenNoCsvArgProvided() {
        // Arrange
        String[] args = {};  // No --csv
        Item[] sampleItems = { new Item("Aged Brie", 2, 0) };
        when(sampleInventorySeeder.sampleItems()).thenReturn(sampleItems);

        // Act
        Item[] result = resolver.resolve(args);

        // Assert
        assertArrayEquals(sampleItems, result);
        verify(sampleInventorySeeder).sampleItems();
        verifyNoInteractions(itemParser);
    }

    @Test
    void shouldHandleNullArgsAsNoCsv() {
        // Arrange
        Item[] sampleItems = { new Item("Sulfuras", 0, 80) };
        when(sampleInventorySeeder.sampleItems()).thenReturn(sampleItems);

        // Act
        Item[] result = resolver.resolve(null);

        // Assert
        assertArrayEquals(sampleItems, result);
        verify(sampleInventorySeeder).sampleItems();
        verifyNoInteractions(itemParser);
    }

    @Test
    void shouldIgnoreBlankCsvValue() {
        // Arrange
        String[] args = {"--csv=   "};
        Item[] sampleItems = { new Item("Backstage passes", 10, 25) };
        when(sampleInventorySeeder.sampleItems()).thenReturn(sampleItems);

        // Act
        Item[] result = resolver.resolve(args);

        // Assert
        assertArrayEquals(sampleItems, result);
        verify(sampleInventorySeeder).sampleItems();
        verifyNoInteractions(itemParser);
    }
}
