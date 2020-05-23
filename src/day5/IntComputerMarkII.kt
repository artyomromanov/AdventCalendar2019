package day5

import day5.operations.OperationFactory
import day5.util.DigitCode
import java.io.File

class IntComputerMarkII(input: Int = 1) {

    private val inputData = File("data/input_int_computer2")
    private val proceduresList: MutableList<String>

    init {
        proceduresList = initializeProceduresList()
    }

    companion object {
        //Init operations map
        val allOperations: Map<Int, DigitCode> = mapOf(
            Pair(0, DigitCode.OP_CODE), Pair(1, DigitCode.OP_CODE_EXTRA), Pair(2, DigitCode.PARAM_ONE),
            Pair(3, DigitCode.PARAM_TWO)
        )
    }

    //Runs the new day2.IntComputer
    fun runTests(): Int {
        //Set cursor to 0
        var opCursor = 0
        //Begin program
        do {

            val operation = OperationFactory(proceduresList = proceduresList, cursor = opCursor).getOperation()

            with(operation) {
                readValues()
                execute()
                printOperationData()
            }
            opCursor += setCursor { operation.setCursor() }
        } while (opCursor <= proceduresList.size)
        return 1
    }

    private fun setCursor(cursor: () -> Int): Int {
        return cursor()
    }

    private fun initializeProceduresList(): MutableList<String> {
        val list = mutableListOf<String>()
        inputData
            .readText()
            .split(",")
            .forEach {
                list.add(it.trim())
            }
        return list
    }
}