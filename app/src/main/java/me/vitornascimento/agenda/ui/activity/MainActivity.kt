package me.vitornascimento.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import me.vitornascimento.agenda.R
import me.vitornascimento.agenda.dao.AlunoDAO
import me.vitornascimento.agenda.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

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
    }

    override fun onResume() {
        super.onResume()
        val lvAlunos = binding.lvAlunos
        val dao = AlunoDAO
        lvAlunos.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dao.todos())
    }
}