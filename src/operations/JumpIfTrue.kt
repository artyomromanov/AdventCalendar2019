package operations

import day5.util.DigitCode
import java.lang.RuntimeException

class JumpIfTrue(
    override val operationsMap: MutableMap<DigitCode, Int>,
    override val proceduresList: MutableList<String>,
    override val cursor: Int
) :
    Operation(operationsMap, proceduresList, cursor) {

    private var intA: Int? = null
    private var intB: Int? = null
    private var deltaCursor: Int? = null

    override fun readValues() {
        intA = super.read(DigitCode.PARAM_ONE, 1, cursor)
        intB = super.read(DigitCode.PARAM_TWO, 2, cursor)
    }

    override fun execute() {
        if (intA == null || intB == null) {
            throw RuntimeException("Unassigned parameter value exception - necessary parameter is null")
        } else {
            if (intA != 0) {
                intB?.let { deltaCursor = it - cursor }
            } else {
                deltaCursor = 3
            }
        }
    }

    override fun retrieveCursor(): Int = deltaCursor?.let { cursor + it }
        ?: throw RuntimeException("Incorrect operation JumpIfFalse delta cursor exception - necessary parameter is null")

    override fun printOperationData() {
        println("Operation : JumpIfTrue. IntA = $intA; IntB = $intB; Jumped to position ${cursor + deltaCursor!!}")
    }
}