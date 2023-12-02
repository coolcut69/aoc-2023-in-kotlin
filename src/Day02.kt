private fun String.numberOfCubes(color: String): Int {
    val filter: String? = this.split(',').find { s -> s.contains(color) }
    return filter?.trim()?.split(' ')?.get(0)?.toInt() ?: 0
}

fun main() {
    fun isGamePossible(sets: List<String>): Boolean {
        //only 12 red cubes, 13 green cubes, and 14 blue cubes
        return !sets.any { it.numberOfCubes("blue") > 14 || it.numberOfCubes("red") > 12 || it.numberOfCubes("green") > 13 }
    }

    fun part1(input: List<String>): Int {
        var total: Int = 0
        for (games in input) {
            val gameNumber = games.split(':')[0].substringAfter("Game ").toInt()
            val sets = games.split(':')[1].split(';')
            if (isGamePossible(sets)) {
                total += gameNumber
            }
        }
        return total
    }

    fun part2(input: List<String>): Int {
        var total = 0
        for (games in input) {
            val sets = games.split(':')[1].split(';')
            val maxBlue = sets.stream().map { set -> set.numberOfCubes("blue") }.toList().max()
            val maxGreen = sets.stream().map { set -> set.numberOfCubes("green") }.toList().max()
            val maxRed = sets.stream().map { set -> set.numberOfCubes("red") }.toList().max()
            total += maxBlue * maxGreen * maxRed
        }
        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    check(part1(input) == 2169)
    part2(input).println()
}
