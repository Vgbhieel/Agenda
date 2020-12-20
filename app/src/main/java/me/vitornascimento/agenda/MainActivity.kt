package me.vitornascimento.agenda

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import me.vitornascimento.agenda.databinding.MainActivityBinding

class MainActivity : Activity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val listaAlunos = arrayListOf("João", "José", "Maria", "Ana", "Paulo",
                                      "João", "José", "Maria", "Ana", "José",
                                      "Maria", "Ana", "Paulo", "João", "José",
                                      "Maria", "Ana")
        val lvAlunos = binding.lvAlunos

        lvAlunos.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaAlunos)
    }
}