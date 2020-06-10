package com.example.mvp

import kotlinx.android.synthetic.main.activity_main.*

class MainPresenter( var  mainView: MainView?, val mainInteractor: MainInteractor): MainInteractor.OnCliqueShow{


    private  fun onSetMensagem() {
        mainView?.showMensagem()
    }
    fun  onClearMensagem(){
        mainView?.hiddenMensagem()
    }
    override fun onValida(v: Boolean) {
        if (v ){
            onClearMensagem()
        }else{
            onSetMensagem()
        }

    }


}