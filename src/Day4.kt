import java.util.function.Predicate

val passwordRange = 158126..624574

class PasswordGenerator {

    private val predicate = Predicate<Int> {

        var previousNum = -1
        var sequenceTracker = 0
        var doublePredicateFulfilled = false

        it.toString().forEach { number ->

            val num = number.toInt()
            //Increasing predicate
            if (num < previousNum) {
                return@Predicate false
            }

            //Predicate of two, but not more consecutive numbers
            if (!doublePredicateFulfilled) {
                //If number is equal to his preceding number
                if (num == previousNum) {
                    //See if number is equals to stored tracker value(init = 0; when sequence > 3 in a row = -1
                    if (num == sequenceTracker) { //if number in a sequence is the same that was previously stored
                        sequenceTracker = -1 //sets the sequence as not valid for predicate, as it is longer than 2 characters
                    } else {
                        if (sequenceTracker != -1) { //otherwise, as long as number stored is not indicating an invalid sequence,
                            sequenceTracker = num   //store this new number, as this is a new sequence
                        }
                    }
                } else {
                    if (sequenceTracker != 0 && sequenceTracker != -1) { //if after two equal numbers stored tracker is valid
                        doublePredicateFulfilled = true  //this predicate is fulfilled, regardless of the remaining digits
                    }
                    sequenceTracker = 0 //in any case, reset the tracker
                }
            }
            previousNum = num //save this number to become the previous number in the next iteration
        }
        if (sequenceTracker != 0 && sequenceTracker != -1) doublePredicateFulfilled = true
        //reassess doublePredicate as if it is fulfilled with the last digit, it must be set to true
        return@Predicate doublePredicateFulfilled
    }


    fun generate() {
        passwordRange
            .filter { predicate.test(it) }
            .also { println(it.size) }
            //.forEach { println(it) } //prints all the elements of the list
    }
}