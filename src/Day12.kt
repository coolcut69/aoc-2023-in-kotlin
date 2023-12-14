fun main() {
    fun part1(input: List<String>): Long {
        return Day12(input).solvePart1()
    }

    fun part2(input: List<String>): Long {
        return Day12(input).solvePart2()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day12_test")
    check(part1(testInput) == 21L)
    check(part2(testInput) == 525152L)

    val input = readInput("Day12")
    check(part1(input) == 7286L)
    part2(input).println()
}

class Day12(private val input: List<String>) {

    fun solvePart1(): Long =
        input
            .map { parseRow(it) }
            .sumOf { (springs, damage) -> countArrangements(springs, damage) }

    fun solvePart2(): Long =
        input
            .map { parseRow(it) }
            .map { unfold(it) }
            .sumOf { (springs, damage) -> countArrangements(springs, damage) }

    private fun countArrangements(
        springs: String,
        damage: List<Int>,
        cache: MutableMap<Pair<String, List<Int>>, Long> = HashMap()
    ): Long {
        val key = springs to damage
        cache[key]?.let {
            return it
        }
        if (springs.isEmpty()) return if (damage.isEmpty()) 1 else 0

        return when (springs.first()) {
            '.' -> countArrangements(springs.dropWhile { it == '.' }, damage, cache)

            '?' -> countArrangements(springs.substring(1), damage, cache) +
                    countArrangements("#${springs.substring(1)}", damage, cache)

            '#' -> when {
                damage.isEmpty() -> 0
                else -> {
                    val thisDamage = damage.first()
                    val remainingDamage = damage.drop(1)
                    if (thisDamage <= springs.length && springs.take(thisDamage).none { it == '.' }) {
                        when {
                            thisDamage == springs.length -> if (remainingDamage.isEmpty()) 1 else 0
                            springs[thisDamage] == '#' -> 0
                            else -> countArrangements(springs.drop(thisDamage + 1), remainingDamage, cache)
                        }
                    } else 0
                }
            }

            else -> throw IllegalStateException("Invalid springs: $springs")
        }.apply {
            cache[key] = this
        }
    }

    private fun unfold(input: Pair<String, List<Int>>): Pair<String, List<Int>> =
        (0..4).joinToString("?") { input.first } to (0..4).flatMap { input.second }

    private fun parseRow(input: String): Pair<String, List<Int>> =
        input.split(" ").run {
            first() to last().split(",").map { it.toInt() }
        }
}
