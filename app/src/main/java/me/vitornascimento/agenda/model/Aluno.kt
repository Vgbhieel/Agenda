package me.vitornascimento.agenda.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
@Entity
data class Aluno(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 1,

    @ColumnInfo
    var nome: String? = "",

    @ColumnInfo
    var email: String? = "",

    @ColumnInfo
    var momentoDeCadastro: Calendar = Calendar.getInstance()

) : Parcelable {

    override fun toString(): String {
        return nome!!
    }

    fun getDataFormatada(): String {
        val localBrasil = Locale("pt", "BR")
        val sdf = SimpleDateFormat("dd/MM/yyyy", localBrasil)
        return sdf.format(momentoDeCadastro.time)
    }
}
