package me.vitornascimento.agenda.ui.activity


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import me.vitornascimento.agenda.R
import me.vitornascimento.agenda.dao.AlunoDAO
import me.vitornascimento.agenda.dao.TelefoneDAO
import me.vitornascimento.agenda.database.AgendaDatabase
import me.vitornascimento.agenda.databinding.FormularioAlunoActivityBinding
import me.vitornascimento.agenda.model.Aluno
import me.vitornascimento.agenda.model.Telefone
import me.vitornascimento.agenda.model.TipoTelefone


class FormularioAlunoActivity : AppCompatActivity() {
    private lateinit var binding: FormularioAlunoActivityBinding
    private lateinit var campoNome: EditText
    private lateinit var campoTelefoneCelular: EditText
    private lateinit var campoTelefoneFixo: EditText
    private lateinit var campoEmail: EditText
    private lateinit var aluno: Aluno
    private lateinit var db: AgendaDatabase
    private lateinit var alunoDao: AlunoDAO
    private lateinit var telefoneDao: TelefoneDAO
    private lateinit var todosTelefonesDoAluno: List<Telefone>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FormularioAlunoActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        db = AgendaDatabase.getInstance(this)
        alunoDao = db.getAlunoDAO()
        telefoneDao = db.getTelefoneDAO()

        inicializaCampos()
        carregaAluno()
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

        preencheObjetoAluno()

        if (alunoTemIdValido()) {

            alunoDao.edita(aluno)

            editaTelefones()

            finish()

        } else {

            val idAlunoSalvo = alunoDao.salva(aluno).toInt()
            val numeroCelular = campoTelefoneCelular.text.toString()
            val numeroFixo = campoTelefoneFixo.text.toString()

            salvaNumeros(numeroCelular, numeroFixo, idAlunoSalvo)

            finish()

        }
    }

    private fun editaTelefones() {
        todosTelefonesDoAluno.forEach {

            if (it.tipo == TipoTelefone.CELULAR) {
                it.numero = campoTelefoneCelular.text.toString()
            } else {
                it.numero = campoTelefoneFixo.text.toString()
            }

        }
        telefoneDao.edita(todosTelefonesDoAluno)
    }

    private fun salvaNumeros(
        numeroCelular: String,
        numeroFixo: String,
        idAlunoSalvo: Int
    ) {
        when {
            numeroCelular.isNotBlank() and numeroFixo.isNotBlank() -> {
                val telefoneCelular =
                    Telefone(numeroCelular, TipoTelefone.CELULAR, idAlunoSalvo)
                val telefoneFixo =
                    Telefone(numeroFixo, TipoTelefone.FIXO, idAlunoSalvo)
                telefoneDao.salva(telefoneCelular, telefoneFixo)
            }

            numeroCelular.isNotBlank() -> {
                val telefoneCelular =
                    Telefone(numeroCelular, TipoTelefone.CELULAR, idAlunoSalvo)
                telefoneDao.salva(telefoneCelular)
            }

            numeroFixo.isNotBlank() -> {
                val telefoneFixo =
                    Telefone(numeroFixo, TipoTelefone.FIXO, idAlunoSalvo)
                telefoneDao.salva(telefoneFixo)
            }
        }
    }

    private fun alunoTemIdValido() = aluno.id > 0

    private fun preencheObjetoAluno() {
        aluno.nome = campoNome.text.toString()
        aluno.email = campoEmail.text.toString()
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

        todosTelefonesDoAluno = telefoneDao.buscaTodosTelefonesDoAluno(aluno.id)
        todosTelefonesDoAluno.forEach {
            if (it.tipo == TipoTelefone.CELULAR) {
                campoTelefoneCelular.setText(it.numero)
            } else {
                campoTelefoneFixo.setText(it.numero)
            }
        }

        campoEmail.setText(aluno.email)
    }

    private fun inicializaCampos() {
        campoNome = binding.etFormularioAlunoNome
        campoTelefoneCelular = binding.etFormularioAlunoTelefoneCelular
        campoTelefoneFixo = binding.etFormularioAlunoTelefoneFixo
        campoEmail = binding.etFormularioAlunoEmail
    }
}