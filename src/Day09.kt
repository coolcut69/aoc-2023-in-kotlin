fun main() {

    fun extrapolate(row: List<Int>): Int =
        if (row.all { it == 0 }) 0
        else {
            val differences = row.windowed(2, 1).map { it[1] - it[0] }
            row.last() + extrapolate(differences)
        }

    fun part1(input: List<String>): Int {
        val rows = input.map { it.split(" ").map { i -> i.toInt() } }
        return rows.sumOf { extrapolate(it) }
    }

    fun part2(input: List<String>): Int {
        val rows = input.map { it.split(" ").map { i -> i.toInt() } }
        return rows.map { it.reversed() }.sumOf { extrapolate(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 114)
    check(part2(testInput) == 2)

    val input = readInput("Day09")
    check(part1(input) == 1853145119)
    part2(input).println()
}
