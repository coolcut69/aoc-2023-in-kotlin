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

    fun part2(time: Long, bestDistance: Long): Long {
        var numberOfPossible = 0L
        for (i in 0..time) {
            val distanceBoat = i * (time - i)
            if (distanceBoat > bestDistance) {
                numberOfPossible++
            }
        }
        return numberOfPossible

    }


    check(part1(listOf(7, 15, 30), listOf(9, 40, 200)) == 288)
    check(part2(71530, 940200) == 71503L)

    check(part1(listOf(44, 70, 70, 80), listOf(283, 1134, 1134, 1491)) == 219849)
    check(part2(44707080, 283113411341491) == 29432455L)
}


