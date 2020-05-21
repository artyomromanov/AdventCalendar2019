fun main(args: Array<String>) {

    val elementOneFactor = 0
    val elementTwoFactor = 0

    val requiredValue = 19690720;

    val intComputer = IntComputer(elementOneFactor, elementTwoFactor)
    val wireFixer = WireCrossingLocator();

    println(wireFixer.locateCrossings())

}
