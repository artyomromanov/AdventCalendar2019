import java.util.function.IntPredicate
import java.util.function.Predicate

val passwordRange = 158126..624574


class PasswordGenerator {

    private val predicateTwoConsecutiveNumbers = Predicate<Int> {

        var previousNum = -1
        var hasConsecutive = false

        it.toString().forEachIndexed { numberIndex, number ->
            when {
                number.toInt() == previousNum -> {
                    hasConsecutive = true
                }
                number.toInt() < previousNum -> {
                    return@Predicate false
                }
            }
            previousNum = number.toInt()
        }
        return@Predicate hasConsecutive
    }

    fun generate() {
        passwordRange
            .filter { predicateTwoConsecutiveNumbers.test(it) }
            .also { println(it) }
        //.forEach { println(it) } //prints all the elements of the list
    }
}