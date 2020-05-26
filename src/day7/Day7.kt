package day7

fun main() {

    val phaseSettings = setOf(0, 1, 2, 3, 4)
    val inputSignal: Int = 0

    val computer = IntComputerAmplifier()
    val amplifier = Amplifier(phaseSettings)
    amplifier.combinationsList.forEach {
        println(it)
    }
    amplifier.combinationsList.also { println(it.size) }
    //computer.runProgram(phaseSettings)

}


