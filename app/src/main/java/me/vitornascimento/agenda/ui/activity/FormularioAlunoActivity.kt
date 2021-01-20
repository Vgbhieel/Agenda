package me.vitornascimento.agenda.ui.activity


import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.vitornascimento.agenda.R
import me.vitornascimento.agenda.dao.AlunoDAO
import me.vitornascimento.agenda.databinding.FormularioAlunoActivityBinding
import me.vitornascimento.agenda.model.Aluno


class FormularioAlunoActivity : AppCompatActivity() {
    private lateinit var binding: FormularioAlunoActivityBinding
    private lateinit var campoNome: EditText
    private lateinit var campoTelefone: EditText
    private lateinit var campoEmail: EditText
    private lateinit var aluno: Aluno
    private val dao = AlunoDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FormularioAlunoActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        inicializaCampos()
        configuraBtnSalvar()
        carregaAluno()
    }

    private fun configuraBtnSalvar() {
        val btnSalvar = binding.btnFormularioAlunoSalvar
        btnSalvar.setOnClickListener {
            finalizaFormulario()
        }
    }

    private fun finalizaFormulario() {
        preencheAluno()
        if (aluno.nome.isNotBlank() and (aluno.telefone.isNotBlank() or aluno.email.isNotBlank())) {
            if (aluno.id > 0) {
                dao.edita(aluno)
                finish()
            } else {
                dao.salva(aluno)
                finish()
            }
        } else {
            Toast.makeText(
                this,
                "O campo nome e ao menos um contato são obrigatórios.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun preencheAluno() {
        val nome = campoNome.text.toString()
        val telefone = campoTelefone.text.toString()
        val email = campoEmail.text.toString()

        aluno.nome = nome
        aluno.telefone = telefone
        aluno.email = email
    }

    private fun carregaAluno() {
        val dados = intent
        if (dados.hasExtra("aluno")) {
            title = getString(R.string.titulo_secundario_formulario_aluno)
            aluno = dados.getParcelableExtra<Aluno>("aluno") as Aluno
            preencheCampos()
        } else {
            title = getString(R.string.titulo_formulario_aluno)
            aluno = Aluno()
        }
    }

    private fun preencheCampos() {
        campoNome.setText(aluno.nome)
        campoTelefone.setText(aluno.telefone)
        campoEmail.setText(aluno.email)
    }

    private fun inicializaCampos() {
        campoNome = binding.etFormularioAlunoNome
        campoTelefone = binding.etFormularioAlunoTelefone
        campoEmail = binding.etFormularioAlunoEmail
    }
}