import java.io.File
import kotlin.math.*
import kotlin.collections.*

fun main() {
    var ids = mutableListOf<Int>()

    for (dir in File("input/5").readText().trim().split("\n"))
        ids.add(getLower(dir, 'F', 127) * 8 + getLower(dir, 'L', 7))

    val sorted = ids.sorted()

    println("P1: " + sorted.last() + "\nP2: " + getUserID(sorted))
}

fun getLower(dir: String, alt: Char, def: Int): Int {
    var l = 0
    var h = def

    for (ch in if (alt == 'F') dir.substring(0, 7) else dir.substring(7))
        if (ch == alt) h = l + (h - l) / 2 else l = (h + l + 1) / 2

    return l
}

fun getUserID(l: List<Int>): Int {
    for ((i, v) in l.withIndex()) if (i > 0 && (l[i - 1] != v - 1)) return v - 1

    return -1
}
