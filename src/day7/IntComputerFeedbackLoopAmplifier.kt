package day7

import computer.Computer

class IntComputerFeedbackLoopAmplifier(filepath: String) : Computer {

    val proceduresList: MutableList<String> = initializeData(filepath)

    override fun runProgram(inputInstructions: List<Int>): Int {
        //This is done within the Amplifier class
        return -1
    }
}