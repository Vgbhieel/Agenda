package me.vitornascimento.agenda.dao

import androidx.room.Dao
import androidx.room.Query
import me.vitornascimento.agenda.model.Telefone

@Dao
interface TelefoneDAO {

    @Query(
        "SELECT t.* FROM Telefone t " +
                "WHERE t.alunoId = :alunoId " +
                "LIMIT 1"
    )
    fun buscaPrimeiroTelefoneDoAluno(alunoId: Int): Telefone

}