package day7

import Computer
import operations.OperationFactory

class IntComputerAmplifier : Computer {

    private val filepath = "data/input_int_computer_amplifier"
    private val proceduresList: MutableList<String>

    init {
        proceduresList = initializeData(filepath)
    }

    override fun runProgram(inputInstructions: List<Int>): Int {
        //Set cursor to 0
        var opCursor = 0
        //Begin program
        do {
            val operation = OperationFactory(
                proceduresList, opCursor, inputInstructions.first()
            ).getOperation()

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