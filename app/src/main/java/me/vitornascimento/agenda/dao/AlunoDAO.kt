package me.vitornascimento.agenda.dao

import androidx.room.*
import me.vitornascimento.agenda.model.Aluno

@Dao
interface AlunoDAO {

    @Insert
    fun salva(aluno: Aluno): Long

    @Query("SELECT * FROM aluno")
    fun todos(): List<Aluno>

    @Delete
    fun remove(aluno: Aluno)

    @Update
    fun edita(aluno: Aluno)

}