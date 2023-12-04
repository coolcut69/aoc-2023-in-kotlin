import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        var total: Int = 0
        for (card in input) {
            val numbers = card.split(':')[1].split("|")
            val winningNumbers = numbers[0].substring(1).trimEnd().chunked(3).map { s -> s.trim() }.map { s -> s.toInt() }
            val ownNumbers = numbers[1].substring(1).trimEnd().chunked(3).map { s -> s.trim() }.map { s -> s.toInt() }

            val intersect = winningNumbers.intersect(ownNumbers)
            println(intersect)
            val power :Int = intersect.size -1
            val two: Double = 2.0
            total += two.pow(power).toInt()
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
    part1(input).println()
//    part2(input).println()
}
