fun main() {
    fun part1(input: List<String>): Int {
        val instructions = input.get(0)

        val map : MutableMap<String, Pair<String, String>> = HashMap()
        for (n in 2..< input.size ) {
            val split = input[n].split(" = ")
            val key = split[0]
            val left = split[1].split(", ")[0].substring(1)
            val right = split[1].split(", ")[1].take(3)
            map[key] = Pair(left, right)
        }

        var count: Int = 0
        var place = "AAA"
        do {
            for (ins in instructions) {
                val pair = map[place]
                place = if (ins == 'L'){
                    pair?.first!!
                } else {
                    pair?.second!!
                }
                count++
            }
        } while (place != "ZZZ")
        return count
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 6)
//    check(part2(testInput) == 1)

    val input = readInput("Day08")
    part1(input).println()
//    part2(input).println()
}
