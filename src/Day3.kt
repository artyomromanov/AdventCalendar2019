import day3_initial.*
import java.lang.RuntimeException
import kotlin.math.absoluteValue

class WireCrossingLocator {

    private var xCursor = 0
    private var yCursor = 0
    private var timeDelay = 0

    private val firstWirePathMap: MutableList<MutableList<Coordinate>> = mutableListOf(
        mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf()
    )
    private val secondWirePathMap: MutableList<MutableList<Coordinate>> = mutableListOf(
        mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf()
    )

    init {
        //Map the input to coordinates array
        mapCoordinates()
    }

    fun locateCrossings(): Coordinate? {

        val crossingCoordinates = mutableListOf<Coordinate>()

        //Locate the actual crossings
        firstWirePathMap.forEachIndexed { quarterIndex, quarterList ->
            if (quarterIndex != 0) {
                //Save the index of the quarter currently being monitored(1,2,3 or 4)
                for (firstWireCoordinate in quarterList) {
                    for (secondWireCoordinate in secondWirePathMap[quarterIndex]) {
                        if (firstWireCoordinate.xPos.equals(secondWireCoordinate.xPos)) {
                            if (firstWireCoordinate.yPos.equals(secondWireCoordinate.yPos)) {
                                crossingCoordinates.add(
                                    Coordinate(
                                        firstWireCoordinate.xPos,
                                        firstWireCoordinate.yPos,
                                        firstWireCoordinate.timeDelay + secondWireCoordinate.timeDelay,
                                        firstWireCoordinate.quarter
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }


        crossingCoordinates.sortBy { it.xPos.absoluteValue + it.yPos.absoluteValue
        }
        //Sorted by time delay or by absolute value for Part I of the puzzle
        //crossingCoordinates.sortBy { it.xPos.absoluteValue + it.yPos.absoluteValue }

        //Print
        InformationBot.printWireCrossingInfo(crossingCoordinates)

        return crossingCoordinates.getOrNull(0)
    }

    private fun mapCoordinates() {
        //Loop through the first Wire data and map the Coordinates
        DataReader.getFirstWireData().forEach { mapWirePathEntry(it, Wire.FIRST) }
        firstWirePathMap.forEachIndexed { quarterIndex, quarterList ->
            quarterList.sortBy { it.xPos.absoluteValue + it.yPos.absoluteValue }
            //if(quarterIndex != 0) println("<><><><><><> Quarter $quarterIndex  <><><><><><><>")
            /*quarterList.forEachIndexed { index, coordinate ->
                if(index == 0 || index % 10 == 0) println("$index. --> ${coordinate.xPos},  ${coordinate.yPos}")} */
            //--- prints th first and then every 10th entry for visualising the flow
        }
        InformationBot.printMappingInfo(firstWirePathMap, 1)
        //Reset the cursor
        setCursor(0, Cursor.RESET); InformationBot.resetCursorInfo()

        //Loop through the second Wire data and map the Coordinates
        DataReader.getSecondWireData().forEach { mapWirePathEntry(it, Wire.SECOND) }
        secondWirePathMap.forEach { quarterList ->
            quarterList.sortBy { it.xPos.absoluteValue + it.yPos.absoluteValue }
        }
        InformationBot.printMappingInfo(secondWirePathMap, 2)
    }

    ///Map the string inputs into Coordinates
    private fun mapWirePathEntry(entry: String, wire: Wire) {
        val vector = entry.first()
        val length = entry.substring(1).trim().toInt()
        when (vector) {
            //wire goes Right
            'R' -> {
                for (i in 1..length) {
                    setCursor(xCursor + 1, Cursor.X)
                    setCursor(timeDelay + 1, Cursor.TD)
                    addEntry(wire)
                }
            }
            //wire goes Down
            'D' -> {
                for (i in 1..length) {
                    setCursor(yCursor - 1, Cursor.Y)
                    setCursor(timeDelay + 1, Cursor.TD)
                    addEntry(wire)
                }
            }
            //wire goes Left
            'L' -> {
                for (i in 1..length) {
                    setCursor(xCursor - 1, Cursor.X)
                    setCursor(timeDelay + 1, Cursor.TD)
                    addEntry(wire)
                }
            }
            //wire goes Up
            'U' -> {
                for (i in 1..length) {
                    setCursor(yCursor + 1, Cursor.Y)
                    setCursor(timeDelay + 1, Cursor.TD)
                    addEntry(wire)
                }
            }
            else -> {
                throw RuntimeException("Incorrect operation exception")
            }
        }
    }

    //Adds new entry to a corresponding coordinate map
    private fun addEntry(wire: Wire) {

        val currentWireMap = when (wire) {
            Wire.FIRST -> {
                firstWirePathMap
            }
            Wire.SECOND -> {
                secondWirePathMap
            }
        }

        val quarter = when {
            xCursor > 0 && yCursor > 0 -> Quarter.I()
            xCursor < 0 && yCursor > 0 -> Quarter.II()
            xCursor < 0 && yCursor < 0 -> Quarter.III()
            xCursor > 0 && yCursor < 0 -> Quarter.IV()
            else -> Quarter.ZERO()
        }
        val quarterIndex = quarter.index

        when (quarter) {
            is Quarter.I -> {
                currentWireMap[quarterIndex].add(Coordinate(xCursor, yCursor, timeDelay, quarter))
            }
            is Quarter.II -> {
                currentWireMap[quarterIndex].add(Coordinate(xCursor, yCursor, timeDelay, quarter))
            }
            is Quarter.III -> {
                currentWireMap[quarterIndex].add(Coordinate(xCursor, yCursor, timeDelay, quarter))
            }
            is Quarter.IV -> {
                currentWireMap[quarterIndex].add(Coordinate(xCursor, yCursor, timeDelay, quarter))
            }
        }
    }

    //Responsible for rsetting the right cursor
    private fun setCursor(cursor: Int, param: Cursor) {
        when (param) {
            Cursor.X -> {
                this.xCursor = cursor
            }
            Cursor.Y -> {
                this.yCursor = cursor
            }
            Cursor.TD -> {
                this.timeDelay = cursor
            }
            Cursor.RESET -> {
                setCursor(0, Cursor.X)
                setCursor(0, Cursor.Y)
                setCursor(0, Cursor.TD)
            }
        }
    }
}