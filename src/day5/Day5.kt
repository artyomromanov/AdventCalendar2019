package day5

//Input ID's
const val airconID = 1                   //ID for AirCon
const val thermalRadController = 5      //ID for Thermal Radiator controller

fun main(){

    val intComputerMkII = IntComputerMarkII()
    intComputerMkII.runProgram(listOf(thermalRadController))

}



