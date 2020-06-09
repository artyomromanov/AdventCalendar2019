package operations

import util.OperationsList
import util.DigitCode
import java.lang.RuntimeException

//Factory to create operations. Returns an appropriate Operation judging by the OP_CODE
class OperationFactory(private val proceduresList: MutableList<String>, val cursor: Int, val relativeBase : Int = 0, var inputInstruction : Int = 0) {

    private val operationsMap: MutableMap<DigitCode, Int>

    //Initialize operations map upon creation of the Operation, and initialize the Factory
    init {
        operationsMap = readOperationParameters()
    }
    //Distinguish between OP_CODE 09 and 99
    private val operationType = if(operationsMap.getOrDefault(DigitCode.OP_CODE_EXTRA, 0) == 0) operationsMap[DigitCode.OP_CODE] else 99

    fun getOperation(): Operation {
        return when (operationType) {

            1 -> Sum(operationsMap, proceduresList, cursor, relativeBase)
            2 -> Multiply(operationsMap, proceduresList, cursor, relativeBase)
            3 -> Input(operationsMap, proceduresList, cursor, relativeBase, inputInstruction)
            4 -> Output(operationsMap, proceduresList, cursor, relativeBase)
            5 -> JumpIfTrue(operationsMap, proceduresList, cursor, relativeBase)
            6 -> JumpIfFalse(operationsMap, proceduresList, cursor, relativeBase)
            7 -> LessThan(operationsMap, proceduresList, cursor, relativeBase)
            8 -> Equals(operationsMap, proceduresList, cursor, relativeBase)
            9 -> AdjustRelativeBase(operationsMap, proceduresList, cursor, relativeBase)
            99 -> Halt(operationsMap,proceduresList,cursor, relativeBase)

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
            val digitCode = OperationsList.allOperations[i] //Get a correct digitCode from all operations map
            operationParametersMap[digitCode!!] = digitValue //Save into this particular operation parameters map
        }
        return operationParametersMap
    }
}
