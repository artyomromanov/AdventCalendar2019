package day7

object AmplifierCombinationsTester {

    private const val filepath = "data/input_int_computer_amplifier"

    fun generateMaximumSignal(): Long {

        val phaseSettings = setOf(0, 1, 2, 3, 4)
        val generator = CombinationsGenerator(phaseSettings)
        //Signal counter
        var amplifiedSignalResult = 0

        generator.combinationsList.forEachIndexed { combinationIndex, combination ->

            //Instructions to be sent to the first amplifier
            var amplifierOutput = 0

            combination.forEachIndexed { amplifierIndex, phaseSetting ->
                val softwareCopy = IntComputerAmplifier(filepath)
                //println("Amplifier ${amplifierIndex + 1} supplied with phaseSetting $phaseSetting and signal $amplifierOutput")
                amplifierOutput = softwareCopy.runProgram(listOf(phaseSetting, amplifierOutput))
                if (amplifierOutput > amplifiedSignalResult) amplifiedSignalResult = amplifierOutput
                //println("----------------------------------------------------------------------------------------------")
                //println("Amplifier ${amplifierIndex + 1} output is $amplifiedSignalResult")
                //println("----------------------------------------------------------------------------------------------")
            }
            println("Combination ${combinationIndex + 1} $combination generated $amplifiedSignalResult")
        }
        return amplifiedSignalResult.toLong()
    }

    //Certain tests
    fun generateMaximumTestSignal(testData : String, testSequence : List<Int>) : Int {

        val softwareCopy = IntComputerAmplifier(testData)

        //Instructions to be sent to the first amplifier
        var amplifierOutput = 0
        //Signal counter
        var amplifiedSignalResult = 0
        testSequence.forEachIndexed { _, phaseSetting ->
            //println("Amplifier ${amplifierIndex + 1} supplied with phaseSetting $phaseSetting and signal $amplifierOutput")
            amplifierOutput = softwareCopy.runProgram(listOf(phaseSetting, amplifierOutput))
            //println("Amplifier output : $amplifierOutput")
            if (amplifierOutput > amplifiedSignalResult) amplifiedSignalResult = amplifierOutput
            //println("----------------------------------------------------------------------------------------------")
            //println("Amplifier ${amplifierIndex + 1} output is $amplifiedSignalResult")
            //println("----------------------------------------------------------------------------------------------")
        }
        return amplifiedSignalResult
    }
}