package com.example.randonnumber

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class RandonActivity : AppCompatActivity(),RandonView {

    private val presenter = RandonPresenter(this, RandonInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGerar.setOnClickListener { gerar() }
    }
    private fun gerar() {
        Log.v("Log", "INICIO " + edtInicio.text.toString())
        Log.v("Log", "FIM " + edtFim.text.toString())
        presenter.gerarNumero(edtInicio.text.toString(), edtFim.text.toString())
    }

    override fun showNumber(msg: String) {


        txtNumerRandomico.visibility = View.VISIBLE
        txtNumerRandomico.text= msg
    }

    override fun showError(msg: String) {
        txtNumerRandomico.visibility = View.GONE
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()

    }



}