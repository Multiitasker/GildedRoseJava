package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.GildedRose.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    // general

    @Test
    void itemToStringMethod(){
        Item[] items = new Item[]{ new Item("Golder Pearl", 21, 49)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Golder Pearl, 20, 48", app.items[0].toString());
    }


    @Test
    void itemQualityDecreasesOverTime(){
        Item[] items = new Item[] { new Item("Gold Ore", 3, 17) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(16, app.items[0].quality);
    }

    @Test
    void itemCannotHaveNegativeQuality(){
        Item[] items = new Item[] { new Item("Linen Cloth", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void itemQualityCannotExceed50(){
        Item[] items = new Item[] { new Item(BACKSTAGE_PASS, 3, 48) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void sellByDatePassedDegradesTwiceAsFast(){
        Item[] items = new Item[] { new Item("The One Ring", 0, 48) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(46, app.items[0].quality);
    }

    // Conjured items

    @Test
    void conjuredItemDegradesTwiceAsFast(){
        Item[] items = new Item[] { new Item("Conjured potato", 3, 17) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(15, app.items[0].quality);
    }

    @Test
    void conjuredItemDegradeAfterSellinPassed(){
        Item[] items = new Item[] { new Item("Conjured potato", 0, 17) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(13, app.items[0].quality);
    }

    // Backstage Pass

    @Test
    void backstagePassIncreases(){
        Item[] items = new Item[] { new Item(BACKSTAGE_PASS, 15, 21) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
    }


    @Test
    void backstagePassDateLessThan10IncreaseBy2(){
        Item[] items = new Item[] { new Item(BACKSTAGE_PASS, 8, 23) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(25, app.items[0].quality);
    }

    @Test
    void sellByDatePassedLessThan5IncreaseBy3(){
        Item[] items = new Item[] { new Item(BACKSTAGE_PASS, 4, 23) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(26, app.items[0].quality);
    }

    @Test
    void backstagePassDatePassedQualityZero(){
        Item[] items = new Item[] { new Item(BACKSTAGE_PASS, 0, 48) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    // Aged Brie

    @Test
    void agedBrieQualityIncreases(){
        Item[] items = new Item[] { new Item(AGED_BRIE, 3, 18) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(19, app.items[0].quality);
    }

    @Test
    void agedBrieQualityIncreasesTwiceAsFastWhenExpired(){
        Item[] items = new Item[] { new Item(AGED_BRIE, 0, 26) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(28, app.items[0].quality);
    }

    // Sulfuras

    @Test
    void sulfurasQualityCannotChange(){
            Item[] items = new Item[] { new Item(SULFURAS, 3, 80) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(80, app.items[0].quality);
    }

    @Test
    void sulfurasNeverHasToBeSold(){
        Item[] items = new Item[] { new Item(SULFURAS, 60, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(60, app.items[0].sellIn);
    }

}
