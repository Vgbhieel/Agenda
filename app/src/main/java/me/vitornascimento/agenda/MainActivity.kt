package me.vitornascimento.agenda

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import me.vitornascimento.agenda.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setTitle("Lista de Alunos")

        val listaAlunos = arrayListOf("João", "José", "Maria", "Ana", "Paulo",
                                      "João", "José", "Maria", "Ana", "José",
                                      "Maria", "Ana", "Paulo", "João", "José",
                                      "Maria", "Ana")
        val lvAlunos = binding.lvAlunos

        lvAlunos.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaAlunos)
    }
}