package operations

import util.DigitCode

class Halt(
    override val operationsMap: MutableMap<DigitCode, Int>,
    override val proceduresList: MutableList<String>,
    override val cursor: Int,
    relativeBase: Int
) :
    Operation(operationsMap, proceduresList, cursor, relativeBase) {

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