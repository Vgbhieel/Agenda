package me.vitornascimento.agenda.dao

import me.vitornascimento.agenda.model.Aluno

class AlunoDAO {

    companion object {
        private val alunos = ArrayList<Aluno>()

        fun todos(): List<Aluno> {
            return ArrayList(alunos)
        }

    }

    fun salva(aluno: Aluno) {
        alunos.add(aluno)
    }
}