package br.com.deyvidfernandes.fiapnotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.deyvidfernandes.fiapnotas.databinding.ActivityEsqueciSenhaBinding

class EsqueciSenhaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEsqueciSenhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEsqueciSenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //TODO:
    }
}