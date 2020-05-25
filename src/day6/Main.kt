package day6

const val filepath = "data/orbit_map"
const val universalCenterOfMass = "COM"

fun main(){

    val computer = OrbitalComputer()
    println(computer.compileOrbitalData())

}