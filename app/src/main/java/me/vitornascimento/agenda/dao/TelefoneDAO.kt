package me.vitornascimento.agenda.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import me.vitornascimento.agenda.model.Telefone

@Dao
interface TelefoneDAO {

    @Query(
        "SELECT t.* FROM Telefone t " +
                "WHERE t.alunoId = :alunoId " +
                "LIMIT 1"
    )
    fun buscaPrimeiroTelefoneDoAluno(alunoId: Int): Telefone

    @Insert
    fun salva(vararg telefones: Telefone)

    @Query(
        "SELECT t.* FROM Telefone t " +
                "WHERE t.alunoId = :alunoId"
    )
    fun buscaTodosTelefonesDoAluno(alunoId: Int): List<Telefone>

    @Update
    fun edita(telefones: List<Telefone>)

}