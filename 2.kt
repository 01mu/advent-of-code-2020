import java.io.File

fun main() {
    val input = mutableListOf<List<String>>()
    var p1 = 0
    var p2 = 0

    File("input/2").forEachLine { input.add(it.split(" ")) }

    for (v in input) {
        val split = v[0].split("-")
        val al = split[0].toInt()
        val am = split[1].toInt()
        val ch = v[1][0]
        var found = false
        var total = 0

        for ((ip, p) in v[2].withIndex()) {
            if (p == ch)
                total += 1

            if ((ip == al - 1 || ip == am - 1) && p == ch)
                if(!found) {
                    p2 += 1
                    found = true
                } else
                    p2 -= 1
        }

        if (total >= al && total <= am)
            p1 += 1
    }

    println("P1: " + p1 + "\nP2: " + p2)
}
