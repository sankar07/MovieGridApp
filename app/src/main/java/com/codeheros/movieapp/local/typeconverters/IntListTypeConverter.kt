package me.sankar.local.typeconverters

import androidx.room.TypeConverter

/**
 * Created by sankar on 2021-10-11.
 */
class IntListTypeConverter {
    companion object {
        @JvmStatic
        @TypeConverter
        fun fromListToString(list: List<Int>) = StringBuilder().apply {
            when (list.size) {
                0 -> append("")
                1 -> append(list.first().toString())
                else -> {
                    for (i in 0 until list.size - 1) {
                        append("${list[i]},")
                    }
                    append(list.last().toString())
                }
            }
        }.toString()

        @JvmStatic
        @TypeConverter
        fun fromStringToList(movies: String) = movies.split(",").map { it.toInt() }
    }
}