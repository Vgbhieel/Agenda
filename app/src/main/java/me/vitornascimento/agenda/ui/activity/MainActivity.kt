package me.vitornascimento.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import me.vitornascimento.agenda.R
import me.vitornascimento.agenda.dao.AlunoDAO
import me.vitornascimento.agenda.databinding.MainActivityBinding
import me.vitornascimento.agenda.model.Aluno

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: MainActivityBinding
    private val dao = AlunoDAO
    private lateinit var adapter: ArrayAdapter<Aluno?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        title = getString(R.string.titulo_main)

        val fabNovoAluno = binding.fabMain
        fabNovoAluno.setOnClickListener {
            startActivity(Intent(this, FormularioAlunoActivity::class.java))
        }

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dao.todos())
        binding.lvAlunos.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        adapter.clear()
        adapter.addAll(dao.todos())

        binding.lvAlunos.setOnItemClickListener { _, _, position, _ ->
            val todosAlunos = dao.todos()
            val alunoClicado = todosAlunos[position]
            val vaiParaFormularioAlunoActivity = Intent(this, FormularioAlunoActivity::class.java)
            vaiParaFormularioAlunoActivity.putExtra("aluno", alunoClicado)
            startActivity(vaiParaFormularioAlunoActivity)
        }

        binding.lvAlunos.setOnItemLongClickListener { _, _, position, _ ->
            val todosAlunos = dao.todos()
            val alunoClicado = todosAlunos[position]
            val dao = AlunoDAO()
            dao.remove(alunoClicado)
            adapter.remove(alunoClicado)
            true
        }
    }
}
