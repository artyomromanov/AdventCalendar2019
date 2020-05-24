package day5.operations

import day5.util.DigitCode
import java.lang.RuntimeException

class Sum(
    override val operationsMap: MutableMap<DigitCode, Int>,
    override val proceduresList: MutableList<String>,
    override val cursor: Int
) :
    Operation(operationsMap, proceduresList, cursor) {

    private var intA: Int? = null
    private var intB: Int? = null
    private var inputOutput: Int? = null

    override fun readValues() {
        intA = super.read(DigitCode.PARAM_ONE, 1, cursor)
        intB = super.read(DigitCode.PARAM_TWO, 2, cursor)
        inputOutput = super.convert(proceduresList[cursor + 3])
    }

    override fun execute() {
        if (inputOutput == null || intA == null || intB == null) {
            throw RuntimeException("Unassigned parameter value exception - necessary parameter is null")
        } else {
            proceduresList[inputOutput!!] = (intA!! + intB!!).toString()
        }
    }

    override fun retrieveCursor(): Int = cursor + 4

    override fun printOperationData() {
        println("Operation : Sum. $intA + $intB = ${intA!! + intB!!}}; Written at pos $inputOutput")
        println("Operations map - $operationsMap")
    }
}