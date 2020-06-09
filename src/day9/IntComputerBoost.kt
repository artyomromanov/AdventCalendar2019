package day9

import computer.Computer
import operations.Input
import operations.OperationFactory
import operations.Output

class IntComputerBoost(filepath: String) : Computer {

    val proceduresList: MutableList<String> = initializeData(filepath)

    override fun runProgram(inputInstructions: List<Int>): Int {
        //Set cursor to 0
        var opCursor = 0
        //Set relative base to 0
        var relativeBase = 0
        //Input operation counter
        var inputCounter = 0
        var outputSignal = -1
        //Begin program
        do {
            val operation = OperationFactory(proceduresList, opCursor).getOperation()
            if (operation is Input) {
                operation.input = inputInstructions[inputCounter]
                inputCounter++
                inputCounter %= 2
            }
            with(operation) {
                readValues()
                execute()
                printOperationData()
            }
            if (operation is Output) {
                outputSignal = operation.inputOutput ?: -1
            }
            opCursor = operation.retrieveCursor()
            //println("Cursor value is $opCursor")

        } while (opCursor != -1)
        return outputSignal
    }
}