package me.vitornascimento.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
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

        registerForContextMenu(binding.lvAlunos)

        val vitor = Aluno("Vitor", "21966589742")
            dao.salva(vitor)
        val gabrieel = Aluno("Gabriel", "21988553779")
            dao.salva(gabrieel)
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
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.add("Remover")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menuInfo: AdapterContextMenuInfo = item.menuInfo as AdapterContextMenuInfo
        val alunoClicado = adapter.getItem(menuInfo.position)
        dao.remove(alunoClicado as Aluno)
        adapter.remove(alunoClicado)
        return super.onContextItemSelected(item)
    }
}
