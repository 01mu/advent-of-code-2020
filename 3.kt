import java.io.File

fun main() {
    val input = mutableListOf<String>()
    val rd = arrayOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
    var p1 = 0
    var p2 = 1

    File("input/3").forEachLine { input.add(it) }

    val bw = input[0].length
    val bh = input.size

    for ((i, v) in rd.withIndex()) {
        var sum = 0
        var c = 0
        var pos_x = 0
        var pos_y = 0

        for (x in 0..bw * bh) {
            pos_x++
            c++

            if (pos_x >= bw) pos_x -= bw

            if (c % v.first == 0) {
                pos_y += v.second
                c = 0

                if (pos_y >= bh) break
                if (input[pos_y][(pos_x)] == '#' && i == 1) p1++
                if (input[pos_y][(pos_x)] == '#') sum++
            }
        }

        p2 *= sum
    }

    println("P1: " + p1 + "\nP2: " + p2)
}
