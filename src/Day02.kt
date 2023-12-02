private fun String.numberOfCubes(color: String): Int {
    val filter: String? = this.split(',').find { s -> s.contains(color) }
    return filter?.trim()?.split(' ')?.get(0)?.toInt() ?: 0
}

fun main() {
    fun isGamePossible(sets: List<String>): Boolean {
        return !sets.any { it.numberOfCubes("blue") > 14 || it.numberOfCubes("red") > 12 || it.numberOfCubes("green") > 13 }
    }

    fun part1(input: List<String>): Int {
        //only 12 red cubes, 13 green cubes, and 14 blue cubes
        var total: Int = 0
        for (s in input) {
            val gameNumber = s.split(':')[0].substringAfter("Game ").toInt()
            val sets = s.split(':')[1].split(';')
            if (isGamePossible(sets)) {
                total += gameNumber
            }
        }
        return total
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)

    val input = readInput("Day02")
    check(part1(input) == 2169)
    part1(input).println()
//    part2(input).println()
}
