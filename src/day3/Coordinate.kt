package day3

class Coordinate(val xPos: Int, val yPos: Int, val timeDelay: Int, val quarter: Quarter) {
    fun printInfo(): String {
        return "X= ${xPos}; Y= ${yPos}; Time Delay = ${timeDelay}"
    }
}