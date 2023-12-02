data class ReplacePart(val position: Int, val number: Int, val text: String)

fun main() {
    fun findNumber(word: String): Int {
        val result: String = word.filter { it.isDigit() }
        val firstNumber = result[0]
        val reversed = result.reversed()
        val lastNumber = reversed[0]
        return ("" + firstNumber + lastNumber).toInt()
    }


    fun calculateFirstPositionsOfNumbers(replaced: String): MutableList<ReplacePart> {
        val mutableList: MutableList<ReplacePart> = ArrayList()

        val positionOf1 = replaced.filter { !it.isDigit() }.indexOf("one")
        mutableList.add(ReplacePart(positionOf1, 1, "one"))
        val positionOf2 = replaced.filter { !it.isDigit() }.indexOf("two")
        mutableList.add(ReplacePart(positionOf2, 2, "two"))
        val positionOf3 = replaced.filter { !it.isDigit() }.indexOf("three")
        mutableList.add(ReplacePart(positionOf3, 3, "three"))
        val positionOf4 = replaced.filter { !it.isDigit() }.indexOf("four")
        mutableList.add(ReplacePart(positionOf4, 4, "four"))
        val positionOf5 = replaced.filter { !it.isDigit() }.indexOf("five")
        mutableList.add(ReplacePart(positionOf5, 5, "five"))
        val positionOf6 = replaced.filter { !it.isDigit() }.indexOf("six")
        mutableList.add(ReplacePart(positionOf6, 6, "six"))
        val positionOf7 = replaced.filter { !it.isDigit() }.indexOf("seven")
        mutableList.add(ReplacePart(positionOf7, 7, "seven"))
        val positionOf8 = replaced.filter { !it.isDigit() }.indexOf("eight")
        mutableList.add(ReplacePart(positionOf8, 8, "eight"))
        val positionOf9 = replaced.filter { !it.isDigit() }.indexOf("nine")
        mutableList.add(ReplacePart(positionOf9, 9, "nine"))
        return mutableList
    }

    fun calculateLastPositionsOfNumbers(replaced: String): MutableList<ReplacePart> {
        val mutableList: MutableList<ReplacePart> = ArrayList()

        val positionOf1 = replaced.filter { !it.isDigit() }.lastIndexOf("one")
        mutableList.add(ReplacePart(positionOf1, 1, "one"))
        val positionOf2 = replaced.filter { !it.isDigit() }.lastIndexOf("two")
        mutableList.add(ReplacePart(positionOf2, 2, "two"))
        val positionOf3 = replaced.filter { !it.isDigit() }.lastIndexOf("three")
        mutableList.add(ReplacePart(positionOf3, 3, "three"))
        val positionOf4 = replaced.filter { !it.isDigit() }.lastIndexOf("four")
        mutableList.add(ReplacePart(positionOf4, 4, "four"))
        val positionOf5 = replaced.filter { !it.isDigit() }.lastIndexOf("five")
        mutableList.add(ReplacePart(positionOf5, 5, "five"))
        val positionOf6 = replaced.filter { !it.isDigit() }.lastIndexOf("six")
        mutableList.add(ReplacePart(positionOf6, 6, "six"))
        val positionOf7 = replaced.filter { !it.isDigit() }.lastIndexOf("seven")
        mutableList.add(ReplacePart(positionOf7, 7, "seven"))
        val positionOf8 = replaced.filter { !it.isDigit() }.lastIndexOf("eight")
        mutableList.add(ReplacePart(positionOf8, 8, "eight"))
        val positionOf9 = replaced.filter { !it.isDigit() }.lastIndexOf("nine")
        mutableList.add(ReplacePart(positionOf9, 9, "nine"))
        return mutableList
    }

    fun replaceWordsByNumbers(word: String): Int {
        val firstPositions: MutableList<ReplacePart> = calculateFirstPositionsOfNumbers(word)
        val lastPositions: MutableList<ReplacePart> = calculateLastPositionsOfNumbers(word)

        val minWith: ReplacePart? = firstPositions.filter { replacePart -> replacePart.position != -1 }
            .minWithOrNull(Comparator.comparingInt { it.position })

        val firstNumber: Char
        if (minWith != null) {
            val firstNumberString = word.replace(minWith.text, "" + minWith.number)
            firstNumber = firstNumberString.filter { it.isDigit() }[0]
        } else {
            firstNumber = word.filter { it.isDigit() }[0]
        }


        val maxWith: ReplacePart? = lastPositions.filter { replacePart -> replacePart.position != -1 }
            .maxWithOrNull(Comparator.comparingInt { it.position })

        val lastNumber: Char
        if (maxWith != null) {
            val lastNumberString = word.replace(maxWith.text, "" + maxWith.number)
            lastNumber = lastNumberString.reversed().filter { it.isDigit() }[0]
        } else {
            lastNumber = word.reversed().filter { it.isDigit() }[0]
        }
        return ("" + firstNumber + lastNumber).toInt()
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { findNumber(it) }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { replaceWordsByNumbers(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_part1_test")
    check(part1(testInput) == 142)

    val testInputPart2 = readInput("Day01_part2_test")
    check(part2(testInputPart2) == 281)

    val testInputPart3 = readInput("Day01_part3_test")
    check(part2(testInputPart3) == 162)

    val input = readInput("Day01")
    check(part1(input) == 55172)
    check(part2(input) == 54925)
}
