import java.io.File
import kotlin.collections.*

fun main() {
    val bags = HashMap<String, List<Pair<String, Int>>>()
    var p1 = 0

    for (s in File("input/7").readText().replace("bags", "").replace("bag", "")
        .replace(".", "").trim().split("\n")) {

        val splitName = s.split(" contain ")
        val key = splitName[0].trim()

        val childCountPairs = mutableListOf<Pair<String, Int>>()
        bags.put(key, childCountPairs)

        for (pair in splitName[1].trim().split(" , ")) {
            var childCount = 0
            var childName = pair

            if(pair[0].isDigit()) {
                childCount = pair[0].toInt() - '0'.toInt()
                childName = pair.substring(2)
            }

            childCountPairs.add(Pair(childName, childCount))
        }
    }

    for (bag in bags.keys) if (leastOne(bags.getValue(bag), bags)) ++p1

    println("P1: " + p1 + "\nP2: " + bCount(bags.getValue("shiny gold"), bags))
}

fun leastOne(children: List<Pair<String, Int>>,
    bags: HashMap<String, List<Pair<String, Int>>> ): Boolean {

    for (bag in children) {
        if (bag.first == "shiny gold") return true
        else if (bag.first == "no other") return false
        else if (leastOne(bags.getValue(bag.first), bags)) return true
    }

    return false
}

fun bCount(children: List<Pair<String, Int>>,
    bags: HashMap<String, List<Pair<String, Int>>>): Int {

    var c = 0

    for (b in children)
        if (b.first == "no other") return 0
        else c += b.second + b.second * bCount(bags.getValue(b.first), bags)

    return c
}
