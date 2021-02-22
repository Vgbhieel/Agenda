package me.vitornascimento.agenda.database.converter

import androidx.room.TypeConverter
import me.vitornascimento.agenda.model.TipoTelefone

class ConversorTipoTelefone {

    @TypeConverter
    fun paraString(tipo: TipoTelefone): String {
        return tipo.name
    }

    @TypeConverter
    fun paraTipoTelefone(valor: String): TipoTelefone {
        return TipoTelefone.valueOf(valor)
    }

}
