package br.com.deyvidfernandes.fiapnotas

import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
data class Nota(val disciplina: String? = null,
                val nota: Double? = 0.0) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}
