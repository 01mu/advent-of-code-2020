import java.io.File

fun main() {
    val fields = arrayOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid")
    val eye_colors = arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    var af = ('a'..'f').toList().toTypedArray()
    var zn = ('0'..'9').toList().toTypedArray()

    var p1 = 0
    var bad_count = 0
    var id_count = 0

    for (s in File("input/4").readText().split("\n\n")) {
        var id = HashMap<String, String>()

        for (v in s.replace("\n", " ").trim().split(" "))
            id.put(v.split(":")[0], v.split(":")[1])

        var no_cid = false
        var missing = 0
        var bad = false

        for (f in fields)
            if (!id.containsKey(f)) {
                if (f == "cid") no_cid = true
                ++missing
            }

        if (id.containsKey("hgt")) {
            var num = ""
            var type = ""

            for (v in id.get("hgt").toString()) {
                if (v != 'c' && v != 'i') {
                    num += v
                } else if (v == 'c') {
                    type = "cm"
                    break
                } else if (v == 'i') {
                    type = "in"
                    break
                }
            }

            var h = num.toInt()

            if (type == "" ||
                (type == "cm" && (h < 150 || h > 193)) ||
                (type == "in" && (h < 59 || h > 76))) bad = true
        }

        if (id.containsKey("hcl")) {
            var hcl = id.get("hcl").toString()

            if (hcl[0] == '#') {
                if(hcl.substring(1).length != 6) bad = true
                for (v in hcl.substring(1)) if (v !in af && v !in zn) bad = true
            } else bad = true
        }

        if (range(1920, 2002, "byr", id) ||
            range(2010, 2020, "iyr", id) ||
            range(2020, 2030, "eyr", id) ||
            (id.containsKey("pid") && id.get("pid").toString().length != 9) ||
            (id.containsKey("ecl") && id.get("ecl").toString() !in eye_colors))
            bad = true

        if ((missing == 1 && no_cid == true) || missing == 0) ++p1
        else bad = true

        if (bad == true) ++bad_count
        id_count++
    }

    println("P1: " + p1 + "\nP2: " + (id_count - bad_count))
}

fun range(a: Int, b: Int, k: String, id: HashMap<String, String>): Boolean {
    if (id.containsKey(k)) {
        var byr = id.get(k).toString().toInt()
        if (byr < a || byr > b) return true
    }

    return false
}
