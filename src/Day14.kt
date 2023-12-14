
fun main() {
    fun part1(input: List<String>): Int {
        return Day14(input).solvePart1()
    }

    fun part2(input: List<String>): Int {
        return Day14(input).solvePart2()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day14_test")
    check(part1(testInput) == 136)
    check(part2(testInput) == 64)

    val input = readInput("Day14")
    check(part1(input) == 110677)
    check(part2(input) == 90551)
}

class Day14(input: List<String>) {

    private val grid: Array<CharArray> = input.map { it.toCharArray() }.toTypedArray()

    private val progressions: Map<Point2D, List<Point2D>> = mapOf(
        Point2D.NORTH to grid.indices.flatMap { y ->
            grid.first().indices.map { x ->
                Point2D(x, y)
            }
        },
        Point2D.WEST to grid.first().indices.flatMap { x ->
            grid.indices.map { y ->
                Point2D(x, y)
            }
        },
        Point2D.SOUTH to grid.indices.reversed().flatMap { y ->
            grid.first().indices.map { x ->
                Point2D(x, y)
            }
        },
        Point2D.EAST to grid.first().indices.reversed().flatMap { x ->
            grid.indices.map { y ->
                Point2D(x, y)
            }
        }
    )

    fun solvePart1(): Int {
        grid.tilt(Point2D.NORTH)
        return grid.score()
    }

    fun solvePart2(goal: Int = 1_000_000_000): Int {
        val seen = mutableMapOf<Int, Int>()
        var cycleNumber = 1
        while (cycleNumber <= goal) {
            grid.cycle()
            when (val state = grid.sumOf { it.joinToString("").hashCode() }) {
                !in seen -> seen[state] = cycleNumber++
                else -> {
                    val cycleLength = cycleNumber - seen.getValue(state)
                    val cyclesRemaining = (goal - cycleNumber) % cycleLength
                    repeat(cyclesRemaining) {
                        grid.cycle()
                    }
                    return grid.score()
                }
            }
        }
        return grid.score()
    }

    private fun Array<CharArray>.cycle() {
        tilt(Point2D.NORTH)
        tilt(Point2D.WEST)
        tilt(Point2D.SOUTH)
        tilt(Point2D.EAST)
    }

    private fun Array<CharArray>.tilt(direction: Point2D) {
        progressions
            .getValue(direction)
            .filter { this[it] == 'O' }
            .forEach { doTilt(it, direction) }
    }

    private fun Array<CharArray>.doTilt(place: Point2D, direction: Point2D) {
        var current = place
        while (isSafe(current + direction) && this[current + direction] == '.') {
            swap(current, current + direction)
            current += direction
        }
    }

    private fun Array<CharArray>.score(): Int =
        mapIndexed { y, row ->
            row.sumOf { c ->
                if (c == 'O') size - y
                else 0
            }
        }.sum()
}
