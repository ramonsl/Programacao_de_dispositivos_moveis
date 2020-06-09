package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class ImcActivity : AppCompatActivity(), ImcView {

    private  val presenter = ImcPresenter(this, Imc() )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalc.setOnLongClickListener()
    }

    override fun mostrarImc() {
        TODO("Not yet implemented")
    }

    override fun mostrarErrosCampos() {
        TODO("Not yet implemented")
    }

    override fun limparCalculo() {
        TODO("Not yet implemented")
    }


}
