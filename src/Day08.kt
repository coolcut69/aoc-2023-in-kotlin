private fun parseRouteMap(input: List<String>): Map<String, Pair<String, String>> =
    input
        .drop(2)
        .associate {
            it.substring(0..2) to (it.substring(7..9) to it.substring(12..14))
        }

fun main() {

    fun part1(input: List<String>): Int {
        val instructions = input.get(0)
        val map = parseRouteMap(input)

        var steps: Int = 0
        var place = "AAA"
        do {
            for (ins in instructions) {
                val pair = map[place]
                place = if (ins == 'L'){
                    pair?.first!!
                } else {
                    pair?.second!!
                }
                steps++
            }
        } while (place != "ZZZ")
        return steps
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 6)
//    check(part2(testInput) == 1)

    val input = readInput("Day08")
    check(part1(input) == 17141)
//    part2(input).println()
}
