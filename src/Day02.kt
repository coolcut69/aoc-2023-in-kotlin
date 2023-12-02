private fun String.findNumber(color: String): Int {
    val filter: String? = this.split(',').find { s -> s.contains(color) }

    return if (filter != null) {
        filter.trim().split(' ')[0].toInt();
    } else {
        0
    }
}

fun main() {
    fun isGamePossible(sets: List<String>): Boolean {
        for (set in sets) {
            if (set.findNumber("blue") > 14 || set.findNumber("red") > 12 || set.findNumber("green") > 13) {
                return false;
            }
        }
        return true
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
