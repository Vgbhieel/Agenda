package me.vitornascimento.agenda.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import me.vitornascimento.agenda.dao.AlunoDAO
import me.vitornascimento.agenda.dao.TelefoneDAO
import me.vitornascimento.agenda.database.converter.ConversorCalender
import me.vitornascimento.agenda.database.converter.ConversorTipoTelefone
import me.vitornascimento.agenda.model.Aluno
import me.vitornascimento.agenda.model.Telefone
import me.vitornascimento.agenda.model.TipoTelefone

@Database(entities = [Aluno::class, Telefone::class], version = 7, exportSchema = false)
@TypeConverters(value = [ConversorCalender::class, ConversorTipoTelefone::class])
abstract class AgendaDatabase : RoomDatabase() {

    abstract fun getAlunoDAO(): AlunoDAO
    abstract fun getTelefoneDAO(): TelefoneDAO

    companion object {

        private val NOME_DB = "agenda.db"

        fun getInstance(context: Context): AgendaDatabase {
            val db = Room
                .databaseBuilder(context, AgendaDatabase::class.java, NOME_DB)
                .allowMainThreadQueries()
                .addMigrations(object : Migration(6, 7) {
                    override fun migrate(database: SupportSQLiteDatabase) {

                        //RENOMEIA A TABELA  A ANTIGA TABELA
                        database.execSQL("ALTER TABLE Aluno RENAME TO tmp_aluno")

                        //CRIA NOVA TABELA COM A ESTRUTURA DESEJADA
                        database.execSQL(
                            "CREATE TABLE IF NOT EXISTS `Aluno` (" +
                                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                    "`nome` TEXT, " +
                                    "`email` TEXT, " +
                                    "`momentoDeCadastro` INTEGER NOT NULL)"
                        )

                        //INSERE OS DADOS DA ANTIGA TABELA
                        database.execSQL(
                            "INSERT INTO Aluno (" +
                                    "id," +
                                    "nome," +
                                    "email," +
                                    "momentoDeCadastro) " +
                                    "SELECT id, nome, email, momentoDeCadastro FROM tmp_aluno"
                        )

                        //CRIANDO NOVA TABELA Telefone
                        database.execSQL(
                            "CREATE TABLE IF NOT EXISTS `Telefone` (" +
                                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                    "`alunoId` INTEGER NOT NULL, " +
                                    "`numero` TEXT, " +
                                    "`tipo` TEXT)"
                        )

                        //INSERE OS DADOS EXISTENTES NA NOVA TABELA Telefone
                        database.execSQL(
                            "INSERT INTO Telefone (" +
                                    "alunoId," +
                                    "numero) " +
                                    "SELECT id, telefoneFixo FROM tmp_aluno"
                        )

                        //TODOS OS DADOS DA Telefone SAO FIXOS, LOGO UPDATE TIPO
                        database.execSQL(
                            "UPDATE Telefone SET tipo = ?", arrayOf(TipoTelefone.FIXO)
                        )

                        //REMOVE TABELA COM A ESTRUTURA ANTIGA
                        database.execSQL("DROP TABLE tmp_aluno")

                    }
                })
                .build()
            return db
        }
    }

}