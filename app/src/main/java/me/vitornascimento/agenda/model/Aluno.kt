package me.vitornascimento.agenda.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
@Entity
data class Aluno(
    var nome: String,
    var telefone: String,
    var email: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var momentoDeCadastro: Calendar = Calendar.getInstance()
) : Parcelable {
    override fun toString(): String {
        return nome
    }

    fun getNomeCompleto(): String {
        return nome
    }

    fun getDataFormatada(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        return sdf.format(momentoDeCadastro.time)
    }
}
