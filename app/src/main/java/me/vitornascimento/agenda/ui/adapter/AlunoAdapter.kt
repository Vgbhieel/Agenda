package me.vitornascimento.agenda.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import me.vitornascimento.agenda.R
import me.vitornascimento.agenda.database.AgendaDatabase
import me.vitornascimento.agenda.model.Aluno

class AlunoAdapter(private val context: Context) : BaseAdapter() {

    private val alunos = ArrayList<Aluno>()
    private val dao = AgendaDatabase.getInstance(context).getTelefoneDAO()

    override fun getCount(): Int {
        return this.alunos.size
    }

    override fun getItem(position: Int): Aluno {
        return this.alunos[position]
    }

    override fun getItemId(position: Int): Long {
        return this.alunos[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.item_aluno, parent, false)

        val nome: TextView = view.findViewById(R.id.item_aluno_nome)
        val telefone: TextView = view.findViewById(R.id.item_aluno_telefone)
        val alunoAtual = alunos[position]
        nome.text = alunoAtual.nome
        telefone.text = dao.buscaPrimeiroTelefoneDoAluno(alunoAtual.id).numero

        return view
    }

    fun remove(aluno: Aluno) {
        alunos.remove(aluno)
        notifyDataSetChanged()
    }

    fun atualiza(alunos: List<Aluno>) {
        this.alunos.clear()
        this.alunos.addAll(alunos)
        notifyDataSetChanged()
    }


}