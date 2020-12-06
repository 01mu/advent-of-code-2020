import java.io.File

fun main() {
    val input = mutableListOf<List<String>>()
    var p1 = 0
    var p2 = 0

    File("input/2").forEachLine { input.add(it.split(" ")) }

    for (v in input) {
        val ll = v[0].split("-")[0].toInt()
        val ul = v[0].split("-")[1].toInt()
        var total = 0
        var found = 0

        for ((i, c) in v[2].withIndex()) {
            if (c == v[1][0]) ++total
            if ((i == ll - 1 || i == ul - 1) && c == v[1][0]) ++found
        }

        if (total >= ll && total <= ul) ++p1
        if (found == 1) ++p2
    }

    println("P1: " + p1 + "\nP2: " + p2)
}
