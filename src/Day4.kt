import java.util.function.Predicate

val passwordRange = 158126..624574


class PasswordGenerator {

    private val predicateTwoConsecutiveNumbers = Predicate<Int> {

        var previousNum = -1
        var hasConsecutiveTwo = 0 //Zero for no double occurence, and -1 for larger than two group of the same number

        it.toString().forEachIndexed { numberIndex, number ->
            var num = number.toInt()
            when {
                num == previousNum -> {
                    if(num == hasConsecutiveTwo){
                        //third occurence(or more)
                        hasConsecutiveTwo = -1
                    }else{
                        if(hasConsecutiveTwo == 0 || hasConsecutiveTwo != -1){
                            //first occurence || replacing sequence
                            hasConsecutiveTwo = num
                        }
                    }
                }
                num < previousNum -> {
                    return@Predicate false
                }
            }
            previousNum = num
        }
        return@Predicate hasConsecutiveTwo != -1 && hasConsecutiveTwo != 0
    }

    fun generate() {
        passwordRange
            .filter { predicateTwoConsecutiveNumbers.test(it) }
            .also { println(it.size) }
        //.forEach { println(it) } //prints all the elements of the list
    }
}