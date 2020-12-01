import java.io.File

fun main() {
    val vals = mutableListOf<Int>()
    val ab = HashMap<Int, Int>()

    File("input/1").forEachLine { vals.add(it.toInt()) }

    loop@ for (a in vals)
        for (b in vals) {
            ab.put(a + b, a * b)

            if (a + b == 2020) {
                println("P1: " + a * b)
                break@loop
            }
        }

    for (c in vals) {
        val prod = ab.get(2020 - c)

        if (prod != null && 2020 - c + c == 2020) {
            println("P2: " + prod * c)
            break
        }
    }
}
