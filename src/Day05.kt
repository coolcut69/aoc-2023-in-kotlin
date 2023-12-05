data class Range(val source: Long, val destination: Long, val spread: Long)

fun main() {
    fun part1(input: List<String>): Long {

        val seeds: List<Long> = input[0].split("seeds: ")[1].split(" ").map { s -> s.toLong() }
        println(seeds)

        val indexOfMaps: MutableList<Int> = ArrayList()
        for ((index, s) in input.withIndex()) {
            if (s.endsWith("map:")) {
                indexOfMaps.add(index)
            }
        }

        val allMaps: MutableList<MutableList<Range>> = ArrayList()

        for ((index, s) in indexOfMaps.withIndex()) {
            val ranges: MutableList<Range> = ArrayList()
            if ((index + 1) < indexOfMaps.size) {
                for (i in s + 1..<(indexOfMaps[index + 1]) - 1) {
                    val numbers = input[i].split(" ")
                    ranges.add(Range(numbers[1].toLong(), numbers[0].toLong(), numbers[2].toLong()))
                }
            } else {
                for (i in s + 1..<input.size) {
                    val numbers = input[i].split(" ")
                    ranges.add(Range(numbers[1].toLong(), numbers[0].toLong(), numbers[2].toLong()))
                }
            }
            allMaps.add(ranges)
        }

        val names = listOf(
            "soil",
            "fertilizer",
            "water",
            "light",
            "temperature",
            "humidity",
            "location"
        )
        val matches = ArrayList<Long>(seeds.size)
        for (seed in seeds) {
            var match = seed
            for ((index, map) in allMaps.withIndex()) {
                var found = false
                for (m in map) {
                    if (!found && LongRange(m.source, m.source + m.spread - 1).contains(match)) {
                        match = match + m.destination - m.source
                        found = true
                    }
                }
            }
            matches.add(match)
        }
        return matches.min()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 35L)
//    check(part2(testInput) == 1)
//
    val input = readInput("Day05")
    check(part1(input) == 226172555L)
//    part2(input).println()
}
