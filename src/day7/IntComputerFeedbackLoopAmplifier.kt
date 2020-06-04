package day7

import Computer
import operations.Input
import operations.OperationFactory
import operations.Output

class IntComputerFeedbackLoopAmplifier(filepath: String) : Computer {

    val proceduresList: MutableList<String> = initializeData(filepath)

    override fun runProgram(inputInstructions: List<Int>): Int {
        //This is done within the Amplifier class
        return -1
    }
}