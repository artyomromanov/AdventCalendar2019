package day3_initial

object InformationBot {

    fun printMappingInfo(map: MutableList<MutableList<Coordinate>>, wireNo: Int): Unit {

        var totalEntries = 0;
        println("$wireNo wire mapping complete. Grid entries:")
        map.forEachIndexed { quarterIndex, quarterData ->
            if (quarterIndex != 0) println("$quarterIndex. Quarter - mapped ${map[quarterIndex].size} entries")
            totalEntries += map[quarterIndex].size
        }
        println("$totalEntries total entries.")
    }

    fun resetCursorInfo() = println("Resetting cursor and time delay...")

    fun printWireCrossingInfo(crossingCoordinates: MutableList<Coordinate>) {
        crossingCoordinates.forEachIndexed { index, coordinate ->
            println("$index. Crossing : ${coordinate.printInfo()}")
        }
    }

    fun printElements(map : MutableList<MutableList<Coordinate>>){
        map.forEachIndexed { quarterIndex, quartersList ->
            if (quarterIndex != 0) println("<><><><><><> Quarter $quarterIndex  <><><><><><><>")
            quartersList.forEachIndexed { index, coordinate ->
                if (index == 0 || index % 10 == 0) println("$index. --> ${coordinate.xPos},  ${coordinate.yPos}")
            }
            //--- prints the first and then every 10th entry for visualising the flow
        }
    }
}