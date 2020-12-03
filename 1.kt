import java.io.File

fun main() {
    val input = mutableListOf<Int>()
    val ab = HashMap<Int, Int>()
    var p1 = 0
    var p2 = 0

    File("input/1").forEachLine { input.add(it.toInt()) }

    for ((ia, a) in input.withIndex())
        for ((ib, b) in input.withIndex())
            if (ia != ib) {
                ab.put(a + b, a * b)
                if (a + b == 2020) p1 = a * b;
            }

    for (c in input) {
        val prod = ab.get(2020 - c)

        if (prod != null) {
            p2 = prod * c
            break
        }
    }

    println("P1: " + p1 + "\nP2: " + p2)
}
