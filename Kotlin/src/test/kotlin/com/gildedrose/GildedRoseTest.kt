package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun foo() {
        val items = listOf(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("foo", app.items[0].name)
    }


    @Test
    fun `test Dexterity Vest`() {
        val items = listOf(Item("+5 Dexterity Vest", 10, 20))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(9, items[0].sellIn)
        assertEquals(19, items[0].quality)
    }

    @Test
    fun `test Aged Brie`() {
        val items = listOf(Item("Aged Brie", 2, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(1, items[0].sellIn)
        assertEquals(1, items[0].quality)
    }

    @Test
    fun `test Elixir of the Mongoose`() {
        val items = listOf(Item("Elixir of the Mongoose", 5, 7))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(4, items[0].sellIn)
        assertEquals(6, items[0].quality)
    }

    @Test
    fun `test Sulfuras, Hand of Ragnaros`() {
        val items = listOf(
            Item("Sulfuras, Hand of Ragnaros", 0, 80),
            Item("Sulfuras, Hand of Ragnaros", -1, 80)
        )
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, items[0].sellIn)
        assertEquals(80, items[0].quality)
        assertEquals(-1, items[1].sellIn)
        assertEquals(80, items[1].quality)
    }

    @Test
    fun `test Backstage passes`() {
        val items = listOf(
            Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            Item("Backstage passes to a TAFKAL80ETC concert", 5, 49)
        )
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(14, items[0].sellIn)
        assertEquals(21, items[0].quality)
        assertEquals(9, items[1].sellIn)
        assertEquals(50, items[1].quality)
        assertEquals(4, items[2].sellIn)
        assertEquals(50, items[2].quality)
    }

    @Test
    fun `test Conjured Mana Cake`() {
        val items = listOf(Item("Conjured Mana Cake", 3, 6))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(2, items[0].sellIn)
        assertEquals(4, items[0].quality)
    }

}


