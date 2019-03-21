package infinigold

import org.javatuples.Pair
import java.util.*

object MyClass {

    fun findSequences(input: List<Int>): List<List<Int>> {
        val output = ArrayList<List<Int>>()

        val mHashMap = HashMap<Pair<Int, Int>, Int>()

        val minInput = Collections.min(input)
        val maxInput = Collections.max(input)

        // check every N such that minValue*N*N <= maxValue => N <= sqrt(maxValue/minValue)
        val nMax = Math.sqrt(maxInput.toString().toDouble() / minInput!!)
            .toInt()

        for (i in 2 until input.size) {

            var n = 2
            while (n < minThreeNumbers(i.toDouble(), (input.size - 1).toDouble() / 2, nMax.toDouble()) + 1) {
                if (input[i - n] * n == input[i]) {
                    val t = Pair.with(i - n, n)

                    var le = 1
                    if (mHashMap.containsKey(t)) {
                        le = mHashMap[t]!!
                    }
                    mHashMap[Pair.with(i, n)] = le + 1
                }
                n++
            }
        }

        val res = HashMap<Int, Pair<Int?, Int>>()
        mHashMap.forEach { key, value ->
            val n = key.value1
            val le = mHashMap[key]
            val ending = key.value0

            val existsPair = res[n]
            if (existsPair != null) {
                if (res[n]!!.value0!! < le!!) {
                    res[n] = Pair.with(le, ending)
                }
            } else {
                res[n] = Pair.with(le, ending)
            }
        }

        res.forEach { key, value ->
            val innerList = ArrayList<Int>()
            var seqLength: Int? = value.value0
            var endIndex: Int? = value.value1
            while (seqLength!! > 0) {
                seqLength--
                innerList.add(input[endIndex!!])
                endIndex -= key
            }
            if (!innerList.contains(0)) {
                innerList.sort()
                val sum = innerList.stream()
                    .mapToInt { it.toInt() }
                    .sum()
                println("for N = $key >>---> $innerList with the sum = $sum")
                output.add(innerList)
            }
        }

        return output
    }

    // minimum of 3 numbers.
    private fun minThreeNumbers(
        a: Double,
        b: Double,
        c: Double
    ): Double {
        return Math.min(Math.min(a, b), c)
    }
}

