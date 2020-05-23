package day2

const val gravityBasevalue = 1090665
const val deltaElementOneValue = 300000
const val deltaElementTwoValue = 1

fun main(){

    val computer = IntComputer(2, 12)

    computer.compute()
        .let { computer.calculateElementFactors(it) }
        .also { println(it) }

}
