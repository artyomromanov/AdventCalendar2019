import java.io.File

object FuelCalculator {

    private const val filepath = "data/input_fuel.txt"
    private val input = File(filepath)
    private val intList = mutableListOf<Int>()

    //Extracting the data
    init {
        input
            .readText()
            .split("\n")
            .forEach { intList.add(calculateFuelReq(it.trim().toInt())) }
    }
    //Calculate fuel recursively
    private fun calculateFuelReq(mass: Int): Int {
        val fuelRequired = mass/3 - 2
        return if (fuelRequired < 0) {
            0;
        } else {
            fuelRequired + calculateFuelReq(fuelRequired)
        }
    }
}