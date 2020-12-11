import java.io.File
import kotlin.collections.*

fun p1(values: List<Int>) {
    var o = 0
    var t = 0
    var th = 0

    for (v in values)
        if (values.contains(v + 1)) o++
        else if (values.contains(v + 2)) t++
        else if (values.contains(v + 3)) th++

    println(th * o)
}

fun trib(n: Int): Int {
    if(n == 0 || n == 1 || n == 2) {
        return 0
    }

    if(n == 3) {
        return 1
    } else {
        return trib(n - 1) + trib(n - 2) + trib(n - 3)
    }
}

fun main() {
    var input = mutableListOf<Int>()

    for (l in File("input/10").readText()
        .trim().split("\n")) input.add(l.toInt())

    input.add(0)
    input.add(input.max()!! + 3)

    //for (i in 0..10)
    trib(5)

    //p1(input)
}

