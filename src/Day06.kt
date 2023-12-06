fun main() {
    fun part1(times: List<Int>, distance: List<Int>): Int {
        val total: MutableList<Int> = ArrayList()
        for ((index, time) in times.withIndex()) {
            var numberOfPossible = 0
            for (i in 0..time) {
                val distanceBoat = i * (time - i)
                if (distanceBoat > distance[index]) {
                    numberOfPossible++
                }
            }
            total.add(numberOfPossible)
        }
        return total.reduce { accumulator, element ->
            accumulator * element
        }
    }

    fun part2(timeList: List<Int>, distance: List<Int>): Int {
        return 1
    }

    check(part1(listOf(7, 15, 30), listOf(9, 40, 200)) == 288)
//    check(part2(listOf(7, 15, 30), listOf(9, 40, 200)) == 71503)

    part1(listOf(44, 70, 70, 80), listOf(283, 1134, 1134, 1491)).println()
//    part2(listOf(44, 70, 70, 80), listOf(283, 1134, 1134, 1491)).println()
}
