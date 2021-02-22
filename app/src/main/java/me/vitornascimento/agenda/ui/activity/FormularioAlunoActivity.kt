package me.vitornascimento.agenda.ui.activity


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import me.vitornascimento.agenda.R
import me.vitornascimento.agenda.dao.AlunoDAO
import me.vitornascimento.agenda.database.AgendaDatabase
import me.vitornascimento.agenda.databinding.FormularioAlunoActivityBinding
import me.vitornascimento.agenda.model.Aluno


class FormularioAlunoActivity : AppCompatActivity() {
    private lateinit var binding: FormularioAlunoActivityBinding
    private lateinit var campoNome: EditText
    private lateinit var campoTelefoneCelular: EditText
    private lateinit var campoTelefoneFixo: EditText
    private lateinit var campoEmail: EditText
    private lateinit var aluno: Aluno
    private lateinit var dao: AlunoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FormularioAlunoActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        inicializaCampos()
        carregaAluno()

        dao = AgendaDatabase.getInstance(this).getAlunoDAO()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.formulario_aluno_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_salvar) {
            finalizaFormulario()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun finalizaFormulario() {
        preencheAluno()
        if (aluno.id > 0) {
            dao.edita(aluno)
            finish()
        } else {
            dao.salva(aluno)
            finish()
        }
    }

    private fun preencheAluno() {
        val nome = campoNome.text.toString()
//        val telefoneCelular = campoTelefoneCelular.text.toString()
//        val telefoneFixo = campoTelefoneFixo.text.toString()
        val email = campoEmail.text.toString()

        aluno.nome = nome
//        aluno.telefoneCelular = telefoneCelular
//        aluno.telefoneFixo = telefoneFixo
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
//        campoTelefoneCelular.setText(aluno.telefoneCelular)
//        campoTelefoneFixo.setText(aluno.telefoneFixo)
        campoEmail.setText(aluno.email)
    }

    private fun inicializaCampos() {
        campoNome = binding.etFormularioAlunoNome
        campoTelefoneCelular = binding.etFormularioAlunoTelefoneCelular
        campoTelefoneFixo = binding.etFormularioAlunoTelefoneFixo
        campoEmail = binding.etFormularioAlunoEmail
    }
}