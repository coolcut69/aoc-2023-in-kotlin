fun main() {
    fun i(time: Long, distance: Long): Long {
        var numberOfPossible = 0L
        for (i in 0..time) {
            val distanceBoat = i * (time - i)
            if (distanceBoat > distance) {
                numberOfPossible++
            }
        }
        return numberOfPossible
    }

    fun part1(times: List<Long>, distance: List<Long>): Long {
        val total: MutableList<Long> = ArrayList()
        for ((index, time) in times.withIndex()) {
            total.add(i(time, distance[index]))
        }
        return total.reduce { accumulator, element ->
            accumulator * element
        }
    }

    fun part2(time: Long, bestDistance: Long): Long {
        return i(time, bestDistance)
    }


    check(part1(listOf(7, 15, 30), listOf(9, 40, 200)) == 288L)
    check(part2(71530, 940200) == 71503L)

    check(part1(listOf(44, 70, 70, 80), listOf(283, 1134, 1134, 1491)) == 219849L)
    check(part2(44707080, 283113411341491) == 29432455L)
}

