package operations

import day5.util.DigitCode
import java.lang.RuntimeException

class Halt(
    override val operationsMap: MutableMap<DigitCode, Int>,
    override val proceduresList: MutableList<String>,
    override val cursor: Int
) :
    Operation(operationsMap, proceduresList, cursor) {

    private var inputOutput: Int? = null

    override fun readValues() {
        inputOutput = -1 //Program is halted
    }

    override fun execute() {
        //throw RuntimeException("Program halted. Input/Output $inputOutput")
    }

    override fun retrieveCursor(): Int = -1

    override fun printOperationData() {
    }
}