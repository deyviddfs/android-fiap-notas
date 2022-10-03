package br.com.deyvidfernandes.fiapnotas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.deyvidfernandes.fiapnotas.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //TODO
    }
}