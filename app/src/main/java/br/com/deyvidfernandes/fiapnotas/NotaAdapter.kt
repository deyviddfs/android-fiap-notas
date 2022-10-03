package br.com.deyvidfernandes.fiapnotas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotaAdapter(private val dataSet: MutableList<Nota>): RecyclerView.Adapter<NotaAdapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textViewDisciplina: TextView
        val textViewNota: TextView

        init {
            textViewDisciplina = view.findViewById(R.id.textViewDisciplina)
            textViewNota = view.findViewById(R.id.textViewNota)
        }
    }

    //Define o layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recyclerview_nota, parent, false)

        return ViewHolder(view)
    }

    //De/Para Objeto para o layout
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nota = dataSet[position]
        holder.textViewDisciplina.text = nota.disciplina
        holder.textViewNota.text = nota.nota.toString()
    }

    //Retorna o tamanho da lista
    override fun getItemCount(): Int {
        return dataSet.size
    }
}