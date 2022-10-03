package br.com.deyvidfernandes.fiapnotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.deyvidfernandes.fiapnotas.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //TODO
    }
}