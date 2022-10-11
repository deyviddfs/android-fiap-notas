package br.com.deyvidfernandes.fiapnotas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.deyvidfernandes.fiapnotas.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    private val NOTAS = "notas"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.database.reference
        val userUid = Firebase.auth.currentUser?.uid
        val notas: MutableList<Nota> = ArrayList()

        database.child(NOTAS).child(userUid.toString()).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                notas.clear()
                for (postSnapshot in snapshot.children) {
                    val nota = postSnapshot.getValue(Nota::class.java)
                    notas.add(nota!!)
                }

                load(notas)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.i("FIREBASE", "The read failed: " + databaseError.message)
            }
        })
    }

    private fun load(notas: MutableList<Nota>) {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerView.adapter = NotaAdapter(notas)

        val itemDecor = DividerItemDecoration(binding.root.context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecor)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem)= when (item.itemId) {
        R.id.menu_perfil -> {
            val intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
            true
        }
        R.id.menu_sair -> {
            finish()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}