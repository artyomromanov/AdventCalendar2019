package util

object OperationsList {
    //Init operations map
    val allOperations: Map<Int, DigitCode> = mapOf(
        Pair(0, DigitCode.OP_CODE), Pair(1, DigitCode.OP_CODE_EXTRA), Pair(2, DigitCode.PARAM_ONE),
        Pair(3, DigitCode.PARAM_TWO)
    )
}