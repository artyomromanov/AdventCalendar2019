package day5

import Computer
import operations.OperationFactory
import day5.util.DigitCode

class IntComputerMarkII : Computer {

    private val filepath = "data/input_int_computer2"
    private val proceduresList: MutableList<String>

    init {
        proceduresList = initializeData(filepath)
        paginateProcedures(proceduresList)
    }

    companion object {
        //Init operations map
        val allOperations: Map<Int, DigitCode> = mapOf(
            Pair(0, DigitCode.OP_CODE), Pair(1, DigitCode.OP_CODE_EXTRA), Pair(2, DigitCode.PARAM_ONE),
            Pair(3, DigitCode.PARAM_TWO)
        )
    }

    //Runs the new day2.IntComputer
    override fun runProgram(inputInstructions: List<Int>): Int {
        //Set cursor to 0
        var opCursor = 0
        //Begin program
        do {
            val operation = OperationFactory(proceduresList = proceduresList, cursor = opCursor, inputInstruction = inputInstructions.first()).getOperation()
            with(operation) {
                readValues()
                execute()
                printOperationData()
            }
            opCursor = operation.retrieveCursor()
            println("Cursor value : $opCursor")
        } while (opCursor <= proceduresList.size)
        return -1
    }
}