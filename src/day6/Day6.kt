package day6

const val filepath = "data/orbit_map"
const val universalCenterOfMass = "COM"
const val yourLocation = "YOU"
const val santasLocation = "SAN"

fun main(){

    val computer = OrbitalComputer()
    println(computer.compileOrbitalData())
    computer.plotCourseToSantasLocation()

}