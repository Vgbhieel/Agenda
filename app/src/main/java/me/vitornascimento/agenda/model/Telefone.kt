package me.vitornascimento.agenda.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Aluno::class,
            parentColumns = ["id"],
            childColumns = ["alunoId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Telefone(
    var numero: String,

    var tipo: TipoTelefone,

    var alunoId: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}