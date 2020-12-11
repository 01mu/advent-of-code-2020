import java.io.File
import kotlin.collections.*

fun check(idx: Int, value: Long, input: List<Long>): Boolean {
    var a = idx - 25
    var found = false

    println(a.toString() + " " + value.toString())

    while (a < idx) {
        var b = idx - 25
        var va = input[a]

        while (b < idx) {
            var vb = input[b]
            if (a != b++ && (vb + va == value)) found = true
        }

        a++
    }

    return found
}

fun main() {
    var input = mutableListOf<Long>()

    for (l in File("input/9").readText()
        .trim().split("\n")) input.add(l.toLong())


    var found = false
/*    var idx = 0

    while (idx < input.size) {
        if (idx > 25) if(!check(idx, input[idx], input)) break
        idx++
    }
*/
    var a = 0
    found = false
    var total = 0L
    var l = 0
    var u = 0

    while (a < input.size) {
        var b = a + 1

        total += input[a]

        while (b < input.size) {
            total += input[b]

            if(total == 675280050L) {
                l = a
                u = b
            }
            //else println(total)

            b++
        }

        total = 0

        a++
    }

    var t = mutableListOf<Long>()

    while (l <= u) t.add(input[l++])

    val max = t.max()!!
    val min = t.min()!!

    println(max+min)

    //println(found)
    //println(input[idx])
}
