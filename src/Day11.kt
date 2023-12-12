import kotlin.math.abs

data class Galaxy(val x: Long, val y: Long)

class Image(private val input: List<String>) {
    val heigth = input.size.toLong()
    val width = input.first().length.toLong()

    private fun isGalaxy(x: Long, y: Long) = input[y.toInt()][x.toInt()] == '#'
    private fun isEmptySpace(x: Long, y: Long) = !isGalaxy(x, y)

    val galaxies = buildList {
        for (x in 0..<width) {
            for (y in 0..<heigth) {
                if (isGalaxy(x, y))
                    add(Galaxy(x, y))
            }
        }
    }

    private fun isExpandingColumn(x: Long) = (0..<heigth).all { y -> isEmptySpace(x, y) }

    val expandingColumns = (0..<width).filter { x -> isExpandingColumn(x) }

    private fun isExpandingRow(y: Long) = (0..<width).all { x -> isEmptySpace(x, y) }

    val expandingRows = (0..<heigth).filter { y -> isExpandingRow(y) }

}

data class ExpandedImage(val ySize: Long, val xSize: Long, val galaxies: List<Galaxy>)

fun expandImage(image: Image, expansionCoefficient: Int): ExpandedImage {
    fun Galaxy.positionAfterExpansion(): Galaxy {
        val yDelta = image.expandingRows.count { it < y }
        val xDelta = image.expandingColumns.count { it < x }
        return Galaxy(
            x + (expansionCoefficient - 1) * xDelta,
            y + (expansionCoefficient - 1) * yDelta
        )
    }

    val newGalaxies = image.galaxies.map { it.positionAfterExpansion() }
    return ExpandedImage(
        image.heigth + image.expandingRows.size,
        image.width + image.expandingColumns.size,
        newGalaxies
    )
}

fun shortestPath(first: Galaxy, second: Galaxy) = abs(first.x - second.x) + abs(first.y - second.y)

fun findResult(newGalaxies: List<Galaxy>): Long {
    val allPairs = newGalaxies.flatMap { first -> newGalaxies.map { second -> first to second } }
    return allPairs.sumOf { shortestPath(it.first, it.second) } / 2
}


fun main() {
    fun part1(lines: List<String>): Long {
        val galaxies = expandImage(Image(lines), 2).galaxies
        return findResult(galaxies)
    }

    fun part2(lines: List<String>): Long {
        val galaxies = expandImage(Image(lines), 1000000).galaxies
        return findResult(galaxies)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 374L)
    check(part2(testInput) == 82000210L)

    val input = readInput("Day11")
    check(part1(input) == 9684228L)
    check(part2(input) == 483844716556L)
}
