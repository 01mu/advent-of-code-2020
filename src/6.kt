import java.io.File
import kotlin.collections.*

fun main() {
    var p1 = 0
    var p2 = 0

    for (s in File("input/6").readText().split("\n\n")) {
        val group = (s.replace("\n", " ").trim().split(" "))
        var answers = HashMap<Char, Int>()

        for (person in group)
            for (q in person) {
                if (!answers.containsKey(q)) answers.put(q, 1)
                else answers.put(q, answers.getValue(q) + 1)

                if (answers.getValue(q) == group.size) ++p2
            }

        p1 += answers.keys.size
    }

    println("P1: " + p1 + "\nP2: " + p2)
}
