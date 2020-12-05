import java.io.File
import kotlin.math.*
import kotlin.collections.*

fun main() {
    val input = mutableListOf<String>()
    var list = mutableListOf<Int>()
    var p2 = 0

    File("input/5").forEachLine { input.add(it) }

    for (dir in input) {
        val row = findMin(dir.substring(0, 7), 'F', 127) * 8
        val col = findMin(dir.substring(7), 'L', 7)

        list.add(row + col)
    }

    val s = list.sorted()

    for ((i, v) in s.withIndex()) if(i > 0 && (s[i - 1] != v - 1)) p2 = v - 1

    println("P1: " + list.maxOrNull() + "\nP2: " + p2)
}

fun findMin(str: String, alt: Char, def: Int): Int {
    var l = 0
    var h = def

    for (ch in str)
        if (ch == alt) h = l + ((h - l) / 2)
        else l = (h + l + 1) / 2

    return min(l, h)
}
