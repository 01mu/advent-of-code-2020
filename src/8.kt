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

fun accumulator(ins: List<Pair<String, Int>>): Pair<Boolean, Int> {
    val positions = HashMap<Int, Int>()
    var acc = 0
    var pos = 0
    var found = false

    while (pos < ins.size && !positions.containsKey(pos)) {
        if (!positions.containsKey(pos)) positions.put(pos, 1)

        if (ins[pos].first == "jmp") pos += ins[pos].second
        else if (ins[pos].first == "acc") acc += ins[pos++].second
        else ++pos

        if (pos == ins.size - 1) found = true
    }

    return Pair(found, acc)
}

fun p2(ins: List<Pair<String, Int>>): Int {
    var i = 0
    var found = false
    var acc = 0

    while (i != ins.size - 1 && !found) {
        val z = modIns(i, ins)
        val loop = accumulator(z.second)

        if (loop.first) found = true

        acc = loop.second
        i = z.first
    }

    return acc
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

    println("P1: " + accumulator(ins).second + "\nP2: " + p2(ins))
}
