import kotlin.math.pow


data class CardWithValue(val number: Int, val value: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CardWithValue) return false

        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        return number
    }
}

private fun parseCard(input: String): Int {
    val winningNumbers = input.substringAfter(":")
        .substringBefore("|")
        .split(" ")
        .filter { it.isNotEmpty() }
        .toSet()

    val ourNumbers = input.substringAfter("|")
        .split(" ")
        .filter { it.isNotEmpty() }
        .toSet()

    return winningNumbers.intersect(ourNumbers).size
}

fun main() {
    fun getNumbers(s1: String): List<Int> {
        return s1.substring(1).trimEnd().chunked(3).map { s -> s.trim() }.map { s -> s.toInt() }
    }

    fun getWinningCards(card: String): Set<Int> {
        val numbers = card.split(':')[1].split("|")
        val winningNumbers = getNumbers(numbers[0])
        val ownNumbers = getNumbers(numbers[1])
        return winningNumbers.intersect(ownNumbers.toSet())
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { 2.0.pow(getWinningCards(it).size - 1).toInt() }
    }

    fun part2(input: List<String>): Int {
        val cardMatches = input.map { parseCard(it) }

        val cards = IntArray(cardMatches.size) { 1 }
        cardMatches.forEachIndexed { index, score ->
            repeat(score) {
                cards[index+it+1] += cards[index]
            }
        }
        return cards.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 30)

    val input = readInput("Day04")
    check(part1(input) == 23847)
    check(part2(input) == 8570000)
}
