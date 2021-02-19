package me.vitornascimento.agenda.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import me.vitornascimento.agenda.dao.AlunoDAO
import me.vitornascimento.agenda.database.converter.ConversorCalender
import me.vitornascimento.agenda.model.Aluno

@Database(entities = [Aluno::class], version = 6, exportSchema = false)
@TypeConverters(value = [ConversorCalender::class])
abstract class AgendaDatabase : RoomDatabase() {

    abstract fun getDAO(): AlunoDAO

    companion object {

        private val NOME_DB = "agenda.db"

        fun getInstance(context: Context): AgendaDatabase {
            val db = Room
                .databaseBuilder(context, AgendaDatabase::class.java, NOME_DB)
                .allowMainThreadQueries()
                .addMigrations(object : Migration(5, 6) {
                    override fun migrate(database: SupportSQLiteDatabase) {

                        //RENOMEIA A TABELA  A ANTIGA TABELA
                        database.execSQL("ALTER TABLE Aluno RENAME TO tmp")

                        //CRIA NOVA TABELA COM A ESTRUTURA DESEJADA
                        database.execSQL(
                            "CREATE TABLE `Aluno` (" +
                                    "`nome` TEXT, " +
                                    "`telefoneCelular` TEXT, " +
                                    "`telefoneFixo` TEXT, " +
                                    "`email` TEXT, " +
                                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                    "`momentoDeCadastro` INTEGER NOT NULL)"
                        )

                        //INSERE OS DADOS DA ANTIGA TABELA
                        database.execSQL(
                            "INSERT INTO Aluno (" +
                                    "nome," +
                                    "telefoneCelular," +
                                    "telefoneFixo," +
                                    "email," +
                                    "id," +
                                    "momentoDeCadastro) " +
                                    "SELECT nome, telefoneCelular, telefoneFixo, email, id, momentoDeCadastro FROM tmp"
                        )

                        //REMOVE TABELA COM A ESTRUTURA ANTIGA
                        database.execSQL("DROP TABLE tmp")

                    }
                })
                .build()
            return db
        }
    }

}