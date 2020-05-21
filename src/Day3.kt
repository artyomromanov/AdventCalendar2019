import java.io.File
import java.lang.RuntimeException

class WireCrossingLocator {

    private val filepath = "data/input_wire_paths"
    val input = File(filepath)

    private val firstWirePathData: MutableList<String>
    private val secondWirePathData: MutableList<String>

    private var xCursor = 0
    private var yCursor = 0
    private var timeDelay = 0


    private val firstWirePathMap: MutableList<Coordinate> = mutableListOf()
    private val secondWirePathMap: MutableList<Coordinate> = mutableListOf()

    //Read input and split into first and second wire data
    init {
        firstWirePathData = input.readText().substringBefore('\n').split(",").toMutableList()
        secondWirePathData = input.readText().substringAfter('\n').split(",").toMutableList()
    }

    inner class Coordinate(val xPos: Int, val yPos: Int, val timeDelay: Int, val quarter: Quarter){
        fun printInfo() : String {
            return "X= ${this.xPos}; Y= ${this.yPos}; Time Delay = ${this.timeDelay}"
        }
    }
    //Cursor enum
    sealed class Cursor {
        object X : Cursor()
        object Y : Cursor()
        object TD : Cursor()
    }

    //Cursor enum
    sealed class Wire {
        object First : Wire()
        object Second : Wire()
    }

    sealed class Quarter {
        object I : Quarter()
        object II : Quarter()
        object III : Quarter()
        object IV : Quarter()
        object ZERO : Quarter()
    }

    fun locateCrossings(): Coordinate {

        //init coordinates array where all coordinates first wire ever travelled to will be stored
        //first initial coordinate of 0, 0 where both wires begin is stored as first element of the array

        val crossingCoordinates = mutableListOf<Coordinate>()

        firstWirePathData.forEach {
            mapWirePathEntry(it, Wire.First)
        }
        println("First wire mapping complete. Grid entries - ${firstWirePathMap.size}")
        println("Resetting cursor and time delay")
        setCursor(0, Cursor.X)
        setCursor(0, Cursor.Y)
        setCursor(0, Cursor.TD)

        secondWirePathData.forEach {
            mapWirePathEntry(it, Wire.Second)
        }
        println("Second wire mapping complete. Grid entries - ${secondWirePathMap.size}")

        //I(n) loop to find the matching coordinates. Sorting by quarter helps reduce operation count
        firstWirePathMap.forEach { firstWireCoordinate ->
            for (secondWireCoordinate in secondWirePathMap) {
                if (firstWireCoordinate.quarter == secondWireCoordinate.quarter) {
                    if (firstWireCoordinate.xPos == secondWireCoordinate.xPos) {
                        if (firstWireCoordinate.yPos == secondWireCoordinate.yPos) {
                            crossingCoordinates.add(
                                Coordinate(
                                    firstWireCoordinate.xPos,
                                    firstWireCoordinate.yPos,
                                    firstWireCoordinate.timeDelay + secondWireCoordinate.timeDelay, //new coordinate contains joint Time Delay
                                    firstWireCoordinate.quarter
                                )
                            )
                        }
                    }
                }
            }
            crossingCoordinates.sortBy { it.timeDelay }
            //Sorted by time delay or by absolute value for Part I of the puzzle
            //crossingCoordinates.sortBy { it.xPos.absoluteValue + it.yPos.absoluteValue }
        }

        crossingCoordinates.forEachIndexed { index, coordinate ->
            println("$index. Crossing : ${coordinate.printInfo()}")
        }
        return crossingCoordinates[0]
    }

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

    private fun addEntry(wire: Wire) {

        val quarter = when {
            xCursor > 0 && yCursor > 0 -> Quarter.I
            xCursor < 0 && yCursor > 0 -> Quarter.II
            xCursor < 0 && yCursor < 0 -> Quarter.III
            xCursor > 0 && yCursor < 0 -> Quarter.IV
            else -> Quarter.ZERO
        }

        when (wire) {
            Wire.First -> {
                firstWirePathMap.add(Coordinate(xCursor, yCursor, timeDelay, quarter))
            }
            Wire.Second -> {
                secondWirePathMap.add(Coordinate(xCursor, yCursor, timeDelay, quarter))
            }
        }
    }

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
        }
    }
}