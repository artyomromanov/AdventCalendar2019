package operations

import util.DigitCode
import java.lang.RuntimeException

class Multiply(
    override val operationsMap: MutableMap<DigitCode, Int>,
    override val proceduresList: MutableList<String>,
    override val cursor: Int,
    relativeBase: Int
) : Operation(operationsMap, proceduresList, cursor, relativeBase) {

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
            proceduresList[inputOutput!!] = (intA!! * intB!!).toString()
        }
    }

    override fun retrieveCursor(): Int = cursor + 4

    override fun printOperationData() {
        println("Operation : Multiply. $intA * $intB = ${intA!! * intB!!} }; written at pos $inputOutput")
    }
}