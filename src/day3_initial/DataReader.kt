package day3_initial

import java.io.File

object DataReader {

    private val input = File(filepath)

    private val firstWirePathData: MutableList<String>
    private val secondWirePathData: MutableList<String>

    init {
        firstWirePathData = input.readText().substringBefore('\n').split(",").toMutableList()
        secondWirePathData = input.readText().substringAfter('\n').split(",").toMutableList()
    }

    fun getFirstWireData() = firstWirePathData
    fun getSecondWireData() = secondWirePathData

}