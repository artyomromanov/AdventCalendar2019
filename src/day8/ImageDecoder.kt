package day8

import java.io.File

const val filepath = "data/image_data"
const val rows = 6
const val columns = 25

object ImageDecoder {

    private var imageMatrix = decodeImageData()
    private var valuesList = mutableListOf<Map<Int, List<Int>>>()

    //Prints the layers of the image
    fun calculateImageData(): MutableList<Map<Int, List<Int>>> {
        imageMatrix.forEach { layer ->
            val valuesMap: Map<Int, List<Int>> = layer
                .flatten()
                .groupBy { it }
                .toSortedMap()
            valuesList.add(valuesMap)
        }
        valuesList.sortBy { it[0]?.size }

        println("Layer 0 contains fewest amount of zeroes.")
        println("Amount of 1s * 2s on that level is ${ImageDecoder.valuesList.first()[1]?.size} * ${ImageDecoder.valuesList.first()[2]?.size}")
        println("Answer is :  ${ImageDecoder.valuesList.first()[1]?.size!!.times(ImageDecoder.valuesList.first()[2]?.size!!)}")

        return valuesList

    }

    //Initialize the 3D array of the image layers
    private fun decodeImageData(): MutableList<MutableList<List<Int>>> {

        val data = File(filepath).readText().chunked(columns * rows)
        val matrix: MutableList<MutableList<List<Int>>> = mutableListOf()
        val decodedImage = mutableMapOf<Int, Int>()

        data.forEach { layer ->
            val rows: MutableList<List<Int>> = mutableListOf()
            layer
                .chunked(columns)
                .forEachIndexed { yPos, row ->
                    var xPos = 0
                    rows.add(row.map {
                        val value = it.toString().toInt()
                        val coordIndex = xPos + (columns * yPos)
                        if (value != 2) decodedImage.putIfAbsent(coordIndex, value)
                        xPos++
                        value
                    })
                }
            matrix.add(rows)
        }
        printImage(decodedImage)
        return matrix
    }

    private fun printImage(decodedImage: MutableMap<Int, Int>) {
        decodedImage
            .toSortedMap()
            .values.chunked(columns)
            .forEach { println(it) }
    }
}