package br.com.deyvidfernandes.fiapnotas

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.deyvidfernandes.fiapnotas.databinding.ActivityNewUserBinding


class NewUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO

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
}