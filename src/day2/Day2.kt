package day2

const val gravityBasevalue = 1090665
const val deltaElementOneValue = 300000
const val deltaElementTwoValue = 1
const val elementOne = 2
const val elementTwo = 12


fun main(){

    val computer = IntComputer()

    computer.runProgram(listOf(elementOne, elementTwo))
        .let { computer.calculateElementFactors(it) }
        .also { println(it) }

}
