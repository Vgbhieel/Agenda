package me.vitornascimento.agenda

import android.app.Application
import me.vitornascimento.agenda.dao.AlunoDAO
import me.vitornascimento.agenda.model.Aluno

class AgendaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        criaAlunosDeTeste()
    }

    private fun criaAlunosDeTeste() {
        val dao = AlunoDAO
        dao.salva(Aluno("Vitor", "21966589742"))
        dao.salva(Aluno("Gabriel", "21988553779"))
    }

}