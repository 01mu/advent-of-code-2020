import java.io.File

fun main() {
    val vals = mutableListOf<Int>()
    val ab = HashMap<Int, Int>()
    var p1 = 0

    File("input/1").forEachLine { vals.add(it.toInt()) }

    for ((ia, a) in vals.withIndex())
        for ((ib, b) in vals.withIndex())
            if (ia != ib) {
                ab.put(a + b, a * b)

                if(a + b == 2020) {
                    p1 = a * b;
                }
            }

    println("P1: " + p1)

    for (c in vals) {
        val prod = ab.get(2020 - c)

        if (prod != null) {
            println("P2: " + prod * c)
            break
        }
    }
}
