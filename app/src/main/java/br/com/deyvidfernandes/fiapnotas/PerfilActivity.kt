package br.com.deyvidfernandes.fiapnotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.deyvidfernandes.fiapnotas.databinding.ActivityPerfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PerfilActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityPerfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        val user = auth.currentUser

        load(user)

        binding.buttonSalvar.setOnClickListener {
            listenerButtonSave(user)
        }
    }

    private fun listenerButtonSave(user: FirebaseUser?) {
        val nome = binding.editTextNome.text.toString()
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(nome)
            .build()

        user?.updateProfile(profileUpdates)

        Toast.makeText(
            baseContext, R.string.dados_do_usuario_atualizados_com_sucesso,
            Toast.LENGTH_SHORT
        ).show()
        finish()
    }

    private fun load(user: FirebaseUser?) {
        user?.let {
            val name = user.displayName
            val email = user.email
            binding.editTextNome.setText(name)
            binding.editTextEmail.setText(email)

            val emailVerified = user.isEmailVerified
            binding.switchEmailVerificado.isChecked = emailVerified
        }
    }
}