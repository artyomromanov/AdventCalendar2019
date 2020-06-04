package day7

const val testFilepathOne = "data/amplifier_test_one_data"
val testSequenceOne = listOf(4, 3, 2, 1, 0)

private val testFilepathTwo = "data/amplifier_test_two_data"
val testSequenceTwo = listOf(0, 1, 2, 3, 4)

private val testFilepathThee = "data/amplifier_test_three_data"
val testSequenceThree = listOf(1, 0, 4, 3, 2)

private val testFilepathFour = "data/amplifier_test_four_data"
val testSequenceFour = listOf(9, 8, 7, 6, 5)

private const val testFilepathFive = "data/amplifier_test_five_data"
val testSequenceFive = listOf(9, 7, 8, 5, 6)


fun main() {

    //println(AmplifierCombinationsTester.generateMaximumLoopedSignal())

    println(AmplifierCombinationsTester.generateMaximumFeedbackLoopSignal())

    //Test One
    //println(AmplifierCombinationsTester.generateMaximumTestSignal(testFilepathOne, testSequenceOne))

    //Test Two
    //println(AmplifierCombinationsTester.generateMaximumTestSignal(testFilepathTwo, testSequenceTwo))

    //Test Three
    //println(AmplifierCombinationsTester.generateMaximumTestSignal(testFilepathThee, testSequenceThree))

    //Test Four
    //println(AmplifierCombinationsTester.generateMaximumFeedbackLoopTestSignal(testFilepathFour, testSequenceFour))

    //Test Five
    //println(AmplifierCombinationsTester.generateMaximumFeedbackLoopTestSignal(testFilepathFive, testSequenceFive))

}


