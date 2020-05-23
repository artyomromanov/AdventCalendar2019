package day5.operations

import day5.DigitCode
import day5.inputInstruction
import java.lang.RuntimeException

class Input(
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
        if (inputOutput == null) {
            throw RuntimeException("Unassigned parameter value exception - necessary parameter is null")
        } else {
            proceduresList[inputOutput!!] = inputInstruction.toString()
        }
    }

    override fun setCursor(): Int = 2

    override fun printOperationData() {
        println("Operation : Input - $inputInstruction written at pos $inputOutput")
        println("Changed value is now ${proceduresList[inputOutput!!]}")
        println("Operations map - $operationsMap")
    }
}