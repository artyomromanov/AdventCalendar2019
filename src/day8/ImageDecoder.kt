package day8

import java.io.File

const val filepath = "data/image_data"
const val rows = 6
const val columns = 25

object ImageDecoder {

    private var imageMatrix = readImageData()
    var valuesList = mutableListOf<Map<Int, List<Int>>>()

    //Prints the layers of the image
    fun calculateImageData(): MutableList<Map<Int, List<Int>>> {
        imageMatrix.forEach { layer ->
            val valuesMap : Map<Int, List<Int>> = layer
                .flatten()
                .groupBy { it }
                .toSortedMap()
            valuesList.add(valuesMap)
        }
        return valuesList.also { list -> list.sortBy { it[0]?.size } }
    }

    //Initialize the 3D array of the image layers
    private fun readImageData(): MutableList<MutableList<List<Int>>> {

        val data = File(filepath).readText().chunked(columns * rows)
        val matrix: MutableList<MutableList<List<Int>>> = mutableListOf()

        data.forEach { layer ->
            val rows: MutableList<List<Int>> = mutableListOf()
            layer
                .chunked(columns)
                .forEach { row ->
                    rows.add(row.map {
                        val value = it.toString().toInt()
                        value
                    })
                }
            matrix.add(rows)
        }
        return matrix
    }
}