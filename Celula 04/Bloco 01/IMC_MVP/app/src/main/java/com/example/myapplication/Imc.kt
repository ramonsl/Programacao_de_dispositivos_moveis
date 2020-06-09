package com.example.myapplication


class Imc(var nome: String? ="", var peso: Float? = 0.0f, var altura: Float? =0.0f, var imc: Float? = 0.0f) {

interface OnCalcularListener{
    fun onValid()
    fun onSuccess()
    fun onCalclar()
}



}