private class Hand(cards: String, val bid: Int, val jokersWild: Boolean = false) : Comparable<Hand> {
    private val identity: Int = calculateIdentity(
        cards,
        if (jokersWild) STRENGTH_WITH_JOKERS else STRENGTH_WITHOUT_JOKERS,
        if (jokersWild) this::calculateCategoryWithJokers else this::calculateCategoryWithoutJokers
    )

    override fun compareTo(other: Hand): Int =
        this.identity - other.identity

    private fun calculateIdentity(
        cards: String,
        strength: String,
        categoryCalculation: (String) -> List<Int>
    ): Int {
        val category = categoryCalculation(cards)
        return cards.fold(CATEGORIES.indexOf(category)) { acc, card ->
            (acc shl 4) or strength.indexOf(card)
        }
    }

    private fun calculateCategoryWithoutJokers(cards: String): List<Int> =
        cards.groupingBy { it }.eachCount().values.sortedDescending()

    private fun calculateCategoryWithJokers(cards: String): List<Int> {
        val cardsWithoutJokers = cards.filterNot { it == 'J' }
        val numberOfJokers = cards.length - cardsWithoutJokers.length

        return if (numberOfJokers == 5) listOf(5)
        else calculateCategoryWithoutJokers(cardsWithoutJokers).toMutableList().apply {
            this[0] += numberOfJokers
        }
    }

    companion object {
        private val CATEGORIES = listOf(
            listOf(1, 1, 1, 1, 1),
            listOf(2, 1, 1, 1),
            listOf(2, 2, 1),
            listOf(3, 1, 1),
            listOf(3, 2),
            listOf(4, 1),
            listOf(5)
        )

        private const val STRENGTH_WITHOUT_JOKERS = "23456789TJQKA"
        private const val STRENGTH_WITH_JOKERS = "J23456789TQKA"
    }
}


fun main() {
    fun part1(input: List<String>): Int {
        return input
            .asSequence()
            .map { row -> row.split(" ") }
            .map { parts -> Hand(parts.first(), parts.last().toInt()) }
            .sorted()
            .mapIndexed { index, hand -> hand.bid * (index + 1) }
            .sum()
    }

    fun part2(input: List<String>): Int {
       return input
           .asSequence()
           .map { row -> row.split(" ") }
           .map { parts -> Hand(parts.first(), parts.last().toInt(), true) }
           .sorted()
           .mapIndexed { index, hand -> hand.bid * (index + 1) }
           .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 6440)
    check(part2(testInput) == 5905)
//
    val input = readInput("Day07")
    check(part1(input) == 253933213)
    check(part2(input) == 253473930)
}
