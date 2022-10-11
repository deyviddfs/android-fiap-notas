package br.com.deyvidfernandes.fiapnotas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.deyvidfernandes.fiapnotas.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.buttonLogin.setOnClickListener {
            listenerButtonLogin()
        }

        binding.buttonNovoUsuario.setOnClickListener {
            listenerNovoUsuario()
        }

        binding.textViewEsqueceuSenha.setOnClickListener {
            listenerEsqueceuSenha()
        }
    }

    private fun listenerEsqueceuSenha() {
        val intent = Intent(this, EsqueciSenhaActivity::class.java)
        startActivity(intent)
    }

    private fun listenerNovoUsuario() {
        val intent = Intent(this, NewUserActivity::class.java)
        startActivity(intent)
    }

    private fun listenerButtonLogin() {
        val email = binding.editTextEmail.text.toString()
        val senha = binding.editTextSenha.text.toString()
        if (email.isNotBlank() && senha.isNotBlank()) {
            auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            baseContext, R.string.usuario_e_ou_senha_invalidos,
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.editTextEmail.setText(R.string.vazio)
                        binding.editTextSenha.setText(R.string.vazio)
                    }
                }
        }
        else{
            Toast.makeText(
                baseContext, R.string.o_email_e_a_senha_sao_obrigatorios,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}