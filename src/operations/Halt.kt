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
        inputOutput = super.convert(proceduresList[cursor + 1])
    }

    override fun execute() {
        //Any logging that is required after the program halts can be input here
    }

    override fun retrieveCursor(): Int = -1

    override fun printOperationData() {
        println("Operations map - $operationsMap")
    }
}