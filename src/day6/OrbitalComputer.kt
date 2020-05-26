package day6

import java.io.File

class OrbitalComputer {


    private var instructionsMap: Map<String, MutableList<String>>
    private var santasOrbitLevel: Int = 0
    private var yourOrbitLevel: Int = 0
    private var orbitalJumps = 0 //Counter to add orbits to reach Santa
    
    init {
        instructionsMap = initializeOrbitalMapValues()

    }
    //Recursively locates the moons and their branches, then add all orbits upwards
    fun compileOrbitalData(objectKey: String = universalCenterOfMass, orbitLevel: Int = 0): Int {
        var totalOrbits = 0
        val moons = instructionsMap.getOrDefault(objectKey, mutableListOf())
        //println("Object : $objectKey -> Moons: $moons, OrbitLevel : $orbitLevel")
        if (moons.isNotEmpty()) {
            for (moon in moons) {
                totalOrbits += compileOrbitalData(moon, orbitLevel + 1) //dive deeper a level
            }
        }
        return totalOrbits + orbitLevel
    }
    //The above function altered slightly to meet in the middle where both paths meet between Santa and yourself
    fun plotCourseToSantasLocation(objectKey: String = universalCenterOfMass, orbitLevel: Int = 0): Boolean {

        var isPath = false //Triggers when Santa or Yourself are located
        var isAnotherPath: Boolean //Modifier to see if the other party is also reachable at that path

        val moons = instructionsMap.getOrDefault(objectKey, mutableListOf())
        if (moons.isNotEmpty()) {
            for (moon in moons) {
                if (moon == santasLocation) {
                    santasOrbitLevel =
                        orbitLevel.also { println("Santa located at level $it, orbiting moon $objectKey") }
                    isPath = true; orbitalJumps++
                }
                if (moon == yourLocation) {
                    yourOrbitLevel =
                        orbitLevel.also { println("You are known to be at level $it, orbiting moon $objectKey") }
                    isPath = true; orbitalJumps++
                }

                isAnotherPath = plotCourseToSantasLocation(moon, orbitLevel + 1)

                if (isAnotherPath && !isPath) {
                    isPath = true
                    orbitalJumps++
                } else if (isAnotherPath && isPath) {
                    println("Total orbits = ${orbitalJumps - 1}") // Since at the meeting point total orbits will be added twice, we need to subtract one.
                }
            }
        }
        return isPath
    }


    private fun initializeOrbitalMapValues(): Map<String, MutableList<String>> {

        val map = mutableMapOf<String, MutableList<String>>()

        val instructionsList = mutableListOf<String>()
        val file = File(filepath)
        file
            .readText()
            .split("\n")
            .forEach {
                instructionsList.add(it.trim())
            }
        instructionsList.forEach { instruction ->
            val static = instruction.split(")").first()
            val orbiting = instruction.split(")").last()

            map.putIfAbsent(static, mutableListOf(orbiting)).also {
                if (!it.isNullOrEmpty()) {
                    it += orbiting
                    map[static] = it
                }
            }
        }
        return map.toMap()
    }
}