import kotlin.math.min

data class Range(val source: Long, val destination: Long, val spread: Long)

fun main() {
    fun parseInputToMapsOfRanges(input: List<String>): MutableList<MutableList<Range>> {
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
        return allMaps
    }

    fun part1(input: List<String>): Long {
        val allMaps: MutableList<MutableList<Range>> = parseInputToMapsOfRanges(input)

        var lowest = Long.MAX_VALUE
        for (seed in input[0].split("seeds: ")[1].split(" ").map { s -> s.toLong() }) {
            var match = seed
            for (map in allMaps) {
                var found = false
                for (m in map) {
                    if (!found && LongRange(m.source, m.source + m.spread - 1).contains(match)) {
                        match = match + m.destination - m.source
                        found = true
                    }
                }
            }
            lowest = min(lowest, match)
        }
        return lowest
    }

    fun part2(input: List<String>): Long {
        val allMaps: MutableList<MutableList<Range>> = parseInputToMapsOfRanges(input)

        val seeds: List<Long> = input[0].split("seeds: ")[1].split(" ").map { s -> s.toLong() }
        val toList = seeds.chunked(2).map { LongRange(it[0], it[0] + it[1] - 1) }.toList()

        var lowest = Long.MAX_VALUE
        for (longRange in toList) {
            for (seed in longRange.asSequence()) {
                var match = seed
                for (map in allMaps) {
                    var found = false
                    for (m in map) {
                        if (!found && LongRange(m.source, m.source + m.spread - 1).contains(match)) {
                            match = match + m.destination - m.source
                            found = true
                        }
                    }
                }
                lowest = min(lowest, match)
            }
        }
        return lowest
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 35L)
    check(part2(testInput) == 46L)
//
    val input = readInput("Day05")
    check(part1(input) == 226172555L)
//    part2(input).println()
}
