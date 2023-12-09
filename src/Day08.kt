private fun parseRouteMap(input: List<String>): Map<String, Pair<String, String>> =
    input
        .drop(2)
        .associate {
            it.substring(0..2) to (it.substring(7..9) to it.substring(12..14))
        }

fun gcd(a: Long, b: Long): Long {
    if(b == 0L) {
        return a
    }
    return gcd(b, a%b)
}

fun main() {
    fun part1(input: List<String>): Int {
        val instructions = input[0]
        val map = parseRouteMap(input)

        var steps: Int = 0
        var place = "AAA"
        do {
            for (ins in instructions) {
                val pair = map[place]
                place = if (ins == 'L'){
                    pair?.first!!
                } else {
                    pair?.second!!
                }
                steps++
            }
        } while (!place.endsWith( "Z"))
        return steps
    }

    fun part2(input: List<String>): Long {
        val instructions = input[0]
        val edges = HashMap<String, List<String>>()
        for(i in 2 until input.size) {
            val line = input[i].split(" ")
            val node = line[0]
            var lhs = line[2]
            lhs = lhs.substring(1, lhs.length - 1)
            var rhs = line[3]
            rhs = rhs.substring(0, rhs.length-1)
            edges[node] = listOf(lhs, rhs)
        }
        var ret: Long = 1
        for(cand in edges.keys) {
            if(cand[cand.length-1] != 'A') continue
            var len: Long = 0
            var now = cand
            while(now[now.length-1] != 'Z') {
                val go = instructions[(len % instructions.length).toInt()]
                val idx = if(go == 'L') 0 else 1
                now = edges[now]!![idx]
                len += 1
            }
            ret = ret / gcd(ret, len) * len
        }
        return ret
    }


// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 6)
    check(part2(testInput) == 6L)

    val input = readInput("Day08")
    check(part1(input) == 17141)
    check(part2(input) == 10818234074807)
}
