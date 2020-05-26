package operations

import day5.IntComputerMarkII
import day5.util.DigitCode
import java.lang.RuntimeException

//Factory to create operations. Returns an appropriate Operation judging by the OP_CODE
class OperationFactory(private val proceduresList: MutableList<String>, val cursor: Int, private val inputInstruction : Int) {

    private val operationsMap: MutableMap<DigitCode, Int>

    //Initialize operations map upon creation of the Operation, and initialize the Factory
    init {
        operationsMap = readOperationParameters()
    }

    private val operationType = operationsMap[DigitCode.OP_CODE]

    fun getOperation(): Operation {
        return when (operationType) {

            1 -> Sum(operationsMap, proceduresList, cursor)
            2 -> Multiply(operationsMap, proceduresList, cursor)
            3 -> Input(operationsMap, proceduresList, cursor, inputInstruction)
            4 -> Output(operationsMap, proceduresList, cursor)
            5 -> JumpIfTrue(operationsMap, proceduresList, cursor)
            6 -> JumpIfFalse(operationsMap, proceduresList, cursor)
            7 -> LessThan(operationsMap, proceduresList, cursor)
            8 -> Equals(operationsMap, proceduresList, cursor)

            9 -> throw RuntimeException("Program Halted! For TEST output see above.")
            else -> throw RuntimeException("Incorrect operation creation exception")
        }
    }

    //Read operation parameters - universal for all operations.
    private fun readOperationParameters(): MutableMap<DigitCode, Int> {
        //Raw operation String
        val operationData = proceduresList[cursor].trim().reversed()
        //Map of the operations and values
        val operationParametersMap = mutableMapOf<DigitCode, Int>()

        //Read opCode and parameter Modes
        operationData.forEachIndexed { i, digitChar ->
            val digitValue = digitChar.toString().toInt() //Save the parameter mode value
            val digitCode = IntComputerMarkII.allOperations[i] //Get a correct digitCode from all operations map

            operationParametersMap[digitCode!!] = digitValue //Save into particular operations parameter map
        }
        return operationParametersMap
    }
}
