package me.vitornascimento.agenda.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.vitornascimento.agenda.databinding.FormularioAlunoActivityBinding


class FormularioAlunoActivity : AppCompatActivity() {
    private lateinit var binding: FormularioAlunoActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FormularioAlunoActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setTitle("Cadastro de Alunos")
    }
}