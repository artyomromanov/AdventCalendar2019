package day7

import operations.Halt
import operations.Input
import operations.OperationFactory
import operations.Output

class Amplifier(proceduresList: MutableList<String>, val ampIndex: Int, val phaseSetting: Int) {

    var opCursor = 0
    var amplifiersProcedures : MutableList<String> = mutableListOf()

    init { amplifiersProcedures.addAll(proceduresList) }


    fun runProgram(inputSignal: Int): Int {

        var outputSignal = -1

        do {
            val operation = OperationFactory(amplifiersProcedures, opCursor).getOperation()
            if (operation is Input)
                if (opCursor == 0) operation.input = phaseSetting else operation.input = inputSignal

            with(operation) {
                readValues()
                execute()
                printOperationData()
            }
            if (operation is Output) {
                outputSignal = operation.inputOutput ?: -1
                opCursor = operation.retrieveCursor()
                break
            }

            if(operation is Halt) break

            opCursor = operation.retrieveCursor()

        } while (opCursor != -1)

        return outputSignal
    }
}