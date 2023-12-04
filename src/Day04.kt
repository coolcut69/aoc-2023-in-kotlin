import kotlin.math.pow

fun main() {
    fun getNumbers(s1: String): List<Int> {
        return s1.substring(1).trimEnd().chunked(3).map { s -> s.trim() }.map { s -> s.toInt() }
    }

    fun part1(input: List<String>): Int {
        var total: Int = 0
        for (card in input) {
            val numbers = card.split(':')[1].split("|")
            val winningNumbers = getNumbers(numbers[0])
            val ownNumbers = getNumbers(numbers[1])

            val intersect = winningNumbers.intersect(ownNumbers.toSet())
            total += 2.0.pow(intersect.size - 1).toInt()
        }

       return total
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
//    check(part2(testInput) == 1)

    val input = readInput("Day04")
    check(part1(input) == 23847)
//    part2(input).println()
}
