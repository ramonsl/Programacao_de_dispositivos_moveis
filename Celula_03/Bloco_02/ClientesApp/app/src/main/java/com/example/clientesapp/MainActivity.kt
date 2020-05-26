package com.example.clientesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var clientesList = mutableListOf<Cliente>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateAdapter()
        initRecyclerView()
        FabAdd.setOnClickListener(View.OnClickListener {
            val it = Intent(this, SaveActivity::class.java)
            startActivity(it)
        })

        FABfiltro.setOnClickListener {
            findAdapter(edtSearch.text.toString())
        }
    }


    override fun onResume() {
        super.onResume()
        updateAdapter()
        initRecyclerView()
    }

    private fun updateAdapter() {
        val clienteDao = ClienteDao(this)
        clientesList.clear() //todo
        clientesList = clienteDao.getAll()
        if (clientesList.isEmpty()) {
            rvDados.setVisibility(View.GONE);
            txtMsg.setVisibility(View.VISIBLE);
            txtMsg.setText("Sem dados para exibir")
        }
        else {
            rvDados.setVisibility(View.VISIBLE);
            txtMsg.setVisibility(View.GONE);
        }
       rvDados.adapter?.notifyDataSetChanged()
    }


    private fun findAdapter(name: String) {
        val clienteDao = ClienteDao(this)
        clientesList.clear()
        clientesList = clienteDao.getByName(name)
        if (clientesList.isEmpty()) {
            rvDados.setVisibility(View.GONE);
            txtMsg.setVisibility(View.VISIBLE);
            txtMsg.setText("Nenhum $name encontrado")
        }
        else {
            rvDados.setVisibility(View.VISIBLE);
            txtMsg.setVisibility(View.GONE);
        }
        rvDados.adapter?.notifyDataSetChanged()
    }

    private fun initRecyclerView() {
        Log.v("LOG", "Inicia RecyclerView")
        val adapter2 = ClienteAdapter(clientesList)
        rvDados.adapter = adapter2
        val layout = GridLayoutManager(this, 2)
       // val layout = LinearLayoutManager(this)
        rvDados.layoutManager = layout
    }
}
