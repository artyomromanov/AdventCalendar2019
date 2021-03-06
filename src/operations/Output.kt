package operations

import day5.util.DigitCode
import java.lang.RuntimeException

class Output(
    override val operationsMap: MutableMap<DigitCode, Int>,
    override val proceduresList: MutableList<String>,
    override val cursor: Int
) :
    Operation(operationsMap, proceduresList, cursor) {

    var inputOutput: Int? = null

    override fun readValues() {
        inputOutput = super.read(DigitCode.PARAM_ONE, 1, cursor)
    }

    override fun execute() {
        if (inputOutput == null) {
            throw RuntimeException("Unassigned parameter value exception - necessary parameter is null")
        }
    }

    override fun retrieveCursor(): Int = cursor + 2

    override fun printOperationData() {
        println("Operation : Output - $inputOutput")
    }
}