package day8

fun main(){

    ImageDecoder.calculateImageData()
    ImageDecoder.valuesList.forEachIndexed { layerIndex, layer ->
        println("<<<<<<< Layer $layerIndex >>>>>>")
        for(entry in layer.entries){
            println("Current layer has ${entry.value.size} amount of ${entry.key}s")

        }
    }
    println("Layer 0 contains fewest amount of zeroes.")
    println("Amount of 1s * 2s on that level is ${ImageDecoder.valuesList.first()[1]?.size} * ${ImageDecoder.valuesList.first()[2]?.size}")
    println("Answer is :  ${ImageDecoder.valuesList.first()[1]?.size!!.times(ImageDecoder.valuesList.first()[2]?.size!!)}")
}