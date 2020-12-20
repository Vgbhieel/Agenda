package me.vitornascimento.agenda

import android.app.Activity
import android.os.Bundle
import me.vitornascimento.agenda.databinding.MainActivityBinding

class MainActivity : Activity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}