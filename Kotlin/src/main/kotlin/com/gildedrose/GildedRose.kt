package com.gildedrose

import kotlin.math.max
import kotlin.math.min

operator fun Regex.contains(text: CharSequence): Boolean = this.matches(text)

class GildedRose(var items: List<Item>) {
    companion object {
        const val MAX_QUALITY = 50
        const val MIN_QUALITY = 0
        const val SULFURAS = "Sulfuras, Hand of Ragnaros"
        const val BRIE = "Aged Brie"
        const val BACKSTAGE_PASSES_REGEX = "^Backstage passes.*"
        const val CONJURED_REGEX = "^Conjured.*"
    }


    fun updateQuality() {
        for (i in items.indices) {
            updateQualityForItem(items[i])
        }
    }

    private fun updateQualityForItem(item: Item) {
        when (item.name) {
            BRIE -> brieBehaviour(item)
            SULFURAS -> legendaryBehaviour(item)
            in Regex(CONJURED_REGEX) -> conjuredBehaviour(item)
            in Regex(BACKSTAGE_PASSES_REGEX) -> backstagePassesBehaviour(item)
            else -> defaultBehaviour(item)
        }
    }

    private fun defaultBehaviour(item: Item) {
        item.sellIn = max(item.sellIn - 1, 0)
        if (item.sellIn == 0) {
            item.quality = max(item.quality - 2, MIN_QUALITY)
        } else {
            item.quality = max(item.quality - 1, MIN_QUALITY)
        }
    }

    private fun brieBehaviour(item: Item) {
        item.sellIn = max(item.sellIn - 1, 0)
        item.quality = min(item.quality + 1, MAX_QUALITY)
    }

    private fun legendaryBehaviour(item: Item) {
        // Do nothing
    }

    private fun backstagePassesBehaviour(item: Item) {
        item.sellIn = max(item.sellIn - 1, MIN_QUALITY)
        if (item.sellIn == 0) {
            item.quality = MIN_QUALITY
        } else if (item.sellIn <= 5) {
            item.quality = min(item.quality + 3, MAX_QUALITY)
        } else if (item.sellIn <= 10) {
            item.quality = min(item.quality + 2, MAX_QUALITY)
        } else {
            item.quality = min(item.quality + 1, MAX_QUALITY)
        }
    }

    private fun conjuredBehaviour(item: Item) {
        item.sellIn = max(item.sellIn - 1, 0)
        item.quality = max(item.quality - 2, MIN_QUALITY)
    }
}


