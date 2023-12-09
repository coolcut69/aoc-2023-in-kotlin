fun main() {
    fun historyToReport(toList: MutableList<Int>): MutableList<MutableList<Int>> {
        val report: MutableList<MutableList<Int>> = ArrayList()
        report.add(toList)
        var toCheck = toList
        do {
            val sequence: MutableList<Int> = ArrayList()
            for (n in 0..<toCheck.size - 1) {
                sequence.add(toCheck[n + 1] - toCheck[n])
            }
            report.add(sequence)
            toCheck = sequence
        } while (!sequence.all { it == 0 })
        return report
    }

    fun prediction(report: List<MutableList<Int>>): Int {
        var prediction = 0
        for ((index, line) in report.withIndex()) {
            if (index != 0) {
                prediction += report[index - 1].last()
            }
        }
        prediction += report.last().last()
//        println(prediction)
        return prediction
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input){
            val history = line.split(" ").map { it.toInt() }.toMutableList()
            val report = historyToReport(history).reversed()
            sum += prediction(report)
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 114)
//    check(part2(testInput) == 1)

    val input = readInput("Day09")
    part1(input).println()
//    part2(input).println()
}
