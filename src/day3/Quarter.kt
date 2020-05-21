package day3

sealed class Quarter(open val index: Int) {
    data class I(override val index: Int = 1) : Quarter(index)
    data class II(override val index: Int = 2) : Quarter(index)
    data class III(override val index: Int = 3) : Quarter(index)
    data class IV(override val index: Int = 4) : Quarter(index)
    data class ZERO(override val index: Int = 0) : Quarter(index)
}