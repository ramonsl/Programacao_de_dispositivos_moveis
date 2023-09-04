package com.example.randonnumber

import android.util.Log
import androidx.core.os.HandlerCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlin.random.Random

class RandonInteractor {

    interface  OnClique{
        fun onError(msg: String)
        fun onSuccess(msg: String)
    }

    fun gerar(inicio: String, fim: String, listener: OnClique){

        Log.v("Log","INICIO INTERACTOR "+inicio)
        Log.v("Log","INICIO FIM "+fim)
        var gerar=0

        try {

                if (inicio.toInt() > fim.toInt()) {
                    listener.onError("Numero inicial Maior que numero final")
                } else {
                    gerar = Random.nextInt(inicio.toInt(), fim.toInt())
                    listener.onSuccess(gerar.toString())
                    Log.v("Log", "NUMERO GERADO " + gerar)

                }
            } catch (e : Exception){
                Log.v("Log", "ERRO " + e.message)

                listener.onError("Deu um erro cabuloso")

            }
        }




}