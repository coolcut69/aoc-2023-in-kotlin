import kotlin.math.absoluteValue

private fun List<String>.columnToString(column: Int): String =
    this.map { it[column] }.joinToString("")

private infix fun String.diff(other: String): Int =
    indices.count { this[it] != other[it] } + (length - other.length).absoluteValue

fun main() {

    fun createMirrorRanges(start: Int, max: Int): List<Pair<Int, Int>> =
        (start downTo 0).zip(start + 1..max)

    fun findVerticalMirror(pattern: List<String>, goalTotal: Int): Int? =
        (0 until pattern.first().lastIndex).firstNotNullOfOrNull { start ->
            if (createMirrorRanges(start, pattern.first().lastIndex)
                    .sumOf { (left, right) ->
                        pattern.columnToString(left) diff pattern.columnToString(right)
                    } == goalTotal
            ) start + 1
            else null
        }

    fun findHorizontalMirror(pattern: List<String>, goalTotal: Int): Int? =
        (0 until pattern.lastIndex).firstNotNullOfOrNull { start ->
            if (createMirrorRanges(start, pattern.lastIndex)
                    .sumOf { (up, down) ->
                        pattern[up] diff pattern[down]
                    } == goalTotal
            ) (start + 1) * 100
            else null
        }

    fun findMirror(pattern: List<String>, goalTotal: Int): Int =
        findHorizontalMirror(pattern, goalTotal) ?: findVerticalMirror(pattern, goalTotal)
        ?: throw IllegalStateException("Pattern does not mirror")


    fun part1(input: List<String>): Int {
        val patterns = input.joinToString("\n").split("\n\n").map { it.lines() }
        return patterns.sumOf { findMirror(it, 0) }
    }

    fun part2(input: List<String>): Int {
        val patterns = input.joinToString("\n").split("\n\n").map { it.lines() }
        return patterns.sumOf { findMirror(it, 1) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day13_test")
    check(part1(testInput) == 405)
    check(part2(testInput) == 400)

    val input = readInput("Day13")
    check(part1(input) == 33047)
    check(part2(input) == 28806)
}
