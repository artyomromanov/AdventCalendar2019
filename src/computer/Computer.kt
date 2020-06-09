package computer

import java.io.File

interface Computer {

    fun <T> initializeData(filepath : String): MutableList<T>{
        val list = mutableListOf<String>()
        File(filepath)
            .readText()
            .split(",")
            .forEach {
                list.add(it.trim())
            }
        return list as MutableList<T>
    }

    fun runProgram(inputInstructions : List<Int>) : Int

    fun paginateProcedures(proceduresList : MutableList<String>, maxLineRow : Int = 20, maxColumnRow : Int = 10) {
        var lineRow = 0
        do {
            var line = lineRow
            do {
                val shift = 8 - proceduresList[line].length
                var spacing = ""
                repeat(shift) { spacing += " " }
                print("$line. ${proceduresList[line]}$spacing ")
                line += maxColumnRow
            } while (line < proceduresList.size)
            println()
            lineRow++
        } while (lineRow < maxLineRow)
    }
}