package day2

import java.io.File

class IntComputer (elementOne : Int, elementTwo : Int) {

    private val elementOne = elementOne
        get() { return if(field in 0..99) field else -1 }
    private val elementTwo = elementTwo
        get() { return if(field in 0..99) field else -1 }

    private val input = File("data/input_int_computer")
    private val procedureList = mutableListOf<Int>()

    init {
        input
            .readText()
            .split(",")
            .forEach {
                procedureList.add(it.trim().toInt())
            }
    }

    //Runs the new day2.IntComputer
    fun compute(): Int {

        var opCursor = 0
        var writeCursor: Int? = null
        var readIndexA: Int? = null
        var readIndexB: Int? = null

        //change the required positions before running the program
        with(procedureList){
            set(1, elementOne)
            set(2, elementTwo)
        }

        //begin looping through the array
        loop@ do {
            //initialize cursor values
            try {
                writeCursor = procedureList.getOrNull(opCursor + 3)
                readIndexA = procedureList.getOrNull(opCursor + 1)
                readIndexB = procedureList.getOrNull(opCursor + 2)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
            //do different operations - 1, 2, or 99
            when (procedureList[opCursor]) {

                1 -> {
                    if (writeCursor != null && readIndexA != null && readIndexB != null) {
                        procedureList[writeCursor] = (procedureList[readIndexA] + procedureList[readIndexB])
                        opCursor += 4
                    } else throw RuntimeException("Incorrect indexing through the program reached")
                }
                2 -> {
                    if (writeCursor != null && readIndexA != null && readIndexB != null) {
                        procedureList[writeCursor] = (procedureList[readIndexA] * procedureList[readIndexB])
                        opCursor += 4
                    } else throw RuntimeException("Incorrect indexing through the program reached")
                }
                99 -> {
                    break@loop;
                }
                else -> {
                    throw RuntimeException("Incorrect operation exception")
                }
            }
        } while (opCursor <= procedureList.size)
        return procedureList[0]
    }

    //Calculates the necessary noun and verb
    fun calculateElementFactors(requiredValue : Int) : Int{

        val noun = (requiredValue - gravityBasevalue) / deltaElementOneValue //300 000
        val verb = ((requiredValue - gravityBasevalue) % deltaElementOneValue) / deltaElementTwoValue //1

        return 100 * noun + verb

    }
}