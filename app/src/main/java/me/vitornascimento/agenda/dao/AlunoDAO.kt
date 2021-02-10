package me.vitornascimento.agenda.dao

import me.vitornascimento.agenda.model.Aluno

class AlunoDAO {

    companion object {
        private val alunos = ArrayList<Aluno>()
        private var contadorDeId = 1

        fun todos(): List<Aluno> {
            return ArrayList(alunos)
        }

    }

    fun edita(alunoClicado: Aluno) {
        for (aluno: Aluno in alunos) {
            if (alunoClicado.id == aluno.id) {
                alunos.set(alunos.indexOf(aluno), alunoClicado)
            }
        }
    }

    fun salva(aluno: Aluno) {
        aluno.id = contadorDeId
        alunos.add(aluno)
        contadorDeId++
    }

    fun remove(alunoClicado: Aluno) {
        for (aluno: Aluno in alunos) {
            if (alunoClicado.id == aluno.id) {
                alunos.removeAt(alunos.indexOf(aluno))
            }
        }
    }
}