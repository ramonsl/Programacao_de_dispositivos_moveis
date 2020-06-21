package com.example.randonnumber

import android.util.Log

class RandonPresenter(var randonView :RandonView? , val randonInteractor: RandonInteractor):RandonInteractor.OnClique {

   fun gerarNumero(inicio :String, fim: String):String{
       Log.v("Log","INICIO PRESENTER "+ inicio)
       val numero = randonInteractor.gerar(inicio,fim,this)
       Log.v("Log"," PRESENTER "+ numero)
       return numero.toString()
   }
    override fun onError(msg: String) {
        randonView?.showError(msg)
    }
    override fun onSuccess(msg: String) {
        randonView?.showNumber(msg)
    }
}