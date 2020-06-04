package day7

object AmplifierCombinationsTester {

    private const val filepath = "data/input_int_computer_amplifier"

    private val phaseSettings = setOf(0, 1, 2, 3, 4)
    private val feedbackLoopPhaseSettings = setOf(5, 6, 7, 8, 9)

    private val generatorOne = CombinationsGenerator(phaseSettings)
    val generatorTwo = CombinationsGenerator(feedbackLoopPhaseSettings)


    //part one task
    fun generateMaximumSignal(): Long {
        var amplifiedSignalResult = 0
        generatorOne.combinationsList.forEachIndexed { combinationIndex, combination ->
            var amplifierOutput = 0
            combination.forEachIndexed { currentAmplifier, phaseSetting ->
                val softwareCopy = IntComputerAmplifier(filepath)
                amplifierOutput = softwareCopy.runProgram(listOf(phaseSetting, amplifierOutput))
                if (amplifierOutput > amplifiedSignalResult) amplifiedSignalResult = amplifierOutput
            }
            println("Combination ${combinationIndex + 1} $combination generated $amplifiedSignalResult")
        }
        return amplifiedSignalResult.toLong()
    }


    //Part two task
    fun generateMaximumFeedbackLoopSignal(): String {
        var maximumFeedbackLoopSignal = 0
        var combi = ""
        generatorTwo.combinationsList.forEachIndexed { combinationIndex, combination ->
            println("Combination ${combinationIndex + 1} $combination launched...")
            val softwareCopy = IntComputerFeedbackLoopAmplifier(filepath)
            val amplifiers: MutableList<Amplifier> = mutableListOf()
            var currentSignal = 0
            //Init amplifiers
            combination.forEachIndexed { ampIndex, phaseSetting ->
                amplifiers.add(Amplifier(softwareCopy.proceduresList, ampIndex, phaseSetting))
            }
            var i = 0
            do {

                val signal = amplifiers[i].runProgram(currentSignal)
                if(signal != -1) currentSignal = signal else break
                if (currentSignal > maximumFeedbackLoopSignal) {
                    maximumFeedbackLoopSignal = currentSignal
                    combi = combination.toString()
                }
                i++
                i %= 5
            } while (currentSignal != -1)
            println()
        }
        return "Combination $combi generated $maximumFeedbackLoopSignal"
    }


    //Certain tests
    fun generateMaximumTestSignal(testData: String, testSequence: List<Int>): Int {

        val softwareCopy = IntComputerAmplifier(testData)

        //Instructions to be sent to the first amplifier
        var amplifierOutput = 0
        //Signal counter
        var amplifiedSignalResult = 0
        testSequence.forEachIndexed { amplifierIndex, phaseSetting ->
            println("Amplifier ${amplifierIndex + 1} supplied with phaseSetting $phaseSetting and signal $amplifierOutput")
            amplifierOutput = softwareCopy.runProgram(listOf(phaseSetting, amplifierOutput))
            println("Amplifier output : $amplifierOutput")
            if (amplifierOutput > amplifiedSignalResult) amplifiedSignalResult = amplifierOutput
            println("----------------------------------------------------------------------------------------------")
            println("Amplifier ${amplifierIndex + 1} output is $amplifiedSignalResult")
            println("----------------------------------------------------------------------------------------------")
        }
        return amplifiedSignalResult
    }

    fun generateMaximumFeedbackLoopTestSignal(testData: String, testSequence: List<Int>): Int {

        val softwareCopy = IntComputerFeedbackLoopAmplifier(testData)
        val amplifiers: MutableList<Amplifier> = mutableListOf()
        var currentSignal = 0

        //Init amplifiers
        testSequence.forEachIndexed { ampIndex, phaseSetting ->
            amplifiers.add(Amplifier(softwareCopy.proceduresList, ampIndex, phaseSetting))
            println("Amplifier ${ampIndex + 1} added, phase setting set to $phaseSetting")
        }
        var i = 0;
        var ii = 0;
        do {
            currentSignal = amplifiers[i].runProgram(currentSignal)
            i++
            ii++
            i %= 5
        } while (ii < 100)
        return -1
    }
}