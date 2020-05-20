package com.example.clientesapp

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cliente_item.view.*

class ClienteAdapter (private val clientes: List<Cliente>):
    RecyclerView.Adapter<ClienteAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        Log.v("LOG", "onCreate")
        val v= LayoutInflater.from(parent.context).inflate(R.layout.cliente_item,parent,false)
        val vh = VH(v)


        vh.itemView.setOnClickListener{
            val cliente= clientes[vh.adapterPosition]
            val it = Intent(parent.context,UpdateActivity::class.java)
            it.putExtra("cliente",cliente)
           parent.context.startActivity(it)

        }
        return vh
    }

    override fun getItemCount(): Int {
        return clientes.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        Log.v("LOG", "ViewHolder")
        var cliente = clientes[position]
        holder.txtFoneCliente.text=cliente.fone.toString()
        holder.txtNomeCliente.text=cliente.nome.toString()
        holder.txtIdadeCliente.text=cliente.idade.toString()
    }

    class VH(itenView: View): RecyclerView.ViewHolder(itenView){

        var txtNomeCliente:TextView=itenView.txtNameCliente
        var txtIdadeCliente:TextView=itenView.txtIdadeCliente
        var txtFoneCliente:TextView=itenView.txtFoneCliente
    }
}