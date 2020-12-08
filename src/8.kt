import java.io.File
import kotlin.collections.*

fun modIns(pos: Int, ins: List<Pair<String, Int>>):
    Pair<Int, List<Pair<String, Int>>> {

    var v = pos
    val altered = ins.toMutableList()

    while (v < ins.size) {
        if (ins[v].first == "jmp") {
            altered[v] = Pair("nop", ins[v++].second)
            break
        } else if (ins[v].first == "nop") {
            altered[v] = Pair("jmp", ins[v++].second)
            break
        }

        v++
    }

    return Pair(v, altered)
}

fun p1(ins: List<Pair<String, Int>>): Pair<Boolean, Int> {
    val positions = HashMap<Int, Int>()
    var acc = 0
    var pos = 0
    var found = false

    while (true) {
        if (!positions.containsKey(pos)) positions.put(pos, 1)

        if (ins[pos].first == "jmp") pos += ins[pos].second
        else if (ins[pos].first == "acc") acc += ins[pos++].second
        else ++pos

        if (pos == ins.size - 1) found = true
        if (pos >= ins.size || positions.containsKey(pos)) break
    }

    return Pair(found, acc)
}

fun p2(ins: List<Pair<String, Int>>): Int {
    var i = 0

    while (i != ins.size - 1) {
        val z = modIns(i, ins)
        val loop = p1(z.second)

        if (loop.first) return loop.second
        else i = z.first
    }

    return -1
}

fun main() {
    val ins = mutableListOf<Pair<String, Int>>()

    for (l in File("input/8").readText().trim().split("\n")) {
        val inst = l.split(" ")
        val code = inst[0]
        var num = inst[1].toInt()

        if (inst[1][0] == '+') num = inst[1].substring(1).toInt()

        ins.add(Pair(code, num))
    }

    println("P1: " + p1(ins).second + "\nP2: " + p2(ins))
}
