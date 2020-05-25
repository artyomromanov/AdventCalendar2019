package day6

import java.io.File

class OrbitalComputer {


    private var instructionsMap: Map<String, List<String>>

    init {
        instructionsMap = initializeOrbitalMapValues()

    }

    fun compileOrbitalData(objectKey: String = universalCenterOfMass, orbitLevel: Int = 0): Int {
        var totalOrbits = 0
        val moons = instructionsMap.getOrDefault(objectKey, emptyList())
        println("Object : $objectKey -> Moons: $moons, OrbitLevel : $orbitLevel")
        if (moons.isNotEmpty()) {
            for (moon in moons) {
                totalOrbits += compileOrbitalData(moon, orbitLevel + 1) //dive deeper a level
            }
        }
        return totalOrbits + orbitLevel
    }


    private fun initializeOrbitalMapValues(): Map<String, List<String>> {

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