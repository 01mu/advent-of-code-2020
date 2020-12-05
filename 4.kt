import java.io.File

fun main() {
    val file = File("input/4").readText().split("\n\n")
    val fields = arrayOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid")
    val eye_colors = arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    val af = ('a'..'f').toList().toTypedArray()
    val zn = ('0'..'9').toList().toTypedArray()
    var p1 = 0
    var p2 = file.size

    for (s in file) {
        var id = HashMap<String, String>()
        var missing = 0

        for (v in s.replace("\n", " ").trim().split(" ")) addID(id, v)
        for (f in fields) if (!id.containsKey(f)) ++missing

        if ((missing == 1 && !id.containsKey("cid")) || missing == 0) ++p1

        if (!((missing == 1 && !id.containsKey("cid")) || missing == 0) ||
            HGT(id, zn) || HCL(id, af, zn) || PID(id) || ECL(id, eye_colors) ||
            range(1920, 2002, "byr", id) || range(2010, 2020, "iyr", id) ||
            range(2020, 2030, "eyr", id)) --p2
    }

    println("P1: " + p1 + "\nP2: " + p2)
}

fun addID(id: HashMap<String, String>, v: String) {
    id.put(v.split(":")[0], v.split(":")[1])
}

fun HGT(id: HashMap<String, String>, z: Array<Char>): Boolean {
    val hgt = id.get("hgt").toString()
    var num = ""

    for (ch in hgt) if (ch in z) num += ch

    var h = num.toInt()

    if (hgt.last() in z || (hgt.last() == 'm' && (h < 150 || h > 193)) ||
        (hgt.last() == 'n' && (h < 59 || h > 76))) return true

    return false
}

fun HCL(id: HashMap<String, String>, a: Array<Char>, z: Array<Char>): Boolean {
    val hcl = id.get("hcl").toString()

    if (hcl[0] == '#') {
        if(hcl.substring(1).length != 6) return true
        for (v in hcl.substring(1)) if (v !in a && v !in z) return true
    } else return true

    return false
}

fun PID(id: HashMap<String, String>): Boolean {
    return id.get("pid").toString().length != 9
}

fun ECL(id: HashMap<String, String>, ec: Array<String>): Boolean {
    return id.get("ecl").toString() !in ec
}

fun range(a: Int, b: Int, k: String, id: HashMap<String, String>): Boolean {
    if (id.containsKey(k)) {
        val v = id.get(k).toString().toInt()
        if (v < a || v > b) return true
    }

    return false
}
