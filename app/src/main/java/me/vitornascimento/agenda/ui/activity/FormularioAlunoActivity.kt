package me.vitornascimento.agenda.ui.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import me.vitornascimento.agenda.R
import me.vitornascimento.agenda.databinding.FormularioAlunoActivityBinding
import me.vitornascimento.agenda.model.Aluno
import me.vitornascimento.agenda.dao.AlunoDAO


class FormularioAlunoActivity : AppCompatActivity() {
    private lateinit var binding: FormularioAlunoActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FormularioAlunoActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        title = getString(R.string.titulo_formulario_aluno)

        val btnSalvar = binding.btnFormularioAlunoSalvar
        btnSalvar.setOnClickListener {
            salvarAluno()
        }
    }

    private fun salvarAluno() {
        val campoNome = binding.etFormularioAlunoNome.text.toString()
        val campoTelefone = binding.etFormularioAlunoTelefone.text.toString()
        val campoEmail = binding.etFormularioAlunoEmail.text.toString()

        if (campoNome.isNotBlank()) {
            val alunoCriado = Aluno(campoNome, campoTelefone, campoEmail)
            val dao = AlunoDAO()
            dao.salva(alunoCriado)
            finish()
        } else {
            binding.etFormularioAlunoNome.requestFocus()
            Toast.makeText(this, "O campo nome é obrigatório.", Toast.LENGTH_SHORT).show()
        }

    }
}