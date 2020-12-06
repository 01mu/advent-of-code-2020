import java.io.File

fun main() {
    val input = mutableListOf<String>()
    val rd = arrayOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
    var p1 = 0
    var p2 = 1

    File("input/3").forEachLine { input.add(it) }

    val width = input[0].length
    val height = input.size

    for ((i, v) in rd.withIndex()) {
        var y = 0
        var x = 0
        var c = 0
        var hits = 0

        while (y + v.second < height) {
            x++
            c++

            if (c % v.first == 0) {
                y += v.second
                if (x >= width) x -= width
                if (input[y][(x)] == '#' && i == 1) p1++
                if (input[y][(x)] == '#') hits++
            }
        }

        p2 *= hits
    }

    println("P1: " + p1 + "\nP2: " + p2)
}
