package br.com.deyvidfernandes.fiapnotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.deyvidfernandes.fiapnotas.databinding.ActivityEsqueciSenhaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EsqueciSenhaActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityEsqueciSenhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEsqueciSenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.buttonEnviar.setOnClickListener {
            listenerButtonEnviar()
        }
    }

    private fun listenerButtonEnviar() {
        val email = binding.editTextEmail.text.toString()

        if (email.isNotBlank()) {
            auth.sendPasswordResetEmail(email)
            Toast.makeText(
                baseContext, R.string.verifique_seu_email_para_concluir_o_processo_de_alteracao_de_senha,
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
        else{
            Toast.makeText(
                baseContext, R.string.o_email_e_obrigatorio,
                Toast.LENGTH_SHORT
            ).show()
            binding.editTextEmail.requestFocus()
        }
    }
}