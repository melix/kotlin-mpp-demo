fun main() {
    println("Salut, Frédéric. Fib(90) = ${fib(90)}")
}

fun fib(n: Long): Long = fib(n, mutableMapOf())

fun fib(n: Long, cache: MutableMap<Long, Long>): Long = cache.getOrPut(n) {
    when (n) {
        0L -> 0L
        1L -> 1L
        else -> fib(n - 1, cache) + fib(n - 2, cache)
    }
}