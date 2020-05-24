package day5.operations

import day5.util.DigitCode
import java.lang.RuntimeException

abstract class Operation protected constructor(
    open val operationsMap: MutableMap<DigitCode, Int>,
    open val proceduresList: MutableList<String>,
    open val cursor: Int
) {

    abstract fun readValues()

    abstract fun execute()

    abstract fun printOperationData()

    abstract fun retrieveCursor(): Int

    //Reads particular value from procedures List. Used by abstract fun readValues()
    fun read(code: DigitCode, deltaIndex: Int, cursor: Int): Int {
        val readCursor = convert(proceduresList[cursor + deltaIndex])
        return when (operationsMap.getOrDefault(code, 0)) {
            0 -> convert(proceduresList[readCursor])
            1 -> readCursor
            else -> throw RuntimeException("Incorrect value parameter modifier")
        }
    }

    //Convert to integer
    fun convert(value: String): Int = value.trim().toInt()

}