package com.example.clientesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.android.synthetic.main.activity_update.FABSave
import kotlinx.android.synthetic.main.activity_update.edtFone
import kotlinx.android.synthetic.main.activity_update.edtIdade
import kotlinx.android.synthetic.main.activity_update.edtNome

class UpdateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val cliente = intent.getParcelableExtra<Cliente>("cliente")

        edtFone.setText(cliente.fone.toString())
          edtNome.setText( cliente.nome.toString())
          edtIdade.setText( cliente.idade.toString())


        FABRemove.setOnClickListener {
            val clienteDao = ClienteDao(this)
            clienteDao.remove(cliente)
            onBackPressed()

        }

        FABSave.setOnClickListener {
            var clienteUP = Cliente(cliente.id,edtNome.text.toString(),edtFone.text.toString(),(edtIdade.text.toString().toInt()))
            var clienteDao = ClienteDao(this)
            clienteDao.update(clienteUP)
            onBackPressed()
        }

        FABBack.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })


    }





}
