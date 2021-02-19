package me.vitornascimento.agenda.database.converter

import androidx.room.TypeConverter
import java.util.*

class ConversorCalender {

    @TypeConverter
    fun toLong(valor: Calendar): Long {
        return valor.timeInMillis
    }

    @TypeConverter
    fun toCalendar(valor: Long): Calendar {
        val data = Calendar.getInstance()
        data.timeInMillis = valor
        return data
    }
}
