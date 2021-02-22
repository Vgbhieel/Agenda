package me.vitornascimento.agenda.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Telefone(var numero: String?, var tipo: TipoTelefone?) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 1

    @ForeignKey(
        entity = Aluno::class,
        parentColumns = ["id"],
        childColumns = ["alunoId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )
    var alunoId: Int = 1

}