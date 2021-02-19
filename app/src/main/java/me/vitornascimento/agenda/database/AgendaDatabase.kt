package me.vitornascimento.agenda.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.vitornascimento.agenda.dao.AlunoDAO
import me.vitornascimento.agenda.model.Aluno

@Database(entities = [Aluno::class], version = 1, exportSchema = false)
abstract class AgendaDatabase : RoomDatabase() {

    abstract fun getDAO(): AlunoDAO

    companion object {

        private val NOME_DB = "agenda.db"

        fun getInstance(context: Context): AgendaDatabase {
            val db = Room
                .databaseBuilder(context, AgendaDatabase::class.java, NOME_DB)
                .allowMainThreadQueries()
                .build()
            return db
        }
    }

}