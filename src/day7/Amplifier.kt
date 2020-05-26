package day7

class Amplifier(setOfCombinations: Set<Int>) {

    val combinationsList: List<List<Int>>

    init {
        combinationsList = initializePhaseSettingsCombinations(setOfCombinations)
    }

    private fun initializePhaseSettingsCombinations(setOfCombinations : Set<Int>) : List<List<Int>>{
        return addRemaining(setOfCombinations).toList()
    }

    private fun addRemaining(remainingNumbers: Set<Int>, previousList: MutableList<Int> = mutableListOf()): MutableList<MutableList<Int>> {
        val combinationsList: MutableList<MutableList<Int>> = mutableListOf()
        for (number in remainingNumbers) {
            val copiedList = mutableListOf<Int>().apply { addAll(previousList); add(number) }
            val setToPassOnwards = mutableSetOf<Int>().apply { addAll(remainingNumbers); remove(number) }

            when {
                remainingNumbers.size > 1 -> combinationsList.addAll(addRemaining(setToPassOnwards, copiedList))
                remainingNumbers.size == 1 -> combinationsList.add(copiedList)
            }
        }
        return combinationsList
    }
}