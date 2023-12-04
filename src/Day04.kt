import kotlin.math.pow

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

    fun part1(input: List<String>): Int {
        val cardMatches = input.map { parseCard(it) }
        return cardMatches.sumOf { 2.0.pow(it - 1).toInt() }
    }

    fun part2(input: List<String>): Int {
        val cardMatches = input.map { parseCard(it) }

        val cards = IntArray(cardMatches.size) { 1 }
        cardMatches.forEachIndexed { index, score ->
            repeat(score) {
                cards[index + it + 1] += cards[index]
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
