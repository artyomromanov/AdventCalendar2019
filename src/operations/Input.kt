package operations
import util.DigitCode

class Input(
    override val operationsMap: MutableMap<DigitCode, Int>,
    override val proceduresList: MutableList<String>,
    override val cursor: Int,
    relativeBase: Int,
    var input : Int
) :
    Operation(operationsMap, proceduresList, cursor, relativeBase) {

    private var inputOutput: Int? = null

    override fun readValues() {
        inputOutput = super.convert(proceduresList[cursor + 1])
    }

    override fun execute() {
        if (inputOutput == null) {
            throw RuntimeException("Unassigned parameter value exception - necessary parameter is null")
        } else {
            proceduresList[inputOutput!!] = input.toString()
        }
    }

    override fun retrieveCursor(): Int = cursor + 2

    override fun printOperationData() {
        println("Operation : Input - $input written at pos $inputOutput")
    }
}