import java.util.*
import kotlin.math.sqrt

class Practice {

    fun solution(start: String, end: String): List<String> {
        val queue: Queue<List<String>> = LinkedList()
        queue.add(listOf(start))
        val visited = HashSet<String>()

        while (queue.isNotEmpty()) {
            val path = queue.remove()
            val current = path.last()

            if (current == end) return path
            if (current in visited) continue

            visited.add(current)
            for (nextPrime in findNextPrimes(current)) {
                if (nextPrime !in visited && isOneDigitDiff(current, nextPrime)) {
                    val newPath = path.toMutableList()
                    newPath.add(nextPrime)
                    queue.add(newPath)
                }
            }
        }
        return emptyList()
    }

    private fun findNextPrimes(current: String): List<String> {
        val nextPrimes = mutableListOf<String>()
        for (i in current.indices) {
            ('0'..'9')
                .map { current.substring(0, i) + it + current.substring(i + 1) }
                .filterTo(nextPrimes) { it != current && isPrime(it.toInt()) }
        }
        return nextPrimes.sorted()
    }

    private fun isOneDigitDiff(prime1: String, prime2: String): Boolean {
        return prime1.indices.count { prime1[it] != prime2[it] } == 1
    }

    private fun isPrime(n: Int): Boolean {
        if (n <= 1) return false
        return (2..sqrt(n.toDouble()).toInt()).none { n % it == 0 }
    }
}