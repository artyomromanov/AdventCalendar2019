package day5

import computer.Computer
import operations.OperationFactory
import util.DigitCode

class IntComputerMarkII : Computer {

    private val filepath = "data/input_int_computer2"
    private val proceduresList: MutableList<String>

    init {
        proceduresList = initializeData(filepath)
        paginateProcedures(proceduresList)
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
        } while (opCursor <= proceduresList.size && opCursor != -1)
        println("Program halted.")
        return -1
    }
}