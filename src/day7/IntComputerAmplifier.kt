package day7

import Computer
import operations.Input
import operations.OperationFactory
import operations.Output
import java.lang.RuntimeException

class IntComputerAmplifier(filepath : String) : Computer {

    private val proceduresList: MutableList<String> = initializeData(filepath)

    override fun runProgram(inputInstructions: List<Int>): Int {
        //Set cursor to 0
        var opCursor = 0
        //Input operation counter
        var inputCounter = 0
        var outputSignal = -1
        //Begin program
        do {
            val operation = OperationFactory(proceduresList, opCursor).getOperation()
            if (operation is Input) {
                operation.input = inputInstructions[inputCounter]
                //println("Input $inputCounter")
                inputCounter++
                inputCounter %=2
            }
            with(operation) {
                readValues()
                execute()
                //printOperationData()
            }
            if (operation is Output) outputSignal = operation.inputOutput ?: -1
            opCursor = operation.retrieveCursor()
        } while (opCursor != -1)
        return if (outputSignal != -1) outputSignal else -1
    }
}