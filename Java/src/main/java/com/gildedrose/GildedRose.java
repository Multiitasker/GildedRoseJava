package com.gildedrose;

import org.apache.commons.lang3.StringUtils;

class GildedRose {

    Item[] items;

    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        for (Item item : items) {
            if (StringUtils.equalsIgnoreCase(item.name, SULFURAS)) {
                //This legendary item is too mighty for its quality to be altered, let alone be sold to peasants!
            } else if (StringUtils.equalsIgnoreCase(item.name, AGED_BRIE)) {
                updateAgedBrie(item);
            } else if (StringUtils.equalsIgnoreCase(item.name, BACKSTAGE_PASS)) {
                updateBackstagePass(item);
            } else if (StringUtils.containsIgnoreCase(item.name, "conjured")) {
                updateConjuredItem(item);
            } else {
                updateGenericItem(item);
            }
        }
    }

    private void improveQuality(Item item, int increase) {
        if (item.quality + increase <= 50) {
            item.quality += increase;
        } else {
            item.quality = 50;
        }
    }

    private void decreaseQuality(Item item, int decrease) {
        if (item.quality - decrease >= 0) {
            item.quality -= decrease;
        } else {
            item.quality = 0;
        }
    }

    private void reduceSellIn(Item item) {
        item.sellIn -= 1;
    }

    private void updateAgedBrie(Item item) {

        if (item.sellIn > 0) {
            improveQuality(item, 1);
        } else {
            improveQuality(item, 2);
        }

        reduceSellIn(item);
    }

    private void updateBackstagePass(Item item) {
        if (item.sellIn <= 0) {
            item.quality = 0;
            return;
        }
        if (item.sellIn <= 10 && item.sellIn > 5) {
            improveQuality(item, 2);
        } else if (item.sellIn <= 5) {
            improveQuality(item, 3);
        } else {
            improveQuality(item, 1);

        }
        reduceSellIn(item);
    }

    private void updateConjuredItem(Item item) {
        if (item.sellIn > 0) {
            decreaseQuality(item, 2);
        } else {
            decreaseQuality(item, 4);
        }

        reduceSellIn(item);
    }

    private void updateGenericItem(Item item) {
        if (item.sellIn > 0) {
            decreaseQuality(item, 1);
        } else {
            decreaseQuality(item, 2);
        }

        reduceSellIn(item);
    }

}