import kotlin.math.abs

data class Number(val value: Int, val row: Int, val position: Pair<Int, Int>)

fun extractNumbers(index: Int, line: String) = Regex("(\\d++)").findAll(line).map {
    Number(it.value.toInt(), index, Pair(it.range.first, it.range.last))
}.toList()

private fun Number.isPartNumber(spec: List<String>): Boolean {
    try {
        val line = spec[row]
        val leftSymbol = line[position.first - 1]
        val rightSymbol = line[position.second + 1]

        val lineAbove = spec[row - 1]
        val symbolsAbove = lineAbove.substring(position.first - 1, position.second + 2)
        val lineBelow = spec[row + 1]
        val symbolsBelow = lineBelow.substring(position.first - 1, position.second + 2)

        val hasSymbolOnTheLeft = leftSymbol != '.'
        val hasSymbolOnTheRight = rightSymbol != '.'

        val hasSymbolsAbove = symbolsAbove.any { it != '.' && !it.isDigit() }
        val hasSymbolsBelow = symbolsBelow.any { it != '.' && !it.isDigit() }


        return hasSymbolOnTheLeft || hasSymbolOnTheRight || hasSymbolsAbove || hasSymbolsBelow
    } catch (e: Exception){
        return false
    }
}

private fun GearSymbol.toGearOrNull(numbers: List<Number>): Gear? {
    val adjacentNumbers = findAdjacentNumbers(numbers)
    return if (adjacentNumbers.size == 2) {
        Gear(adjacentNumbers[0].value * adjacentNumbers[1].value)
    } else {
        null
    }
}

private fun GearSymbol.findAdjacentNumbers(numbers: List<Number>): List<Number> {
    val adjacentNumbers: List<Number> =
        numbers.filter {
            abs(it.row - row) < 2 && position in it.position.first - 1..it.position.second + 1
        }
    return adjacentNumbers
}


@JvmInline
value class Gear(val ratio: Int)
data class GearSymbol(val row: Int, val position: Int)




fun main() {
    fun findNumbers(spec: List<String>): List<Number> {
        val numbers = mutableListOf<Number>()
        spec.forEachIndexed { index, it ->
            numbers.addAll(extractNumbers(index, it))
        }
        return numbers
    }

    fun extractGearSymbols(index: Int, line: String) = Regex("\\*").findAll(line).map {
        GearSymbol(index, it.range.first)
    }.toList()

    fun findGears(spec: List<String>): List<Gear> {
        val symbols = spec.flatMapIndexed { index, it ->
            extractGearSymbols(index, it)
        }
        val numbers = findNumbers(spec)
        return symbols.mapNotNull { it.toGearOrNull(numbers) }
    }

    fun part1(engineSpec: List<String>): Int {
        val sum = findNumbers(engineSpec).sumOf {
            if (it.isPartNumber(engineSpec)) it.value else 0
        }
        println("Answer for part 1: $sum")
        return sum
    }

    fun part2(engineSpec: List<String>): Int {
        val sum = findGears(engineSpec).sumOf {
            it.ratio
        }
        println("Answer for part 2: $sum")
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test").wrap()
    check(part1(testInput) == 4361)
    check(part2(testInput) == 467835)

    val input = readInput("Day03").wrap()
    check(part1(input) == 553079)
    check(part2(input) == 84363105)
}

fun List<String>.wrap() =
    buildList {
        add(".".repeat(this@wrap[0].length))
        addAll(this@wrap.map { ".$it." })
        add(".".repeat(this@wrap[0].length))
    }
