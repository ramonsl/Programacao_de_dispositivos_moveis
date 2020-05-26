package com.example.clientesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_save.*

class SaveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)


        FABBack.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })


        FABSave.setOnClickListener(View.OnClickListener {
            var cliente = Cliente(null,edtNome.text.toString(),edtFone.text.toString(),(edtIdade.text.toString().toInt()))
            var clienteDao = ClienteDao(this)
            clienteDao.insert(cliente)
            onBackPressed()
        })
    }


}
