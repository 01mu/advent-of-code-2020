import java.io.File

fun main() {
    val vals = mutableListOf<Int>()
    val hm = HashMap<Int, Int>()

    File("input/1").forEachLine { vals.add(it.toInt()) }

    loop@ for(a in vals)
        for(b in vals) {
            hm.put(a + b, a * b)

            if(a + b == 2020) {
                println("P1: " + a * b)
                break@loop
            }
        }

    for(c in vals) {
        val ab = 2020 - c
        val g = hm.get(ab)

        if(g != null && ab + c == 2020) {
            println("P2: " + g * c)
            break
        }
    }
}
