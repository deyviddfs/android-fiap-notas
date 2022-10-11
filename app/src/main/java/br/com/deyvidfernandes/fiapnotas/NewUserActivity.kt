package br.com.deyvidfernandes.fiapnotas

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.deyvidfernandes.fiapnotas.databinding.ActivityNewUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class NewUserActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityNewUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.buttonEnviar.setOnClickListener {
            listenerButtonEnviar()
        }

        if (binding.root !is EditText) {
            binding.root.setOnTouchListener(View.OnTouchListener { _, _ ->
                hideSoftKeyboard(this)
                false
            })
        }
    }

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager: InputMethodManager = activity.getSystemService(
            INPUT_METHOD_SERVICE
        ) as InputMethodManager
        if (inputMethodManager.isAcceptingText) {
            inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken,
                0
            )
        }
    }

    private fun listenerButtonEnviar() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextSenha1.text.toString()
        val nome = binding.editTextNome.text.toString()
        if (validarCamposObrigatorios()) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(nome)
                            .build()
                        val user = auth.currentUser
                        user?.updateProfile(profileUpdates)
                        Toast.makeText(
                            baseContext, R.string.usuario_cadastrado_com_sucesso,
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }
        }
    }

    private fun validarCamposObrigatorios(): Boolean{
        val view = binding.root
        if(binding.editTextNome.text.isNullOrEmpty()) {
            Toast.makeText(view.context, R.string.o_nome_e_obrigatorio, Toast.LENGTH_LONG)
                .show()
            binding.editTextNome.requestFocus()
            return false
        }
        else if(binding.editTextEmail.text.isNullOrEmpty()) {
            Toast.makeText(view.context, R.string.o_email_e_obrigatorio, Toast.LENGTH_LONG)
                .show()
            binding.editTextEmail.requestFocus()
            return false
        }
        else if(binding.editTextSenha1.text.isNullOrEmpty() ||
            binding.editTextSenha2.text.isNullOrEmpty() ||
            binding.editTextSenha1.text.toString() != binding.editTextSenha2.text.toString()
        ) {
            Toast.makeText(view.context, R.string.os_campos_senhas_sao_obrigatorios_e_devem_ser_iguais, Toast.LENGTH_LONG)
                .show()
            binding.editTextSenha1.setText(R.string.vazio)
            binding.editTextSenha2.setText(R.string.vazio)
            binding.editTextSenha1.requestFocus()
            return false
        }
        return true
    }
}