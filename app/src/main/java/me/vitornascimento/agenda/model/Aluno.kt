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

    @ColumnInfo
    var nome: String? = "",

    @ColumnInfo
    var telefoneCelular: String? = "",

    @ColumnInfo
    var telefoneFixo: String? = "",

    @ColumnInfo
    var email: String? = "",

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo
    var momentoDeCadastro: Calendar = Calendar.getInstance()

) : Parcelable {

    override fun toString(): String {
        return nome!!
    }

    fun getNomeCompleto(): String {
        return nome!!
    }

    fun getTelefone(): String? {
        return if (telefoneCelular?.isNotBlank() == true) {
            telefoneCelular
        } else {
            telefoneFixo
        }
    }

    fun getDataFormatada(): String {
        val localBrasil = Locale("pt", "BR")
        val sdf = SimpleDateFormat("dd/MM/yyyy", localBrasil)
        return sdf.format(momentoDeCadastro.time)
    }
}
