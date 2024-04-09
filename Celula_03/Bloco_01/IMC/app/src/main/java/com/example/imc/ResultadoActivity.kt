package com.example.imc

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultadoActivity : AppCompatActivity() {

    private lateinit var titleDensidadeCorporal: TextView
    private lateinit var textViewHeaderDensidadeCorporal: TextView
    private lateinit var textViewResultadoDensidadeCorporal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado2)

        // Inicializando os componentes com findViewById
        titleDensidadeCorporal = findViewById(R.id.titleDensidadeCorporal)
        textViewHeaderDensidadeCorporal = findViewById(R.id.textViewHeaderDensidadeCorporal)
        textViewResultadoDensidadeCorporal = findViewById(R.id.textViewResultadoDensidadeCorporal)

        // Obtendo o objeto IMC passado pela Intent
        val imc = intent.getParcelableExtra<IMC>("value")

        // Configurando os textos dos componentes da UI
        titleDensidadeCorporal.text = imc?.nome.toString()
        textViewHeaderDensidadeCorporal.text = imc?.calcular()
        textViewResultadoDensidadeCorporal.text = imc?.imc.toString()
    }
}
