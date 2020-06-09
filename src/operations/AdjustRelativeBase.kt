package operations

import util.DigitCode

class AdjustRelativeBase(
    override val operationsMap: MutableMap<DigitCode, Int>,
    override val proceduresList: MutableList<String>,
    override val cursor: Int,
    relativeBase : Int
) :
    Operation(operationsMap, proceduresList, cursor, relativeBase) {

    override fun readValues() {
        TODO("Not yet implemented")
    }

    override fun execute() {
        TODO("Not yet implemented")
    }

    override fun printOperationData() {
        TODO("Not yet implemented")
    }

    override fun retrieveCursor(): Int {
        TODO("Not yet implemented")
    }
}