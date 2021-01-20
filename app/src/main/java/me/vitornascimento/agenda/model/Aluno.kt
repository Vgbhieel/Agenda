package me.vitornascimento.agenda.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Aluno(var nome: String = "", var telefone: String = "", var email: String = "", var id: Int = 0) : Parcelable {
    override fun toString(): String {
        return nome
    }
}
