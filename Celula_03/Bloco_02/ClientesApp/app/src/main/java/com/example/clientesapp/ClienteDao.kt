package com.example.clientesapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import info.camposha.kotlinsqlite.DbHelper


class ClienteDao(context: Context) {
    var banco = DbHelper(context)

    /**
     * Vamos criar nosso método insert ().
          * Insere dados no banco de dados SQLIte.
     */

    fun insert(cliente: Cliente): String {
        val db = banco.writableDatabase
        val contentValues = ContentValues()
       // val sql= "Insert into table...."
       // db.execSQL(sql)
        contentValues.put(CLIENTE_NOME, cliente.nome)
        contentValues.put(CLIENTE_FONE, cliente.fone)
        contentValues.put(CLIENTE_IDADE, cliente.idade)

        var resp_id=   db.insert(TABLE_CLIENTES, null, contentValues)
         val msg = if(resp_id!=-1L){
             "Inserido com sucesso"
        }else{
             "Erro ao inserir"
        }
        db.close()
        return msg
    }
    /**
     * Vamos criar um método para atualizar uma linha com novos valores de campo.
     * */
    fun update(cliente: Cliente):
            Boolean {
        val db = banco.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(CLIENTE_ID, cliente.id)
        contentValues.put(CLIENTE_NOME, cliente.nome)
        contentValues.put(CLIENTE_FONE, cliente.fone)
        contentValues.put(CLIENTE_IDADE, cliente.idade)
        //db.update()

        db.insertWithOnConflict(TABLE_CLIENTES,null,contentValues,SQLiteDatabase.CONFLICT_REPLACE)
        db.close()

        return true
    }

    /**
     * Vamos criar uma função para excluir uma determinada linha com base no ID.
     * */
    fun remove(cliente: Cliente) : Int {
        val db = banco.writableDatabase
        return db.delete(TABLE_CLIENTES,"ID =?", arrayOf(cliente.id.toString()))
    }

    /**
     * A propriedade getter abaixo retornará um Cursor contendo nosso conjunto de dados.
     * */
    fun getAll(): ArrayList<Cliente>{
        Log.v("LOG", "GetAll")
        val db = banco.writableDatabase
        val  sql = "SELECT *  from "+TABLE_CLIENTES
            val cursor = db.rawQuery(sql ,null)
            val clientes =ArrayList<Cliente>()
            while (cursor.moveToNext()){
                val cliente= clienteFromCursor(cursor)
                clientes.add(cliente)
            }
        cursor.close()
        db.close()
        Log.v("LOG", "Get ${clientes.size}")
        return clientes
        }


    fun getByName(name:String): ArrayList<Cliente>{
        Log.v("LOG", "GetAll")
        val db = banco.writableDatabase
        val  sql = "SELECT *  from "+TABLE_CLIENTES+" where $CLIENTE_NOME like '%$name%'"
        val cursor = db.rawQuery(sql ,null)
        val clientes =ArrayList<Cliente>()
        while (cursor.moveToNext()){
            val cliente= clienteFromCursor(cursor)
            clientes.add(cliente)
        }
        cursor.close()
        db.close()
        Log.v("LOG", "Get ${clientes.size}")
        return clientes
    }

    private fun clienteFromCursor(cursor: Cursor): Cliente{
        val id = cursor.getInt(cursor.getColumnIndex(CLIENTE_ID))
        val nome = cursor.getString(cursor.getColumnIndex(CLIENTE_NOME))
        val fone = cursor.getString(cursor.getColumnIndex(CLIENTE_FONE))
        val idade = cursor.getInt(cursor.getColumnIndex(CLIENTE_IDADE))
        return Cliente(id,nome,fone,idade)
    }


}

